package news.blog.com.controller;

import lombok.RequiredArgsConstructor;

import news.blog.com.model.dto.ArticleDto;
import news.blog.com.service.impl.ArticleServiceImpl;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ArticleController
{
    private final ArticleServiceImpl articleService;

    @GetMapping
    public String getArticles(Model model)
    {
        model.addAttribute("articles", articleService.getArticles());
        return "articles";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable Long id, Model model)
    {
        model.addAttribute("article", articleService.getArticle(id));
        return "article";
    }

    @GetMapping("/add_article")
    public String saveArticle()
    {
        return "add_article";
    }

    @PostMapping("/add_article")
    public String saveArticle(ArticleDto articleDto)
    {
        articleService.saveArticle(articleDto);
        return "redirect:/";
    }
}
