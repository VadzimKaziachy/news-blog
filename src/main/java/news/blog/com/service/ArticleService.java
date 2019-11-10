package news.blog.com.service;

import news.blog.com.model.ArticleEntity;

public interface ArticleService
{
    Iterable<ArticleEntity> getArticles();

    ArticleEntity getArticle(Long id);

    void saveArticle(String tags, String title, String imageName, String fullDescription, String shortDescription);
}
