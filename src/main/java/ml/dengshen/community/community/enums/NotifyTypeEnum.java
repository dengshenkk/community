package ml.dengshen.community.community.enums;

import lombok.Getter;

@Getter
public enum NotifyTypeEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");
    private Integer type;
    private String name;


    NotifyTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String typeOf(Integer type) {
        for (NotifyTypeEnum typeEnum : NotifyTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum.getName();
            }
        }
        return "";
    }
}
