package ml.dengshen.community.community.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GithubUser {
    private long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
