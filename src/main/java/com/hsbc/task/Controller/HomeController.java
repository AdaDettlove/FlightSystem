package com.hsbc.task.Controller;

import com.hsbc.task.Model.User;
import com.hsbc.task.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLogin(Model model, HttpServletRequest req) {
        if(req.getParameter("reg")!=null && req.getParameter("reg").equals("success")) {
            model.addAttribute("message", "Registration successful! Please login.");
        }
        if(req.getParameter("logout")!=null && req.getParameter("logout").equals("success")) {
            model.addAttribute("message", "You have successfully logged out.");
        }
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public String validateLogin(@Valid User user, BindingResult bindingResult,
                                @RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "index";
        }
        if(!userService.validateUser(username, password)) {
            model.addAttribute("error", "Your Username or Password is incorrect");
            return "index";
        }
        session.setAttribute("username", username);
        return "redirect:/flights";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.invalidate();
        return "redirect:/?logout=success";
    }

    @GetMapping("/register")
    public String goToIndexPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String validateRegistration(@Valid User user, BindingResult bindingResult,
                                       Model model, @RequestParam String username) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        if(userService.duplicateUsername(username)) {
            model.addAttribute("error", "This username is already taken");
            return "register";
        }
        userService.registerNewUser(user);
        return "redirect:/?reg=success";
    }



}
