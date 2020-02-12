package ml.dengshen.community.community.dto;

import lombok.Data;
import ml.dengshen.community.community.model.User;

@Data
public class NotifyDTO {
    private Long id;
    private Long grmCreate;
    private Integer status;
    private User user;
    private Long outerId;
    private String outerTitle;
    private String type;
}
