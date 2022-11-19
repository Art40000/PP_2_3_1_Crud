package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController{
    private UserService userService;
    int id;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        id = 0;
    }

    @GetMapping (value = "/users")
    public String getUsers( Model model){
        model.addAttribute("listUsers", userService.getAllUsers());
        return "/users";
    }

    @GetMapping(value = "/userdata")
    public String addUser(Model model){
        User user  = new User();
        model.addAttribute("user", user);
        id = 0;
        return "/userdata";
    }

    @PostMapping(value =  "/userdata")
    public String saveUser(@ModelAttribute("user") User user) {
        if (id==0) {
            userService.add(user);
        } else {
            userService.update(user, userService.getUserById(id));
            id = 0;
        }
        return "redirect:/users";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id){
        userService.remove(id);
        return "redirect:/users";
    }

    @GetMapping("/userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        this.id = id;
        return "/userdata";
    }
}
