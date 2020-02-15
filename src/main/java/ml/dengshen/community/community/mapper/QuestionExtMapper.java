package ml.dengshen.community.community.mapper;

import ml.dengshen.community.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {

    List<Question> selectRelateQuestion(Question question);

    List<Question> selectSearch(String search, Integer page, Integer size);
}
