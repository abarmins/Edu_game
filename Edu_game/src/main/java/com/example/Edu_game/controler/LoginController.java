package com.example.Edu_game.controler;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.example.Edu_game.model.*;
import com.example.Edu_game.service.*;


@Controller
public class LoginController {
	
	@Autowired
	private PlayerService playerService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		Player player = new Player();
		modelAndView.addObject("player", player);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid Player player, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		Player playerExists = playerService.findPlayerByEmail(player.getEmail());
		if (playerExists != null) {
			bindingResult
					.rejectValue("email", "error.player",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			playerService.savePlayer(player);
			modelAndView.addObject("successMessage", "Player has been registered successfully");
			modelAndView.addObject("player", new Player());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}
	
//	@RequestMapping(value="/game/home", method = RequestMethod.GET)
//	public ModelAndView home(){
//		ModelAndView modelAndView = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Player player = playerService.findPlayerByEmail(auth.getName());
//		modelAndView.addObject("playerName", "Welcome " + player.getPlayerName() + " (" + player.getEmail() + ")");
//		modelAndView.addObject("playerMessage","Content Available Only for Registered players");
//		modelAndView.setViewName("game/home");
//		return modelAndView;
//	}
//
//	@RequestMapping(value="/game/Game", method = RequestMethod.GET)
//	public ModelAndView ModelAndView(){
//		ModelAndView modelAndView = new ModelAndView();
//		return modelAndView;
//	}

}
