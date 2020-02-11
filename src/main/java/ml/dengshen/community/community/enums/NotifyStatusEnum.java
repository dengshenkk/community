package ml.dengshen.community.community.enums;

import lombok.Getter;

@Getter
public enum NotifyStatusEnum {
    UNREAD(0), READ(1);
    private Integer status;


    NotifyStatusEnum(Integer status) {
        this.status = status;
    }
}
