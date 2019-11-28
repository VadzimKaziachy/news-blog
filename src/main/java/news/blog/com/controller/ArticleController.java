package news.blog.com.controller;

import lombok.RequiredArgsConstructor;

import news.blog.com.service.ArticleService;
import news.blog.com.service.dto.ArticleDto;

import news.blog.com.service.dto.response.ArticleTagsResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Collection;


@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController
{
    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<Collection<ArticleDto>> getArticles(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok(articleService.getArticles(pageable));
    }

    @PostMapping
    public ResponseEntity saveArticle(@RequestBody ArticleDto articleDto)
    {
        articleService.saveArticle(articleDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/article")
    public ResponseEntity<ArticleDto> getArticle(@RequestParam Long id)
    {
        return ResponseEntity.ok(articleService.getArticle(id));
    }

    @GetMapping("/tags")
    public ResponseEntity<Collection<ArticleTagsResponseDto>> getTags()
    {
        return ResponseEntity.ok(articleService.getArticleTags());
    }

    @GetMapping("/tag")
    public ResponseEntity<Collection<ArticleDto>> getArticleByTag(@RequestParam String tag)
    {
        return ResponseEntity.ok(articleService.getArticleByTag(tag));
    }
}
