package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.ClaimFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClaimFileDAO {
    int insertClaimFile(ClaimFileDTO claimFileDTO);
    ClaimFileDTO selectClaimFile(Long claimFileNo);
    List<ClaimFileDTO> selectClaimFileList();
    int updateClaimFile(ClaimFileDTO claimFileDTO);
    int deleteClaimFile(Long claimFileNo);
}
