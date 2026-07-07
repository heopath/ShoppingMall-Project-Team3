package kr.co.springkmarketapp.service.cs;

import kr.co.springkmarketapp.dao.cs.NoticeDAO;
import kr.co.springkmarketapp.dto.cs.NoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDAO noticeDAO;

    public int insertNotice(NoticeDTO noticeDTO) {
        return noticeDAO.insertNotice(noticeDTO);
    }

    public List<NoticeDTO> selectNoticeListByPage(int offset, int limit) {
        return noticeDAO.selectNoticeListByPage(offset, limit);
    }

    public int countNotice() {
        return noticeDAO.countNotice();
    }

    public NoticeDTO selectNotice(Integer noticeNo) {
        return noticeDAO.selectNotice(noticeNo);
    }

    public List<NoticeDTO> selectNoticeList() {
        return noticeDAO.selectNoticeList();
    }

    public int updateNotice(NoticeDTO noticeDTO) {
        return noticeDAO.updateNotice(noticeDTO);
    }

    public int deleteNotice(Integer noticeNo) {
        return noticeDAO.deleteNotice(noticeNo);
    }
}
