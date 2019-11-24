package news.blog.com.repository;

import news.blog.com.model.HomeEntity;
import org.springframework.data.repository.CrudRepository;

public interface HomeRepository extends CrudRepository<HomeEntity, Long>
{
    HomeEntity findFirstByOrderByIdAsc();
}
