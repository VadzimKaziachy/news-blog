package news.blog.com.service.impl;

import com.tabasoft.converter.api.ExtendedConversionService;
import lombok.AllArgsConstructor;
import news.blog.com.model.HomeEntity;
import news.blog.com.repository.HomeRepository;
import news.blog.com.service.HomeService;
import news.blog.com.service.dto.HomeDto;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService
{

    private final HomeRepository homeRepository;
    private final ExtendedConversionService conversionService;

    @Override
    public HomeDto getHome()
    {
        return conversionService.convert(homeRepository.findFirstByOrderByIdAsc(), HomeDto.class);
    }

    @Override
    public void saveHome(HomeDto homeDto) {
        homeRepository.save(HomeEntity.builder()
                                      .title(homeDto.getTitle())
                                      .textButton(homeDto.getTextButton())
                                      .build());
    }
}
