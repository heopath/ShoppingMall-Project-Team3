package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.CartDAO;
import kr.co.springkmarketapp.dto.order.CartAddRequestDTO;
import kr.co.springkmarketapp.dto.order.CartDTO;
import kr.co.springkmarketapp.dto.order.CartListDTO;
import kr.co.springkmarketapp.dto.order.CartProductDTO;
import kr.co.springkmarketapp.dto.product.ProductOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDAO cartDAO;

    /*
     * 상품 상세에서 장바구니 담기
     */
    @Transactional
    public CartDTO addCart(Integer memberNo, CartAddRequestDTO request) {

        if (request.getProductNo() == null) {
            throw new IllegalArgumentException("상품 정보가 없습니다.");
        }

        if (request.getQuantity() == null || request.getQuantity() < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        // 1. 상품 판매상태 / 재고 확인
        CartProductDTO product =
                cartDAO.selectProductForCart(request.getProductNo());

        if (product == null) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }

        if (!"판매중".equals(product.getStatus())) {
            throw new IllegalArgumentException("현재 판매할 수 없는 상품입니다.");
        }

        // 2. 프론트에서 받은 옵션번호 정리
        List<Integer> requestOptionNos = request.getOptionNos() == null
                ? Collections.emptyList()
                : request.getOptionNos();

        Set<Integer> selectedOptionNoSet =
                new LinkedHashSet<>(requestOptionNos);

        if (selectedOptionNoSet.size() != requestOptionNos.size()) {
            throw new IllegalArgumentException("중복된 옵션이 포함되어 있습니다.");
        }

        // 3. 해당 상품이 실제로 가진 옵션 조회
        List<ProductOptionDTO> productOptions =
                cartDAO.selectProductOptionsForCart(request.getProductNo());

        Map<Integer, ProductOptionDTO> optionMap =
                productOptions.stream()
                        .collect(Collectors.toMap(
                                ProductOptionDTO::getOptionNo,
                                option -> option
                        ));

        List<ProductOptionDTO> selectedOptions =
                selectedOptionNoSet.stream()
                        .map(optionNo -> {

                            ProductOptionDTO option = optionMap.get(optionNo);

                            if (option == null) {
                                throw new IllegalArgumentException(
                                        "해당 상품에 존재하지 않는 옵션입니다."
                                );
                            }

                            return option;
                        })
                        .toList();

        // 4. 옵션 그룹마다 하나씩 모두 선택했는지 검증
        Set<Integer> requiredGroupNos =
                productOptions.stream()
                        .map(ProductOptionDTO::getOptionGroupNo)
                        .collect(Collectors.toSet());

        Set<Integer> selectedGroupNos =
                selectedOptions.stream()
                        .map(ProductOptionDTO::getOptionGroupNo)
                        .collect(Collectors.toSet());

        if (!requiredGroupNos.equals(selectedGroupNos)
                || selectedGroupNos.size() != selectedOptions.size()) {

            throw new IllegalArgumentException(
                    "상품 옵션을 모두 선택해주세요."
            );
        }

        // 예: 옵션 3번, 8번 선택 → "3,8"
        String optionSignature =
                selectedOptionNoSet.stream()
                        .sorted()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));

        // 5. 같은 회원 + 상품 + 옵션조합이 이미 있는지 확인
        CartDTO existingCart =
                cartDAO.selectCartByMemberProductOption(
                        memberNo,
                        request.getProductNo(),
                        optionSignature
                );

        int totalQuantity = request.getQuantity();

        if (existingCart != null) {
            totalQuantity += existingCart.getQuantity();
        }

        // 장바구니 수량도 재고보다 많으면 막기
        if (product.getStock() == null
                || product.getStock() < totalQuantity) {

            throw new IllegalArgumentException(
                    "상품 재고가 부족합니다. 현재 재고: "
                            + product.getStock() + "개"
            );
        }

        // 6. 같은 옵션 조합이 이미 있으면 수량만 증가
        if (existingCart != null) {

            cartDAO.updateCartQuantity(
                    existingCart.getCartNo(),
                    request.getQuantity()
            );

            existingCart.setQuantity(totalQuantity);

            return existingCart;
        }

        // 7. 처음 담는 조합이면 cart 저장
        CartDTO cartDTO = CartDTO.builder()
                .memberNo(memberNo)
                .productNo(request.getProductNo())
                .optionSignature(optionSignature)
                .quantity(request.getQuantity())
                .build();

        cartDAO.insertCart(cartDTO);

        // 8. 선택 옵션 저장
        if (!selectedOptionNoSet.isEmpty()) {
            cartDAO.insertCartOptions(
                    cartDTO.getCartNo(),
                    new ArrayList<>(selectedOptionNoSet)
            );
        }

        return cartDTO;
    }

    public List<CartListDTO> selectCartListByMemberNo(Integer memberNo) {
        return cartDAO.selectCartListByMemberNo(memberNo);
    }

    @Transactional
    public CartDTO updateCartQuantity(
            Integer memberNo,
            Integer cartNo,
            Integer quantity
    ) {
        if (quantity == null || quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }

        CartDTO cartDTO =
                cartDAO.selectCartByCartNoAndMemberNo(cartNo, memberNo);

        if (cartDTO == null) {
            throw new IllegalArgumentException("장바구니 상품을 찾을 수 없습니다.");
        }

        CartProductDTO product =
                cartDAO.selectProductForCart(cartDTO.getProductNo());

        if (product == null || !"판매중".equals(product.getStatus())) {
            throw new IllegalArgumentException("현재 판매할 수 없는 상품입니다.");
        }

        if (product.getStock() == null || product.getStock() < quantity) {
            throw new IllegalArgumentException(
                    "상품 재고가 부족합니다. 현재 재고: "
                            + product.getStock() + "개"
            );
        }

        cartDAO.updateCartQuantityByMemberNo(
                cartNo,
                memberNo,
                quantity
        );

        cartDTO.setQuantity(quantity);

        return cartDTO;
    }

    @Transactional
    public int deleteSelectedCarts(
            Integer memberNo,
            List<Integer> cartNos
    ) {
        if (cartNos == null || cartNos.isEmpty()) {
            throw new IllegalArgumentException("삭제할 상품을 선택하세요.");
        }

        List<Integer> uniqueCartNos = cartNos.stream()
                .filter(cartNo -> cartNo != null)
                .distinct()
                .toList();

        if (uniqueCartNos.isEmpty()) {
            throw new IllegalArgumentException("삭제할 상품을 선택하세요.");
        }

        // FK 때문에 옵션부터 삭제
        cartDAO.deleteCartOptionsByMemberNo(memberNo, uniqueCartNos);

        return cartDAO.deleteCartsByMemberNo(memberNo, uniqueCartNos);
    }

    public CartDTO selectCart(Integer cartNo) {
        return cartDAO.selectCart(cartNo);
    }

    public List<CartDTO> selectCartList() {
        return cartDAO.selectCartList();
    }

    public int updateCart(CartDTO cartDTO) {
        return cartDAO.updateCart(cartDTO);
    }

    public int deleteCart(Integer cartNo) {
        return cartDAO.deleteCart(cartNo);
    }
}