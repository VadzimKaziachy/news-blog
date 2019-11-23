package news.blog.com.controller;

import lombok.AllArgsConstructor;

import news.blog.com.service.UserService;
import news.blog.com.service.dto.UserDto;
import news.blog.com.service.RegistrationService;

import news.blog.com.service.dto.responseDto.UserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController
{

    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping
    public ResponseEntity<UserProfileDto> getUserProfile()
    {
        return ResponseEntity.ok().body(userService.getUserProfile());
    }

    @PostMapping("/save")
    public ResponseEntity saveUserProfile(@RequestBody UserDto user)
    {
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public ResponseEntity<UserProfileDto> registration(@RequestBody UserDto user)
    {
        return ResponseEntity.ok().body(registrationService.registrationUser(user));
    }
}