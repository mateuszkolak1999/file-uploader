package pl.kolak.hostingzdjec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.hostingzdjec.model.File;
import pl.kolak.hostingzdjec.model.UserApp;
import pl.kolak.hostingzdjec.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserApp> register(@RequestBody UserApp user, BindingResult result){
        if(!result.hasErrors()){
            userService.addUser(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(user);
        }
        throw new IllegalArgumentException("File does not save!");
    }
}
