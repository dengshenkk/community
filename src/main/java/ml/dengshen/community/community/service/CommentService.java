package ml.dengshen.community.community.service;

import ml.dengshen.community.community.mapper.CommentMapper;
import ml.dengshen.community.community.mapper.QuestionMapper;
import ml.dengshen.community.community.model.Comment;
import ml.dengshen.community.community.model.Question;
import ml.dengshen.community.community.model.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    public void insert(Comment comment) {

        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new RuntimeException("该问题不存在,不能进行评论");
        }
        Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
        if (question == null) {
            throw new RuntimeException("该问题错误,不能进行评论");
        }
        commentMapper.insert(comment);
        question.setCommentCount(question.getCommentCount() + 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andIdEqualTo(comment.getParentId());
        questionMapper.updateByExampleSelective(question, questionExample);
    }
}
