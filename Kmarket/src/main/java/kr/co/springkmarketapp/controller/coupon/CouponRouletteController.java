package kr.co.springkmarketapp.controller.coupon;

import kr.co.springkmarketapp.config.MyUserDetails;
import kr.co.springkmarketapp.service.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/coupon/roulette")
@RequiredArgsConstructor
public class CouponRouletteController {

    private final CouponService couponService;

    @GetMapping("/prizes")
    public Map<String, Object> prizes(
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {
        Integer memberNo = userDetails == null
                ? null
                : userDetails.getMember().getMemberNo();

        List<Map<String, Object>> prizes =
                couponService.getRoulettePrizes(memberNo);

        return Map.of("result", true, "prizes", prizes);
    }

    @PostMapping("/spin")
    public ResponseEntity<Map<String, Object>> spin(
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("result", false, "message", "로그인이 필요합니다.")
            );
        }

        try {
            Map<String, Object> prize = couponService.spinRoulette(
                    userDetails.getMember().getMemberNo()
            );
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("result", true);
            response.put("message", "쿠폰이 발급되었습니다.");
            response.putAll(prize);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("result", false, "message", e.getMessage())
            );
        }
    }
}
