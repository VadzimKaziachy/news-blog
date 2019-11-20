package news.blog.com.service.impl;

import news.blog.com.exception.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import news.blog.com.model.ArticleEntity;
import news.blog.com.service.dto.ArticleDto;
import news.blog.com.service.ArticleService;
import news.blog.com.repository.ArticleRepository;

import com.tabasoft.converter.api.ExtendedConversionService;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService
{
    private final ArticleRepository articleRepository;
    private final ExtendedConversionService conversionService;

    @Override
    public Collection<ArticleDto> getArticles()
    {
        return conversionService.convertMany((Collection) articleRepository.findAll(), ArticleDto.class);
    }

    @Override
    public ArticleDto getArticle(Long id)
    {
        return conversionService.convert(articleRepository.findById(id).orElseThrow(()-> new NotFoundException("Article not found")), ArticleDto.class);
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
