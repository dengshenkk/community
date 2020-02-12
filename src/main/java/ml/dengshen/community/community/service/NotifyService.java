package ml.dengshen.community.community.service;

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


    public List<NotifyDTO> list(User user) {

//        int offset = size * (page - 1);
//        PageHelper.startPage(offset, size);
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
                Question question = questionMapper.selectByPrimaryKey(notify.getOuterId());
                notifyDTO.setOuterTitle(question.getTitle());
            } else if (notify.getType() == NotifyTypeEnum.REPLY_COMMENT.getType()) {
                Comment comment = commentMapper.selectByPrimaryKey(notify.getOuterId());
                notifyDTO.setOuterTitle(comment.getContent());
            }
            notifyDTO.setStatus(notify.getStatus());
            notifyDTO.setType(NotifyTypeEnum.typeOf(notify.getType()));
            notifyDTO.setUser(user);
            notifyDTO.setOuterId(notify.getOuterId());
            notifyDTOList.add(notifyDTO);
        }
        return notifyDTOList;

    }

    public Notify read(Long id, User user) {
        Notify notify = notifyMapper.selectByPrimaryKey(id);
        if (notify.getReceiver() != user.getId()) {
            throw new RuntimeException("兄弟,你怎么能偷看别人的信息呢?");
        }
        notify.setStatus(1);
        notifyMapper.updateByPrimaryKey(notify);
        return notify;
    }

    public Long unreadCount(User user) {
        NotifyExample notifyExample = new NotifyExample();
        notifyExample.createCriteria()
                .andReceiverEqualTo(user.getId())
                .andStatusEqualTo(0);
        return notifyMapper.countByExample(notifyExample);
    }
}
