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
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping (value = "/users")
    public String getUsers( Model model){
        model.addAttribute("listUsers", userService.getAllUsers());
        return "/users";
    }

    @RequestMapping(value = "/userdata", method = RequestMethod.GET)
    public String addUser(Model model){
        User user  = new User();
        model.addAttribute("user", user);
        return "/userdata";
    }

    @RequestMapping(value =  "/userdata" , method = RequestMethod.POST)
    public String saveUser(Model model, @ModelAttribute("user") User user) {
        this.userService.add(user);
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id){
        this.userService.remove(id);
        return "redirect:/users";
    }

    @RequestMapping("/userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));
        this.userService.remove(id);
        return "/userdata";
    }
}
