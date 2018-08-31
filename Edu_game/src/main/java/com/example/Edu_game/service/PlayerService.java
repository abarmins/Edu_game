package com.example.Edu_game.service;

import com.example.Edu_game.model.*;

public interface PlayerService {
	public Player findPlayerByEmail(String email);
	public Player savePlayer(Player player);
}
