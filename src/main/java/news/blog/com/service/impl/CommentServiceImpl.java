package news.blog.com.service.impl;

import com.tabasoft.converter.api.ExtendedConversionService;
import lombok.RequiredArgsConstructor;
import news.blog.com.exception.NotFoundException;
import news.blog.com.model.ArticleEntity;
import news.blog.com.model.CommentEntity;
import news.blog.com.repository.ArticleRepository;
import news.blog.com.repository.CommentRepository;
import news.blog.com.service.CommentService;
import news.blog.com.service.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService
{
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ExtendedConversionService conversionService;

    @Override
    public Collection<CommentDto> getCommentsByArticle(Long id)
    {
        return conversionService.convertMany(commentRepository.findByArticleId(id), CommentDto.class);
    }

    @Override
    public void saveCommentsByArticle(CommentDto commentDto, Long id)
    {
        ArticleEntity articleEntity = articleRepository.findById(id).orElseThrow(()-> new NotFoundException("Article not found"));
        commentRepository.save(CommentEntity.builder()
                                            .article(articleEntity)
                                            .comment(commentDto.getComment())
                                            .build()
        );

    }
}
