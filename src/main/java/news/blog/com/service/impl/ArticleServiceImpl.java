package news.blog.com.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import news.blog.com.model.ArticleEntity;
import news.blog.com.model.dto.ArticleDto;
import news.blog.com.service.ArticleService;
import news.blog.com.repository.ArticleRepository;

import com.tabasoft.converter.api.ExtendedConversionService;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService
{
    private final ArticleRepository articleRepository;
    private final ExtendedConversionService conversionService;

    @Override
    public Iterable<ArticleEntity> getArticles()
    {
        return articleRepository.findAll();
    }

    @Override
    public ArticleDto getArticleDto(Long id)
    {
        return conversionService.convert(articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Article not found " + id)), ArticleDto.class);
    }

    @Override
    public void saveArticle(ArticleDto articleDto)
    {
        articleRepository.save(ArticleEntity.builder()
                                            .tags(articleDto.getTags())
                                            .title(articleDto.getTitle())
                                            .imageName(articleDto.getImageName())
                                            .fullDescription(articleDto.getFullDescription())
                                            .shortDescription(articleDto.getShortDescription())
                                            .build()
        );
    }
}
