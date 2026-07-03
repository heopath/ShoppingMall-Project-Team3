package kr.co.springkmarketapp.service.policy;

import kr.co.springkmarketapp.dao.policy.PolicyDAO;
import kr.co.springkmarketapp.dto.policy.PolicyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyDAO policyDAO;

    public int insertPolicy(PolicyDTO policyDTO) {
        return policyDAO.insertPolicy(policyDTO);
    }

    public PolicyDTO selectPolicy(Integer policyNo) {
        return policyDAO.selectPolicy(policyNo);
    }

    public List<PolicyDTO> selectPolicyList() {
        return policyDAO.selectPolicyList();
    }

    public int updatePolicy(PolicyDTO policyDTO) {
        return policyDAO.updatePolicy(policyDTO);
    }

    public int deletePolicy(Integer policyNo) {
        return policyDAO.deletePolicy(policyNo);
    }
}
