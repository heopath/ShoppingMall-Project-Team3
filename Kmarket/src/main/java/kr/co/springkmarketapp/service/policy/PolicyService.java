package kr.co.springkmarketapp.service.policy;

import kr.co.springkmarketapp.dao.policy.PolicyDAO;
import kr.co.springkmarketapp.dto.policy.PolicyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyDAO policyDAO;

    private static final Map<String, PolicyDTO> DEFAULT_POLICIES = new LinkedHashMap<>();

    static {
        DEFAULT_POLICIES.put("buyer", PolicyDTO.builder()
                .policyType("buyer")
                .title("구매회원 약관")
                .content("제 1 조 (목적)\n이 약관은 주식회사 케이마켓쇼핑몰이 운영하는 인터넷 쇼핑몰에서 제공하는 전자상거래 관련 서비스의 이용과 관련하여 회사와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.")
                .requiredYn("Y")
                .useYn("Y")
                .build());

        DEFAULT_POLICIES.put("seller", PolicyDTO.builder()
                .policyType("seller")
                .title("판매회원 약관")
                .content("제 1 조 (목적)\n이 약관은 판매자가 케이마켓에서 상품을 등록하고 판매하는 과정에서 필요한 서비스 이용 조건, 판매자의 의무, 상품 관리 및 정산 기준을 규정함을 목적으로 합니다.")
                .requiredYn("Y")
                .useYn("Y")
                .build());

        DEFAULT_POLICIES.put("finance", PolicyDTO.builder()
                .policyType("finance")
                .title("전자금융거래 약관")
                .content("제 1 조 (목적)\n이 약관은 회사가 제공하는 전자지급결제대행 서비스 및 결제 관련 서비스의 이용 조건과 절차, 회사와 이용자 간의 권리 및 책임사항을 정함을 목적으로 합니다.")
                .requiredYn("Y")
                .useYn("Y")
                .build());

        DEFAULT_POLICIES.put("location", PolicyDTO.builder()
                .policyType("location")
                .title("위치정보 이용약관")
                .content("제 1 조 (목적)\n이 약관은 회사가 제공하는 위치정보 기반 서비스의 이용과 관련하여 회사와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.")
                .requiredYn("N")
                .useYn("Y")
                .build());

        DEFAULT_POLICIES.put("privacy", PolicyDTO.builder()
                .policyType("privacy")
                .title("개인정보처리방침")
                .content("제 1 조 (목적)\n이 개인정보처리방침은 회사가 이용자의 개인정보를 어떤 기준으로 수집, 이용, 보관 및 보호하는지 안내하는 것을 목적으로 합니다.")
                .requiredYn("Y")
                .useYn("Y")
                .build());
    }

    public int insertPolicy(PolicyDTO policyDTO) {
        return policyDAO.insertPolicy(policyDTO);
    }

    public PolicyDTO selectPolicy(Integer policyNo) {
        return policyDAO.selectPolicy(policyNo);
    }

    public PolicyDTO selectPolicyByType(String policyType) {
        return policyDAO.selectPolicyByType(policyType);
    }

    public List<PolicyDTO> selectPolicyList() {
        return policyDAO.selectPolicyList();
    }

    public List<PolicyDTO> selectAdminPolicyList() {
        List<PolicyDTO> policies = new ArrayList<>();

        for (String policyType : DEFAULT_POLICIES.keySet()) {
            PolicyDTO policy = policyDAO.selectPolicyByType(policyType);
            policies.add(policy != null ? policy : DEFAULT_POLICIES.get(policyType));
        }

        return policies;
    }

    @Transactional
    public int savePolicy(PolicyDTO policyDTO) {
        if (policyDTO.getRequiredYn() == null || policyDTO.getRequiredYn().isBlank()) {
            policyDTO.setRequiredYn("Y");
        }

        if (policyDTO.getUseYn() == null || policyDTO.getUseYn().isBlank()) {
            policyDTO.setUseYn("Y");
        }

        PolicyDTO savedPolicy = policyDAO.selectPolicyByType(policyDTO.getPolicyType());

        if (savedPolicy == null) {
            return policyDAO.insertPolicy(policyDTO);
        }

        policyDTO.setPolicyNo(savedPolicy.getPolicyNo());
        return policyDAO.updatePolicy(policyDTO);
    }

    public int updatePolicy(PolicyDTO policyDTO) {
        return policyDAO.updatePolicy(policyDTO);
    }

    public int deletePolicy(Integer policyNo) {
        return policyDAO.deletePolicy(policyNo);
    }



//    일반회원 가입 type=user
//→ buyer
//→ finance
//→ privacy
//→ location
//
//    판매회원 가입 type=seller
//→ seller
//→ finance
//→ privacy
    public List<PolicyDTO> selectSignupPolicyList(String type) {
        List<PolicyDTO> result = new ArrayList<>();

        List<String> policyTypes = new ArrayList<>();

        if ("seller".equals(type)) {
            policyTypes.add("seller");
        } else {
            policyTypes.add("buyer");
        }

        policyTypes.add("finance");
        policyTypes.add("privacy");

        if (!"seller".equals(type)) {
            policyTypes.add("location");
        }

        for (String policyType : policyTypes) {
            PolicyDTO policy = policyDAO.selectPolicyByType(policyType);

            if (policy == null) {
                policy = DEFAULT_POLICIES.get(policyType);
            }

            if (policy != null && "Y".equals(policy.getUseYn())) {
                result.add(policy);
            }
        }

        return result;
    }
}
