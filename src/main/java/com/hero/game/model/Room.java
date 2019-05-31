package com.hero.game.model;

import java.io.Serializable;

public class Room implements Serializable{
	GameCharacterEnum character;

	public GameCharacterEnum getOccupiedCharacter() {
		return character;
	}

	public void setOccupiedCharacter(GameCharacterEnum you) {
		this.character = you;
	}

}
