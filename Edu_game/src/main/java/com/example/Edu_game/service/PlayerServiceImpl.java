package com.example.Edu_game.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Edu_game.model.Player;
import com.example.Edu_game.repository.PlayerRepository;
import com.example.Edu_game.model.Role;
import com.example.Edu_game.repository.RoleRepository;


@Service("userService")
public class PlayerServiceImpl implements PlayerService {

	private PlayerRepository playerRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		this.playerRepository = playerRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public Player findPlayerByEmail(String email) {
		return playerRepository.findByEmail(email);
	}

	@Override
	public Player savePlayer(Player player) {
		player.setPassword(bCryptPasswordEncoder.encode(player.getPassword()));
        player.setActive(1);
        Role userRole = roleRepository.findByRole("PLAYER");
        player.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	
        Player res = playerRepository.save(player);
        return res;
	}
	
	
}
