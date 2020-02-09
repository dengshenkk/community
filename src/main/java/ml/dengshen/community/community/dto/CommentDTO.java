package ml.dengshen.community.community.dto;

import lombok.Data;
import ml.dengshen.community.community.model.Comment;
import ml.dengshen.community.community.model.User;

@Data
public class CommentDTO extends Comment {
    private Long parentId;
    private String content;
    private Integer type;
    private User user;
}
