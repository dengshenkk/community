package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.QuestionDTO;
import ml.dengshen.community.community.mapper.UserMapper;
import ml.dengshen.community.community.model.User;
import ml.dengshen.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(value = "action") String action,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "5") Integer size) {

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我关注的问题");
        }
        if ("record".equals(action)) {
            model.addAttribute("section", "record");
            model.addAttribute("sectionName", "我的提问");
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }
        List<QuestionDTO> questionDTOList = questionService.listById(user.getId(), page, size);
        model.addAttribute("questions", questionDTOList);
        return "profile";
    }
}
