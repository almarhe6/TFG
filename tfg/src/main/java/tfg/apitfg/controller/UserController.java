package tfg.apitfg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tfg.apitfg.commons.FinancialMapper;
import tfg.apitfg.model.dto.UserDto;
import tfg.apitfg.service.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/user")
public class UserController {
    private final FinancialMapper financialMapper;
    private final IUserService userService;

    @GetMapping()
    public UserDto getUser(@RequestAttribute String email) {
        return financialMapper.toDto(userService.findUser(email));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(financialMapper.toEntity(userDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/delete")
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(financialMapper.toEntity(userDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/modify")
    public void modifyUser(@RequestBody UserDto userDto) {
        userService.modifyUser(financialMapper.toEntity(userDto));
    }
}
