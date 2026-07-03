package kr.co.springkmarketapp.dao.policy;

import kr.co.springkmarketapp.dto.policy.PolicyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PolicyDAO {
    int insertPolicy(PolicyDTO policyDTO);
    PolicyDTO selectPolicy(Integer policyNo);
    List<PolicyDTO> selectPolicyList();
    int updatePolicy(PolicyDTO policyDTO);
    int deletePolicy(Integer policyNo);
}
