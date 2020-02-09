package ml.dengshen.community.community.enums;

import lombok.Getter;

@Getter
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;



    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
