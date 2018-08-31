package com.example.Edu_game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Edu_game.model.*;

@Repository("playerRepository")
public interface PlayerRepository extends JpaRepository<Player, Long> {
	Player findByEmail(String email);

}
