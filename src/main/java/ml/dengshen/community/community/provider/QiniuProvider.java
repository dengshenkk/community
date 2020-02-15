package ml.dengshen.community.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class QiniuProvider {


    @Value("${qiniu.oss.accessKey}")
    String accessKey;
    @Value("${qiniu.oss.secretKey}")
    String secretKey;

    @Value("${qiniu.oss.bucketName}")
    String bucket;

    @Value("${qiniu.oss.domain}")
    String domain;


    public String upload(InputStream inputStream) throws IOException {
        String localFilePath = "/images/64.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        Response response = null;
        try {
            response = uploadManager.put(inputStream, null, upToken, null, null);
            //解析上传成功的结果
            JSONObject jsonObject = JSON.parseObject(response.bodyString());
            return this.domain + jsonObject.getString("key");
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return "";
    }
}
