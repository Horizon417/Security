package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users")
    public String listUsers(Model model) {
        model.addAttribute("userList", userService.listUsers());
        return "userlist";
    }

    @GetMapping(value = "/admin")
    public String adminLogin() {
        return "admin";
    }

    @GetMapping("/userInfo")
    public ModelAndView showUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/admin/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/admin/user-create")
    public String createUser(User user){
        userService.add(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User byId = userService.getById(id);
        model.addAttribute("user", byId);
        return "/user-update";
    }
    @PostMapping("/admin/user-update")
    public String updateUser(User user){
        userService.update(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/Test")
    public String test(Model model){
        List<User> testList = userService.listUsers();

        model.addAttribute("testList", testList);

        return "Test";
    }
}
