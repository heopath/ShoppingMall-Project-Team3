package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.ClaimFileDAO;
import kr.co.springkmarketapp.dto.order.ClaimFileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimFileService {

    private final ClaimFileDAO claimFileDAO;

    public int insertClaimFile(ClaimFileDTO claimFileDTO) {
        return claimFileDAO.insertClaimFile(claimFileDTO);
    }

    public ClaimFileDTO selectClaimFile(Long claimFileNo) {
        return claimFileDAO.selectClaimFile(claimFileNo);
    }

    public List<ClaimFileDTO> selectClaimFileList() {
        return claimFileDAO.selectClaimFileList();
    }

    public int updateClaimFile(ClaimFileDTO claimFileDTO) {
        return claimFileDAO.updateClaimFile(claimFileDTO);
    }

    public int deleteClaimFile(Long claimFileNo) {
        return claimFileDAO.deleteClaimFile(claimFileNo);
    }
}
