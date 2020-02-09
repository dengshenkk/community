package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.CommentDTO;
import ml.dengshen.community.community.dto.ResultDTO;
import ml.dengshen.community.community.mapper.CommentMapper;
import ml.dengshen.community.community.model.Comment;
import ml.dengshen.community.community.model.User;
import ml.dengshen.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf("未登录", 2000);
        }
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(1);
        commentService.insert(comment);
        return null;
    }
}
