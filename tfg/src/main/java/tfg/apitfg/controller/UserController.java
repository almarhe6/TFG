package tfg.apitfg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/user")
public class UserController {

    @GetMapping("/")
    public String user() {
        return "hello";
    }

    @GetMapping("/secured")
    public String secured(){
        // TODO Crear JWT y devolver
        return "Hello, secured";
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