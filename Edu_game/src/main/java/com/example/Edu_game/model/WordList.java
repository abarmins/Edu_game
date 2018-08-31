package com.example.Edu_game.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "word_list")
public class WordList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "word_list_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private Player player;

    @OneToMany(cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            mappedBy = "wordList")
    private Set<Word> words;

    @Column(name = "name")
    private String name;
}
