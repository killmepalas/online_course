package kill.me.palas.controllers;

import kill.me.palas.models.Role;
import kill.me.palas.models.User;
import kill.me.palas.services.SecurityService;
import kill.me.palas.services.UserService;
import kill.me.palas.services.UserServiceImpl;
import kill.me.palas.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    private String password;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        password = userForm.getConfirmPassword();

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return  "redirect:/course";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "redirect:/course";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        if (db_user !=null){
            model.addAttribute("user",db_user);
            int rating = 80;
            model.addAttribute("rating",rating);
            return "user/profile";
        }
        else return "error/not_auth";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "user/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
        @PathVariable("id") int id) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        kill.me.palas.models.User db_user = userService.findByUsername(username);
        userValidator.up_validate(user, bindingResult,db_user);
            if (bindingResult.hasErrors()){return "user/edit";}
        String pass;
        if (db_user.getPassword() != user.getConfirmPassword()) pass = user.getConfirmPassword();
        else pass = password;
        userServiceImpl.update(id, user,db_user);
        securityService.autoLogin(user.getUsername(),pass);
        return "redirect:/profile";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "error/not_auth";
    }

}
