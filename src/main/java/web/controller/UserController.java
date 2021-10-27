package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;



@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "userlist";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.add(user);
        return "redirect:/users";
    }
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User byId = userService.getById(id);
        model.addAttribute("user", byId);
        return "/user-update";
    }
    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.update(user);
        return "redirect:/users";
    }
}
