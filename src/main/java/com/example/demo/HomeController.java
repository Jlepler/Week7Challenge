package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",  new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "redirect:/login";
    }

    @GetMapping("/add")
    public String messageForm(@ModelAttribute Message message, Model model, Principal principal, User user){
        model.addAttribute("message", new Message());
        model.addAttribute("user", userRepository.findByUsername(principal.getName()));
        return "messageform";
    }

    @PostMapping("/process")
    public String processMessage(@Valid Message message,
                                 BindingResult result, Principal principal){
        if (result.hasErrors()){
            return "messageform";
        }
        messageRepository.save(message);
        return("home");
    }

    @GetMapping("/username")
    @ResponseBody
    public String currentUsername(Principal principal){
        return principal.getName();
    }

    @RequestMapping("/home")
    public String index(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "home";
    }


    @RequestMapping("/detail/{id}")
    public String showMessage(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("message", messageRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "messageform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        messageRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logoutconfirm(){
        return "logoutconfirm";
    }


}
