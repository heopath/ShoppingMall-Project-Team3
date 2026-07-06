package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.admin.AdminMainDTO;
import kr.co.springkmarketapp.service.admin.AdminMainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminMainController {

    private final AdminMainService adminMainService;

    @GetMapping("/admin")
    public String index(Model model) {

        AdminMainDTO dashboard = adminMainService.getDashboardData();

        model.addAttribute("dashboard", dashboard);
        model.addAttribute("chartData", dashboard.getBarChartData());
        model.addAttribute("noticeList", dashboard.getRecentNotices());
        model.addAttribute("qnaList", dashboard.getRecentQnas());

        // pie chart 색상 순서 (범례 순서와 매칭)
        List<String> pieColors = List.of("blue", "red", "green", "purple");
        model.addAttribute("pieChartData", dashboard.getPieChartData());
        model.addAttribute("pieColors", pieColors);

        // conic-gradient 문자열 미리 계산: "blue-hex 0% 58%, red-hex 58% 80%, ..."
        model.addAttribute("pieChartGradient", buildPieGradient(dashboard.getPieChartData(), pieColors));

        return "admin/index";
    }

    private String buildPieGradient(Map<String, Double> pieChartData, List<String> colorNames) {
        Map<String, String> colorHex = Map.of(
                "blue", "#5d86c6",
                "red", "#c65a5a",
                "green", "#9dbd5a",
                "purple", "#7f6ab3"
        );

        StringBuilder sb = new StringBuilder();
        double cumulative = 0;
        int i = 0;
        for (Map.Entry<String, Double> entry : pieChartData.entrySet()) {
            double start = cumulative;
            cumulative += entry.getValue();
            String hex = colorHex.get(colorNames.get(i % colorNames.size()));
            sb.append(hex).append(" ").append(start).append("% ").append(cumulative).append("%");
            if (i < pieChartData.size() - 1) sb.append(", ");
            i++;
        }
        return sb.toString();
    }
}