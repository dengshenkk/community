package ml.dengshen.community.community.service;

import ml.dengshen.community.community.dto.CommentDTO;
import ml.dengshen.community.community.enums.CommentTypeEnum;
import ml.dengshen.community.community.mapper.CommentMapper;
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
        } else if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            CommentExample commentExample = new CommentExample();
            commentExample.createCriteria()
                    .andParentIdEqualTo(comment.getParentId());
            List<Comment> commentList = commentMapper.selectByExample(commentExample);
        }

        commentMapper.insert(comment);

    }

    public List<CommentDTO> listByTargetId(Long parentId, CommentTypeEnum commentTypeEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(parentId)
                .andTypeEqualTo(commentTypeEnum.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        Set<Long> commentators = comments.stream().map(Comment::getCommentator).collect(Collectors.toSet());
        ArrayList<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        System.out.println(users);
        return commentDTOList;
    }
}
