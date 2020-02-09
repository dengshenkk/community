package ml.dengshen.community.community.controller;

import ml.dengshen.community.community.dto.CommentDTO;
import ml.dengshen.community.community.dto.ResultDTO;
import ml.dengshen.community.community.enums.CommentTypeEnum;
import ml.dengshen.community.community.mapper.CommentMapper;
import ml.dengshen.community.community.model.Comment;
import ml.dengshen.community.community.model.User;
import ml.dengshen.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            return ResultDTO.errorOf("登录失效, 请重新登录", 500);
        }
        if (StringUtils.isBlank(commentDTO.getContent())) {
            throw new RuntimeException("评论内容不能为空");
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
        return ResultDTO.success("操作成功", 200, comment);
    }

    @GetMapping("/comment/{parentId}")
    @ResponseBody
    public ResultDTO getCommentList(@PathVariable("parentId") Long parentId) {
        List<CommentDTO> commentList = commentService.listByTargetId(parentId, CommentTypeEnum.COMMENT);
        return ResultDTO.success(commentList);
    }

}
