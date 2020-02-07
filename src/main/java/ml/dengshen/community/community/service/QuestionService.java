package ml.dengshen.community.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ml.dengshen.community.community.dto.QuestionDTO;
import ml.dengshen.community.community.mapper.QuestionMapper;
import ml.dengshen.community.community.mapper.UserMapper;
import ml.dengshen.community.community.model.Question;
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
    private UserMapper userMapper;


    public List<QuestionDTO> list(Integer page, Integer size) {
        int offset = size * (page - 1);
        PageHelper.offsetPage(offset, size);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PageInfo pageInfo = new PageInfo<>(questions);
        System.out.println("总数：" + pageInfo.getTotal());
        return questionDTOList;
    }

    public List<QuestionDTO> listById(Integer id, Integer page, Integer size) {
        int offset = size * (page - 1);
        PageHelper.offsetPage(offset, size);
        List<Question> questions = questionMapper.listById(id, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PageInfo pageInfo = new PageInfo<>(questions);
        System.out.println("总数1：" + pageInfo.getTotal());
        return questionDTOList;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }
}
