package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.PageDTO;
import ml.dengshen.community.community.mapper.UserMapper;
import ml.dengshen.community.community.model.Question;
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
                        @RequestParam(value = "size", defaultValue = "5") Integer size,
                        @RequestParam(value = "search", required = false) String search) {

        PageDTO questionPageDTO = questionService.list(search, page, size);
        List<Question> questionHotList = questionService.hotList();
        model.addAttribute("questionPageDTO", questionPageDTO);
        model.addAttribute("questionHotList", questionHotList);
        model.addAttribute("search", search);
        return "index";
    }

}
