package com.example.Edu_game.model;

import java.util.ArrayList;
import java.util.List;

public class AnswersListDto {
	private List<AnswersDTO> answ;
	
	public void addWord(AnswersDTO answPar) {
        this.answ.add(answPar);
    }

	public AnswersListDto() {
		answ = new ArrayList<>();
	}

	public List<AnswersDTO> getAnsw() {
		return answ;
	}

	public void setAnsw(List<AnswersDTO> answ) {
		this.answ = answ;
	}
	
	
}
