package kr.co.springkmarketapp.dao.cs;

import kr.co.springkmarketapp.dto.cs.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDAO {
    int insertNotice(NoticeDTO noticeDTO);
    NoticeDTO selectNotice(Integer noticeNo);
    List<NoticeDTO> selectNoticeList();
    int updateNotice(NoticeDTO noticeDTO);
    int deleteNotice(Integer noticeNo);
}
