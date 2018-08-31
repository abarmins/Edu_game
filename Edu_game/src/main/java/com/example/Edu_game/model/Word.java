package com.example.Edu_game.model;


import javax.persistence.*;

@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "word_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name="word_list_id")
    private WordList wordList;

    @Column(name = "definition")
    private String definition;

    @Column(name = "word")
    private String word;
    
    public String getWord() {
		return word;
    }

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
    }

	public void setDefinition(String definition) {
		this.definition = definition;
	}
}
