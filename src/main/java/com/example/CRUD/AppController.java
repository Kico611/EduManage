package com.example.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;
    @GetMapping("/")
    public String viewHomePage(Model model){
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "index";
    }
    @GetMapping("/Home/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/process_login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               Model model) {
        Optional<User> user = repo.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            model.addAttribute("currentUser", user.get());
            return "redirect:/"; // Redirect to home page on successful login
        } else {
            model.addAttribute("errorMessage", "Invalid email or password!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user",new User());
        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegistration(User user, Model model) {
        Optional<User> existingUser = repo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            model.addAttribute("errorMessage", "Korisnik s tim e-mailom već postoji!");
            return "signup_form";
        }
        repo.save(user);
        List<User> users = repo.findAll();
        model.addAttribute("users", users);
        return "redirect:/";
    }
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User updatedUser) {
        User existingUser = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        // Check if the password field is empty
        if (updatedUser.getPassword() == null || updatedUser.getPassword().isEmpty()) {
            // Retain the current password
            updatedUser.setPassword(existingUser.getPassword());
        } else {
            // Validate the new password (optional)
            updatedUser.setPassword(updatedUser.getPassword());
        }

        // Save the updated user
        repo.save(updatedUser);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        model.addAttribute("user", user);
        return "edit_user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}
