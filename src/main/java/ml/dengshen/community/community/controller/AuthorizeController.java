package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.AccessTokenDTO;
import ml.dengshen.community.community.dto.GithubUser;
import ml.dengshen.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.redirectURI}")
    private String redirectURI;
    @Value("${github.client.secret}")
    private String secret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setRedirct_uri(redirectURI);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println(accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        if (user != null) {
            request.getSession().setAttribute("user",user);
            // 登录成功
            return "redirect:/";
        } else {
            // 登录失败
            return "redirect:/";
        }
    }
}
