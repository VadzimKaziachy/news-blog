package news.blog.com.service;

import news.blog.com.service.dto.ArticleDto;

import java.util.Collection;

public interface ArticleService
{
    Collection<ArticleDto> getArticles();

    Collection<ArticleDto> getArticlesByUser();

    ArticleDto getArticle(Long id);

    void saveArticle(ArticleDto articleDto);
}
