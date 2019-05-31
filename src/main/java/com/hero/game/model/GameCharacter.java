/**
 * 
 */
package com.hero.game.model;

/**
 * @author ElGazzarM2
 *
 */
public abstract class GameCharacter {
	GameCharacter(GameCharacterEnum gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	private String weapon;
	private GameCharacterEnum gameCharacter;
	private String name;
	private boolean isHero;
	private int health;

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public boolean isHero() {
		return isHero;
	}

	public void setHero(boolean isHero) {
		this.isHero = isHero;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public GameCharacterEnum getGameCharacter() {
		return gameCharacter;
	}

	public void setGameCharacter(GameCharacterEnum gameCharacter) {
		this.gameCharacter = gameCharacter;
	}

	abstract void fight();
}
