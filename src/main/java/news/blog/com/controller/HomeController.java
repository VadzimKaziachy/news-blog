package news.blog.com.controller;

import lombok.RequiredArgsConstructor;
import news.blog.com.service.HomeService;
import news.blog.com.service.dto.HomeDto;
import news.blog.com.service.impl.HomeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController
{
    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<HomeDto> getHome()
    {
        return ResponseEntity.ok().body(homeService.getHome());
    }

    @PostMapping
    public ResponseEntity saveHome(@RequestBody HomeDto homeDto)
    {
        homeService.saveHome(homeDto);
        return ResponseEntity.ok().build();
    }
}
