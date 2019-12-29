package news.blog.com.controller;

import lombok.RequiredArgsConstructor;
import news.blog.com.service.CommentService;
import news.blog.com.service.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController
{
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity getCommentsByArticle(@RequestParam Long id)
    {
        return ResponseEntity.ok(commentService.getCommentsByArticle(id));
    }

    @PostMapping
    public ResponseEntity saveCommentsByArticle(@RequestBody CommentDto commentDto, @RequestParam Long id)
    {
        commentService.saveCommentsByArticle(commentDto, id);
        return ResponseEntity.ok().build();
    }
}
