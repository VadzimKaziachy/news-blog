package news.blog.com.service;

import news.blog.com.service.dto.HomeDto;

public interface HomeService
{
    HomeDto getHome();

    void saveHome(HomeDto homeDto);
}
