package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.mapper.CommentMapper;
import ml.dengshen.community.community.model.Comment;
import ml.dengshen.community.community.model.Notify;
import ml.dengshen.community.community.model.User;
import ml.dengshen.community.community.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/notify/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable("id") Long id) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        Notify notify = notifyService.read(id, user);
        if (notify.getType() == 1) {
            // 问题
            return "redirect:/question/" + notify.getOuterId();
        } else {
            // 评论
            Comment comment = commentMapper.selectByPrimaryKey(notify.getOuterId());
            return "redirect:/question/" + comment.getParentId() + "#comment_" + comment.getId();
        }
    }
}
