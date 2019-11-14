package news.blog.com.service;

import news.blog.com.model.ArticleEntity;
import news.blog.com.model.dto.ArticleDto;

public interface ArticleService
{
    Iterable<ArticleEntity> getArticles();

    ArticleDto getArticleDto(Long id);

    void saveArticle(ArticleDto articleDto);
}
