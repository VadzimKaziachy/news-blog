package news.blog.com.repository;

import news.blog.com.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>
{
    UserEntity findByLogin(String login);

    boolean existsByLogin(String login);
}
