package com.msmyrnov.gameofthree.controller;

import com.msmyrnov.gameofthree.model.User;
import com.msmyrnov.gameofthree.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private AccountService accountService;

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/register")
    public String login(@Valid @ModelAttribute("user") User user,
                        Errors errors,
                        RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            List<String> errorMessagesList = new ArrayList<>();
            for (ObjectError e : errors.getAllErrors()) {
                errorMessagesList.add(e.getDefaultMessage());
            }
            redirectAttributes.addFlashAttribute("errorMessage",
                    StringUtils.join(errorMessagesList, " ::: "));
            return "redirect:/";
        } else {
            String userName = user.getName();
            boolean isAccountExist = accountService.isExistByName(userName);
            if (!isAccountExist) {
                try {
                    accountService.create(userName);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage",
                            "Something wrong while creating account. Check and try another name please.");
                    LOGGER.error(e.getMessage(), e);
                    return "redirect:/";
                }
            }
            return "redirect:/game";
        }
    }
}
