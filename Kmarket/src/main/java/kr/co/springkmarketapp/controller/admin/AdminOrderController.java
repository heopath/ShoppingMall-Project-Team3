package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.admin.AdminOrderDetailDTO;
import kr.co.springkmarketapp.dto.order.DeliveryDetailDTO;
import kr.co.springkmarketapp.service.admin.AdminOrderService;
import kr.co.springkmarketapp.service.order.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;
    private final DeliveryService deliveryService;


    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "searchType", required = false) String searchType,
                       @RequestParam(value = "keyword", required = false) String keyword) {

        // 서비스 호출하여 실제 데이터 추출
        Map<String, Object> result = adminOrderService.getAdminOrderList(page, searchType, keyword);
        int totalPages = (int) result.get("totalPages");

        // 5단위 페이징 안정화 계산
        int pageSize = 5;
        int currentGroup = (int) Math.ceil((double) page / pageSize);
        int startPage = (currentGroup - 1) * pageSize + 1;
        int endPage = Math.min(startPage + pageSize - 1, totalPages);
        if (startPage < 1) startPage = 1;

        model.addAttribute("orders", result.get("list"));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "admin/order/list";
    }

    @GetMapping("/detail")
    @ResponseBody
    public AdminOrderDetailDTO getOrderDetail(@RequestParam("orderNo") Long orderNo) {
        AdminOrderDetailDTO detail = adminOrderService.getOrderDetail(orderNo);
        detail.setItems(adminOrderService.getOrderItemDetails(orderNo));
        return detail;
    }

    @PostMapping("/update-invoice")
    @ResponseBody
    public Map<String, Object> updateInvoice(@RequestParam Long orderNo,
                                             @RequestParam String courier,
                                             @RequestParam String invoiceNo,
                                             @RequestParam(required = false) String receiverName,
                                             @RequestParam(required = false) String receiverHp,
                                             @RequestParam(required = false) String zip,
                                             @RequestParam(required = false) String addr1,
                                             @RequestParam(required = false) String addr2,
                                             @RequestParam(required = false) String memo) {
        adminOrderService.updateDeliveryInvoice(orderNo, courier, invoiceNo, receiverName, receiverHp, zip, addr1, addr2, memo);
        return Map.of("status", "success", "message", "배송 정보가 등록되었습니다.");
    }

    @GetMapping("/delivery")
    public String delivery(Model model,
                           @RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "searchType", required = false) String searchType,
                           @RequestParam(value = "keyword", required = false) String keyword) {

        Map<String, Object> result = deliveryService.getDeliveryList(page, searchType, keyword);
        int totalPages = (int) result.get("totalPages");

        int pageSize = 5;
        int currentGroup = (int) Math.ceil((double) page / pageSize);
        int startPage = (currentGroup - 1) * pageSize + 1;
        int endPage = Math.min(startPage + pageSize - 1, totalPages);
        if (startPage < 1) startPage = 1;

        model.addAttribute("deliveries", result.get("list"));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "admin/order/delivery";
    }

    @PostMapping("/delivery/update-invoice")
    @ResponseBody
    public Map<String, Object> updateDeliveryInvoiceByDeliveryNo(
            @RequestParam Integer deliveryNo,
            @RequestParam String courier,
            @RequestParam String invoiceNo,
            @RequestParam(required = false) String memo
    ) {
        deliveryService.updateInvoiceByDeliveryNo(
                deliveryNo,
                courier,
                invoiceNo,
                memo
        );

        return Map.of(
                "status", "success",
                "message", "배송 정보가 등록되었습니다."
        );
    }

    @GetMapping("/delivery/list")
    @ResponseBody
    public List<DeliveryDetailDTO> getDeliveryListByOrderNo(@RequestParam Long orderNo) {
        return deliveryService.getDeliveryDetailsByOrderNo(orderNo);
    }


    @GetMapping("/delivery/detail")
    @ResponseBody
    public DeliveryDetailDTO getDeliveryDetail(@RequestParam("deliveryNo") int deliveryNo) {
        return deliveryService.getDeliveryDetail(deliveryNo);
    }
}