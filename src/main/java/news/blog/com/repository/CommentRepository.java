package news.blog.com.repository;

import news.blog.com.model.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity, Long>
{
    List<CommentEntity> findByArticleId(Long id);
}
