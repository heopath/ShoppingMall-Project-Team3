package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.CartDTO;
import kr.co.springkmarketapp.dto.order.CartListDTO;
import kr.co.springkmarketapp.dto.order.CartProductDTO;
import kr.co.springkmarketapp.dto.order.OrderItemOptionDTO;
import kr.co.springkmarketapp.dto.product.ProductOptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartDAO {

    /* 기존 장바구니 기능 */

    int insertCart(CartDTO cartDTO);

    CartDTO selectCart(@Param("cartNo") Integer cartNo);

    List<CartDTO> selectCartList();

    int updateCart(CartDTO cartDTO);

    int deleteCart(@Param("cartNo") Integer cartNo);


    /* 상품 상세 → 장바구니 담기 기능 */

    // 상품 판매 상태 / 재고 조회
    CartProductDTO selectProductForCart(
            @Param("productNo") Integer productNo
    );

    // 해당 상품의 옵션 전체 조회
    List<ProductOptionDTO> selectProductOptionsForCart(
            @Param("productNo") Integer productNo
    );

    // 같은 회원 + 같은 상품 + 같은 옵션 조합 조회
    CartDTO selectCartByMemberProductOption(
            @Param("memberNo") Integer memberNo,
            @Param("productNo") Integer productNo,
            @Param("optionSignature") String optionSignature
    );

    // 기존 장바구니 상품 수량 증가
    int updateCartQuantity(
            @Param("cartNo") Integer cartNo,
            @Param("quantity") Integer quantity
    );

    // 선택 옵션 저장
    int insertCartOption(@Param("cartNo") Integer cartNo,
                         @Param("optionNo") Integer optionNo);

    int insertCartOptions(
            @Param("cartNo") Integer cartNo,
            @Param("optionNos") List<Integer> optionNos
    );

    List<CartListDTO> selectCartListByMemberNo(
            @Param("memberNo") Integer memberNo
    );

    CartDTO selectCartByCartNoAndMemberNo(
            @Param("cartNo") Integer cartNo,
            @Param("memberNo") Integer memberNo
    );

    int updateCartQuantityByMemberNo(
            @Param("cartNo") Integer cartNo,
            @Param("memberNo") Integer memberNo,
            @Param("quantity") Integer quantity
    );

    int deleteCartOptionsByMemberNo(
            @Param("memberNo") Integer memberNo,
            @Param("cartNos") List<Integer> cartNos
    );

    int deleteCartsByMemberNo(
            @Param("memberNo") Integer memberNo,
            @Param("cartNos") List<Integer> cartNos
    );

    List<CartListDTO> selectCartListByMemberNoAndCartNos(
            @Param("memberNo") Integer memberNo,
            @Param("cartNos") List<Integer> cartNos
    );

    List<OrderItemOptionDTO> selectOrderItemOptionsByCartNo(
            @Param("cartNo") Integer cartNo
    );
}