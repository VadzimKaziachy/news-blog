package news.blog.com.service;

import news.blog.com.service.dto.CommentDto;

import java.util.Collection;

public interface CommentService
{
    Collection<CommentDto> getCommentsByArticle(Long id);

    void saveCommentsByArticle(CommentDto commentDto, Long id);
}
