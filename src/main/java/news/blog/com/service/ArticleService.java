package news.blog.com.service;

import news.blog.com.service.dto.ArticleDto;
import news.blog.com.service.dto.response.ArticleTagsResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface ArticleService
{
    Collection<ArticleDto> getArticles(Pageable pageable);

    Collection<ArticleDto> getArticlesByUser(Pageable pageable);

    Collection<ArticleTagsResponseDto> getArticleTags();

    Collection<ArticleDto> getArticleByTag(String tag);

    ArticleDto getArticle(Long id);

    void saveArticle(ArticleDto articleDto);
}
