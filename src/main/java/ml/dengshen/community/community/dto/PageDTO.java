package ml.dengshen.community.community.dto;

import lombok.Data;

@Data
public class PageDTO<T> {
    private Integer page;
    private Integer size;
    private Integer totalPage;
    private T data;
}
