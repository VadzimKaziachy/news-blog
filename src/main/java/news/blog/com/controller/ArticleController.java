package news.blog.com.controller;

import news.blog.com.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ArticleController
{
    @Autowired
    private ArticleServiceImpl articleService;

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
    public String saveArticle(
            @RequestParam String tags,
            @RequestParam String title,
            @RequestParam String imageName,
            @RequestParam String fullDescription,
            @RequestParam String shortDescription)
    {
        articleService.saveArticle(tags, title, imageName, fullDescription, shortDescription);
        return "redirect:/";
    }
}
