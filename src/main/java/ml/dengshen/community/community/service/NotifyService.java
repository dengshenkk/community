package ml.dengshen.community.community.service;

import com.github.pagehelper.PageHelper;
import ml.dengshen.community.community.dto.NotifyDTO;
import ml.dengshen.community.community.enums.NotifyTypeEnum;
import ml.dengshen.community.community.mapper.CommentMapper;
import ml.dengshen.community.community.mapper.NotifyMapper;
import ml.dengshen.community.community.mapper.QuestionMapper;
import ml.dengshen.community.community.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotifyService {

    @Autowired
    private NotifyMapper notifyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private CommentMapper commentMapper;


    public List<NotifyDTO> list(User user, Integer page, Integer size) {

        int offset = size * (page - 1);
        PageHelper.startPage(offset, size);
        NotifyExample notifyExample = new NotifyExample();
        notifyExample.createCriteria()
                .andReceiverEqualTo(user.getId());
        List<Notify> notifyList = notifyMapper.selectByExample(notifyExample);

        List<NotifyDTO> notifyDTOList = new ArrayList<>();
        for (Notify notify : notifyList) {
            NotifyDTO notifyDTO = new NotifyDTO();
            notifyDTO.setGrmCreate(notify.getGmtCreate());
            notifyDTO.setId(notify.getId());
            if (notify.getType() == NotifyTypeEnum.REPLY_QUESTION.getType()) {
                Question question = questionMapper.selectByPrimaryKey(notify.getOuterid());
                notifyDTO.setOuterTitle(question.getTitle());
            } else if (notify.getType() == NotifyTypeEnum.REPLY_COMMENT.getType()) {
                Comment comment = commentMapper.selectByPrimaryKey(notify.getOuterid());
                notifyDTO.setOuterTitle(comment.getContent());
            }
            notifyDTO.setStatus(notify.getStatus());
            notifyDTO.setType(NotifyTypeEnum.typeOf(notify.getType()));
            notifyDTO.setUser(user);
            notifyDTOList.add(notifyDTO);
        }
        return notifyDTOList;

    }
}
