package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.CommentDTO;
import ml.dengshen.community.community.dto.QuestionDTO;
import ml.dengshen.community.community.enums.CommentTypeEnum;
import ml.dengshen.community.community.service.CommentService;
import ml.dengshen.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        questionService.incView(id);
        List<CommentDTO> commentList = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", commentList);
        return "question";

    }
}
