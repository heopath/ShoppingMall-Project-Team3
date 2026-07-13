package kr.co.springkmarketapp.controller.policy;

import kr.co.springkmarketapp.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PolicyController {

    private final CategoryService categoryService;

    private void addCategoryModel(Model model) {
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
    }

    @GetMapping("/policy")
    public String index() {
        return "redirect:/policy/buyer";
    }

    @GetMapping("/policy/buyer")
    public String buyer(Model model) {
        addCategoryModel(model);
        return "policy/buyer";
    }

    @GetMapping("/policy/finance")
    public String finance(Model model) {
        addCategoryModel(model);
        return "policy/finance";
    }

    @GetMapping("/policy/location")
    public String location(Model model) {
        addCategoryModel(model);
        return "policy/location";
    }

    @GetMapping("/policy/privacy")
    public String privacy(Model model) {
        addCategoryModel(model);
        return "policy/privacy";
    }

    @GetMapping("/policy/seller")
    public String seller(Model model) {
        addCategoryModel(model);
        return "policy/seller";
    }
}
