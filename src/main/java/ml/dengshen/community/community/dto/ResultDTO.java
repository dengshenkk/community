package ml.dengshen.community.community.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private String message;
    private Integer code;

    public static ResultDTO errorOf(String message, Integer code) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }
}
