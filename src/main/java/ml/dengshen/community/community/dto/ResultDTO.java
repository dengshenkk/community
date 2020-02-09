package ml.dengshen.community.community.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private String message;
    private Integer code;
    private Object data;

    public static ResultDTO errorOf(String message, Integer code) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(null);
        return resultDTO;
    }

    public static ResultDTO success(String message, Integer code, Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }
    public static ResultDTO success(Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(20000);
        resultDTO.setMessage("操作成功");
        resultDTO.setData(data);
        return resultDTO;
    }
}
