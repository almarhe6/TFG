package tfg.apitfg.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/private/user")
public class UserController {

    @GetMapping("/user")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }

    @GetMapping("/")
    public String user() {
        return "hello";
    }

    @GetMapping("/secured")
    public String secured(){
        // TODO Crear JWT y devolver
        return "Hello, secured";
    }

    @GetMapping("/secured2")
    public String scured2(){
        return "asja";
    }

    @PostMapping("/create")
    public Object createUser(){
        return null;
    }

    @PostMapping("/delete")
    public Object deleteUser(){
        return null;
    }

    @PostMapping("/modify")
    public Object modifyUser(){
        return null;
    }

    @GetMapping("/obtain")
    public Object obtainUser(){
        return null;
    }
}