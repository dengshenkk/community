package ml.dengshen.community.community.service;

import ml.dengshen.community.community.dto.CommentDTO;
import ml.dengshen.community.community.enums.CommentTypeEnum;
import ml.dengshen.community.community.enums.NotifyStatusEnum;
import ml.dengshen.community.community.enums.NotifyTypeEnum;
import ml.dengshen.community.community.mapper.CommentMapper;
import ml.dengshen.community.community.mapper.NotifyMapper;
import ml.dengshen.community.community.mapper.QuestionMapper;
import ml.dengshen.community.community.mapper.UserMapper;
import ml.dengshen.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private NotifyMapper notifyMapper;

    @Transactional
    public void insert(Comment comment) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new RuntimeException("该问题不存在,不能进行评论");
        }
        if (comment.getType() == CommentTypeEnum.QUESTION.getType()) {
            // 回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new RuntimeException("该问题错误,不能进行评论");
            }
            question.setCommentCount(question.getCommentCount() + 1);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(comment.getParentId());
            questionMapper.updateByExampleSelective(question, questionExample);
            createNotify(comment, question.getCreator(), NotifyTypeEnum.REPLY_QUESTION);
        } else if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment commentDB = commentMapper.selectByPrimaryKey(comment.getParentId());
            createNotify(comment, commentDB.getCommentator(), NotifyTypeEnum.REPLY_COMMENT);

        }

        commentMapper.insert(comment);

    }

    private void createNotify(Comment comment, Long commentator, NotifyTypeEnum notifyTypeEnum) {
        // 通知
        Notify notify = new Notify();
        notify.setGmtCreate(System.currentTimeMillis());
        // 回复的对象id
        notify.setOuterId(comment.getParentId());
        // 评论者
        notify.setNotifier(comment.getCommentator());

        notify.setStatus(NotifyStatusEnum.UNREAD.getStatus());

        notify.setType(notifyTypeEnum.getType());

        // 通知父评论的创建人
        notify.setReceiver(commentator);

        notifyMapper.insert(notify);
    }

    public List<CommentDTO> listByTargetId(Long parentId, CommentTypeEnum commentTypeEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(parentId)
                .andTypeEqualTo(commentTypeEnum.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        List<User> users;
        List<CommentDTO> commentDTOList = null;
        if (comments.size() > 0) {
            Set<Long> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
            ArrayList<Long> userIds = new ArrayList<>();
            userIds.addAll(commentators);

            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdIn(userIds);
            users = userMapper.selectByExample(userExample);

            Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

            commentDTOList = comments.stream().map(comment -> {
                CommentDTO commentDTO = new CommentDTO();
                BeanUtils.copyProperties(comment, commentDTO);
                commentDTO.setUser(userMap.get(comment.getCommentator()));
                return commentDTO;
            }).collect(Collectors.toList());
        }
        return commentDTOList;
    }
}
