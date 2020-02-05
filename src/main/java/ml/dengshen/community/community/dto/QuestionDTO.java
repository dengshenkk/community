package ml.dengshen.community.community.dto;

import lombok.Data;
import ml.dengshen.community.community.model.Question;
import ml.dengshen.community.community.model.User;

@Data
public class QuestionDTO extends Question {
    private User user;
}
