package news.blog.com.service;

import news.blog.com.service.dto.ArticleDto;

public interface ArticleService
{
    Iterable<ArticleDto> getArticles();

    ArticleDto getArticle(Long id);

    void saveArticle(ArticleDto articleDto);
}
