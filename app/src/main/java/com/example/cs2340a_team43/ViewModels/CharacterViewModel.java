package com.example.cs2340a_team43.ViewModels;

import com.example.cs2340a_team43.Models.Character;

public abstract class CharacterViewModel {
    private Character character;

    public CharacterViewModel(Character character) {
        this.character = character;
    }

    public CharacterViewModel() {

    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
