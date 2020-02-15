package ml.dengshen.community.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ml.dengshen.community.community.dto.PageDTO;
import ml.dengshen.community.community.dto.QuestionDTO;
import ml.dengshen.community.community.mapper.QuestionExtMapper;
import ml.dengshen.community.community.mapper.QuestionMapper;
import ml.dengshen.community.community.mapper.UserMapper;
import ml.dengshen.community.community.model.Question;
import ml.dengshen.community.community.model.QuestionExample;
import ml.dengshen.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;


    public PageDTO list(String search, Integer page, Integer size) {
        int offset = size * (page - 1);
        PageHelper.offsetPage(offset, size);
        System.out.println("page: " + offset);
        System.out.println("size: " + size);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionExtMapper.selectSearch(search, page, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PageInfo pageInfo = new PageInfo<>(questions);
        System.out.println("总数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        PageDTO<Object> pageDTO = new PageDTO<>();
        pageDTO.setData(questionDTOList);
        pageDTO.setPage(page);
        pageDTO.setSize(size);
        pageDTO.setTotalPage(pageInfo.getPages());
        return pageDTO;
    }

    public List<QuestionDTO> listById(Long id, Integer page, Integer size) {
        int offset = size * (page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(id);
        PageHelper.offsetPage(offset, size);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PageInfo pageInfo = new PageInfo<>(questions);
        System.out.println("总数1：" + pageInfo.getTotal());
        return questionDTOList;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        // TODO: 发布问题后没有默认值 likeCount viewCount...
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            question.setViewCount(1);
            questionMapper.insertSelective(question);
        } else {
            question.setGmtModify(System.currentTimeMillis());
            Question question1 = new Question();
            question1.setId(question.getId());
            question1.setTitle(question.getTitle());
            question1.setDescription(question.getDescription());
            question1.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(question1, questionExample);
//            questionMapper.updateByExample(questionExample);
        }
    }

    public void incView(Long id) {
        Question questionDB = questionMapper.selectByPrimaryKey(id);
        if (questionDB != null) {
            Question updateQuestion = new Question();
            updateQuestion.setViewCount(questionDB.getViewCount() + 1);
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(id);
            questionMapper.updateByExampleSelective(updateQuestion, questionExample);
        }
    }

    public List<Question> selectRelate(QuestionDTO questionDTO) {
        QuestionDTO questionDTO1 = new QuestionDTO();
        BeanUtils.copyProperties(questionDTO, questionDTO1);
        QuestionExample questionExample = new QuestionExample();
        String tags = questionDTO1.getTag().replace(",", "|");
        questionDTO1.setTag(tags);
        questionExample.createCriteria();
        List<Question> questionList = questionExtMapper.selectRelateQuestion(questionDTO1);
        return questionList;
    }

    public List<Question> hotList() {
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("comment_count desc limit 0, 5");
        List<Question> questionList = questionMapper.selectByExample(questionExample);
        return questionList;
    }
}
