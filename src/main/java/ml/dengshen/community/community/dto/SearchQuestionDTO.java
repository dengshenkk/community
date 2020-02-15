package ml.dengshen.community.community.dto;

import lombok.Data;

@Data
public class SearchQuestionDTO {
    private String search;
    private Integer page;
    private Integer size;
}
