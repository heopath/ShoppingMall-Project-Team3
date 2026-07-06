package kr.co.springkmarketapp.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCheckDTO {

    private String type;
    private String value;
}