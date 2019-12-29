package news.blog.com.controller;

import lombok.AllArgsConstructor;

import news.blog.com.service.ArticleService;
import news.blog.com.service.UserService;
import news.blog.com.service.dto.ArticleDto;
import news.blog.com.service.dto.UserDto;
import news.blog.com.service.RegistrationService;

import news.blog.com.service.dto.response.UserProfileResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController
{

    private final UserService userService;
    private final ArticleService articleService;
    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<Collection<UserProfileResponseDto>> getUsersProfile()
    {
        return ResponseEntity.ok().body(userService.getUsersProfile());
    }

    @PostMapping()
    public ResponseEntity saveUserProfile(@RequestBody UserDto user)
    {
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/articles")
    public ResponseEntity<Collection<ArticleDto>> getArticle(
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        return ResponseEntity.ok().body(articleService.getArticlesByUser(pageable));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponseDto> getUserProfile()
    {
        return ResponseEntity.ok().body(userService.getUserProfile());
    }

    @PostMapping("/registration")
    public ResponseEntity<UserProfileResponseDto> registration(@RequestBody UserDto user)
    {
        return ResponseEntity.ok().body(registrationService.registrationUser(user));
    }
}
