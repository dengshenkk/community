package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.FileDTO;
import ml.dengshen.community.community.provider.QiniuProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private QiniuProvider qiniuProvider;


    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO uploadFile(HttpServletRequest request) {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        MultipartFile file1 = multipartHttpServletRequest.getFile("editor-image");
        String res = null;
        try {
            assert file != null;
//            qiniuProvider.upload(file1.getInputStream());
            res = qiniuProvider.upload(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setMessage("成功");
        fileDTO.setSuccess(1);
        if (res == null) {
            fileDTO.setMessage("失败");
            fileDTO.setSuccess(0);
        }
        fileDTO.setUrl(res);
        return fileDTO;
    }

}
