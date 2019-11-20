package news.blog.com.controller;

import lombok.RequiredArgsConstructor;

import news.blog.com.service.dto.ArticleDto;
import news.blog.com.service.impl.ArticleServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Collection;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ArticleController
{
    private final ArticleServiceImpl articleService;

    @GetMapping
    public ResponseEntity<Collection<ArticleDto>> getArticles()
    {
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable Long id)
    {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @PostMapping("/add_article")
    public ResponseEntity saveArticle(@RequestBody ArticleDto articleDto)
    {
        articleService.saveArticle(articleDto);
        return ResponseEntity.ok().build();
    }
}
