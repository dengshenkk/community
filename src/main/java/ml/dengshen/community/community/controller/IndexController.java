package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.QuestionDTO;
import ml.dengshen.community.community.mapper.UserMapper;
import ml.dengshen.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size) {
        List<QuestionDTO> questionList = questionService.list(page, size);
        model.addAttribute("questions", questionList);
        return "index";
    }

}
