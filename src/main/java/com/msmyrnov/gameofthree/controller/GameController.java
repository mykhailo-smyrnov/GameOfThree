package com.msmyrnov.gameofthree.controller;

import com.msmyrnov.gameofthree.model.Game;
import com.msmyrnov.gameofthree.model.User;
import com.msmyrnov.gameofthree.service.ManagePlayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@SessionAttributes({"user", "game"})
public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private ManagePlayService managePlayService;

    @ModelAttribute("game")
    public Game setUpGame() {
        return new Game();
    }

    @GetMapping(value = "/game")
    public String move(@SessionAttribute("user") User user, Model model) {
        String userName = user.getName();
        if (StringUtils.isBlank(userName)) {
            return "redirect:/";
        } else {
            model.addAttribute("welcome_message", "Welcome " + userName + "!");
            Game gameInProgress = managePlayService.getGameInProgress(userName);
            if (gameInProgress != null) {
                model.addAttribute("game", gameInProgress);
                model.addAttribute("isGameStarted", gameInProgress.isStarted());
                model.addAttribute("info_message", gameInProgress.getInfoMessage());
            }
            return "game";
        }
    }

    @PostMapping(value = "/game/play")
    public String play(@Valid @ModelAttribute("user") User user,
                       @Valid @ModelAttribute("game") Game game,
                       final RedirectAttributes redirectAttributes,
                       final HttpServletRequest req) {
        String userName = user.getName();
        String moveByName = req.getParameter("moveByName");
        String startNewGameString = req.getParameter("startNewGame");
        boolean startNewGame = Boolean.parseBoolean(startNewGameString);

        if (startNewGame) {
            game = managePlayService.play(userName, moveByName, null);
        } else {
            String moveNumberString = req.getParameter("moveNumber");
            try {
                Integer moveNumber = Integer.valueOf(moveNumberString);
                if (moveNumber > 1 || moveNumber < -1)
                    throw new IllegalArgumentException();
                game = managePlayService.play(userName, moveByName, moveNumber);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                redirectAttributes.addFlashAttribute("error_message",
                        "Invalid number. Please set value in range : {-1, 0, 1}");
            }
        }

        if (game.isFinished()) {
            redirectAttributes.addFlashAttribute("isGameFinished", game.isFinished());
            redirectAttributes.addFlashAttribute("info_message", game.getInfoMessage());
        }

        return "redirect:/game";
    }

}
