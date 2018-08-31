package com.example.Edu_game.repository;

import com.example.Edu_game.model.WordList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("wordListRepository")
public interface WordListRepository extends JpaRepository<WordList, Integer> {
    WordList findById (int id);
}
