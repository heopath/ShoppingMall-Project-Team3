package kr.co.springkmarketapp.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequestDTO {

    private List<Integer> cartNos;

    private String receiverName;
    private String receiverHp;
    private String zip;
    private String addr1;
    private String addr2;
    private String deliveryRequest;

    private String paymentMethod;

    private Long couponIssueNo;
    private Integer usedPoint;
}