package news.blog.com.repository;

import news.blog.com.model.ArticleEntity;
import news.blog.com.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>
{
    List<ArticleEntity> findAll();

    List<ArticleEntity> findByTag(String string);

    @Query(value = "SELECT DISTINCT tag FROM ArticleEntity ORDER BY tag ASC")
    Set<String> findAllTags();

    @Query(value = "SELECT COUNT(article) FROM ArticleEntity article WHERE article.tag = :tag")
    Long findAmountArticleByTag(@Param("tag") String tag);

    Optional<ArticleEntity> findById(Long id);

    Page<ArticleEntity> findAll(Pageable pageable);

    Page<ArticleEntity> findArticleEntitiesByUser(UserEntity user, Pageable pageable);
}
