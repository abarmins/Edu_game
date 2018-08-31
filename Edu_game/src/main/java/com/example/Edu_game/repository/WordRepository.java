package com.example.Edu_game.repository;


import com.example.Edu_game.model.Word;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("wordRepository")
public interface WordRepository extends JpaRepository<Word, Integer> {
        Word findById (int id);
        
        List<Word> findAll();
        
        Word findByWord (String word);
}
