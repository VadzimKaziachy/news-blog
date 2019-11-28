package news.blog.com.service.impl;

import news.blog.com.exception.NotFoundException;
import news.blog.com.model.UserEntity;
import news.blog.com.service.dto.response.ArticleTagsResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import news.blog.com.model.ArticleEntity;
import news.blog.com.service.dto.ArticleDto;
import news.blog.com.service.ArticleService;
import news.blog.com.repository.ArticleRepository;

import com.tabasoft.converter.api.ExtendedConversionService;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService
{
    private final ArticleRepository articleRepository;
    private final SecurityServiceImpl securityService;
    private final ExtendedConversionService conversionService;

    @Override
    public Collection<ArticleDto> getArticles(Pageable pageable)
    {
        return conversionService.convertMany(articleRepository.findAll(pageable).getContent(), ArticleDto.class);
    }

    @Override
    public Collection<ArticleDto> getArticlesByUser(Pageable pageable)
    {
        UserEntity user = securityService.getUserEntity();
        return conversionService.convertMany(articleRepository.findArticleEntitiesByUser(user, pageable).getContent(), ArticleDto.class);
    }

    @Override
    public Collection<ArticleTagsResponseDto> getArticleTags() {
        Collection<ArticleTagsResponseDto> articleTags = new ArrayList<>();

        Set<String> tags = articleRepository.findAllTags();
        tags.forEach(tag -> {
            articleTags.add(ArticleTagsResponseDto.builder()
                                                  .tag(tag)
                                                  .quantity(articleRepository.findAmountArticleByTag(tag))
                                                  .build()
            );
        });

        return articleTags;
    }

    @Override
    public Collection<ArticleDto> getArticleByTag(String tag) {
        return conversionService.convertMany(articleRepository.findByTag(tag), ArticleDto.class);
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
                                            .tag(articleDto.getTag())
                                            .title(articleDto.getTitle())
                                            .imageName(articleDto.getImageName())
                                            .fullDescription(articleDto.getFullDescription())
                                            .shortDescription(articleDto.getShortDescription())
                                            .user(securityService.getUserEntity())
                                            .build()
        );
    }
}
