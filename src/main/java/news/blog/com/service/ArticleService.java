package news.blog.com.service;

import news.blog.com.service.dto.ArticleDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ArticleService
{
    Collection<ArticleDto> getArticles(Pageable pageable);

    Collection<ArticleDto> getArticlesByUser(Pageable pageable);

    ArticleDto getArticle(Long id);

    void saveArticle(ArticleDto articleDto);
}
