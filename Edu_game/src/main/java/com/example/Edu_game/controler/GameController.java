package com.example.Edu_game.controler;


import com.example.Edu_game.model.AnswersDTO;
import com.example.Edu_game.model.AnswersListDto;
import com.example.Edu_game.model.Player;
import com.example.Edu_game.model.Word;
import com.example.Edu_game.repository.WordRepository;
import com.example.Edu_game.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    @Autowired
    private PlayerService playerService;
    
    @Autowired
    WordRepository wordRep;
    
    private int score = 0;
    
    @RequestMapping(value="/game/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Player player = playerService.findPlayerByEmail(auth.getName());


        modelAndView.addObject("wordList", "Welcome " + player.getPlayerName() + " (" + player.getEmail() + ")");
        modelAndView.addObject("playerMessage","Content Available Only for Registered players");
        modelAndView.setViewName("game/home");
        return modelAndView;
    }
	@RequestMapping(value="/game/NewList", method = RequestMethod.GET)
	public ModelAndView ModelAndView(){
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("game/NewList");
		return modelAndView;
	}
	
	@RequestMapping(value="/game/fooform", method = RequestMethod.POST)
	public String fooForm(Model model,  @ModelAttribute("word") String world, String definition  ){
	
		if ((world == "") || (definition == "") ){
			return "redirect:/game/NewList";
		} else {
		Word word = new Word();
		word.setWord(world);
		word.setDefinition(definition);
		wordRep.save(word);
	

		return "redirect:/game/NewList";
		}
	}

	@RequestMapping(value="/game/list", method = RequestMethod.GET)
	public ModelAndView listModel(Model model,  @ModelAttribute("word") String world, String definition  ){
		List<Word> list = wordRep.findAll();
		List<AnswersDTO> answersDTOs = new ArrayList<>();
		AnswersListDto ansList = new AnswersListDto();
		for(int i = 0; i < list.size(); i++) {
			AnswersDTO answersDTO = new AnswersDTO();
			answersDTO.setQuestion(list.get(i).getDefinition());
			ansList.addWord(answersDTO);;
		}
		
		model.addAttribute("words",list);
		model.addAttribute("answersDTOs",answersDTOs);
		model.addAttribute("ansList",ansList);
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("game/list");
		return modelAndView;
	}
	@RequestMapping(value="/game/check", method = RequestMethod.POST)
	public String list (Model model,  @ModelAttribute("ansList") AnswersListDto ansList  ){
	
	//if wordRep.
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx=>>>>>>>" + ansList.getAnsw().toString());
		List<AnswersDTO> dtos = ansList.getAnsw();
		int postiveCount = 0;
		for(AnswersDTO answersDTO: dtos) {
			String word = answersDTO.getAnswer();
			String answer = answersDTO.getQuestion();
			Word wordR = wordRep.findByWord(word);
			if	(wordR.getDefinition().equalsIgnoreCase(answer)) {
				postiveCount++;
			}
			this.score = postiveCount;
		}
		return "redirect:/game/Score";
	}
	@RequestMapping(value="/game/Score", method = RequestMethod.GET)
	public ModelAndView ScoreModel(){
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("Score", score);
		 
		return modelAndView;
}
}

