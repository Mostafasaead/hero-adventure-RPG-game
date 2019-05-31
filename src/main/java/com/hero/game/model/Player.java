package com.hero.game.model;

import com.hero.game.service.StoryLineService;

public class Player {
	private static final int rows = 6;
	private static final int columns = 4;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getStones() {
		return stones;
	}

	public void setStones(Integer stones) {
		this.stones = stones;
	}

	public GameCharacter getCharacter() {
		return character;
	}

	public void setCharacter(GameCharacter character) {
		this.character = character;
	}

	public Spaceship getSpaceship() {
		return spaceship;
	}

	public void setSpaceship(Spaceship spaceship) {
		spaceship = spaceship;
	}

	private Integer level;
	private Integer health;
	private Integer stones;
	private GameCharacter character;

	private Spaceship spaceship;
	private StoryLineService storyLineService;

	public void setStoryLineService(StoryLineService storyLineService) {
		this.storyLineService = storyLineService;
	}

	@Override
	public String toString() {
		return this.getName() + "'s Health : " + this.getHealth() + " Stones: " + this.getStones();
	}

	public Player(String name, GameCharacter hero, GameCharacter enemy) {
		this.name = name;
		this.health = 100;
		this.character = hero;
		this.stones = 0;
		this.level = 1;
		this.spaceship = new Spaceship(hero, enemy);
	}

	public Player(String name, int level, int stones, int health, Spaceship Spaceship) {
		this.name = name;
		this.level = level;
		this.stones = stones;
		this.health = health;
		this.spaceship = Spaceship;
	}

	public void modifyHealth(int delta) {
		health += delta;
		if (health < 0)
			health = 0;
	}

	public void modifyEnemyHealth(int delta) {
		int currentHealth = this.character.getHealth();
		currentHealth += delta;
		this.character.setHealth(currentHealth);
		if (health < 0)
			health = 0;
	}

	public void modifyStones(int number) {
		stones += number;
	}

	public void IncLevel() {
		this.level++;
	}

	public void DecLevel() {
		this.level--;
	}

	public boolean explore() {
		spaceship.print();
		storyLineService.printGuidness();
		String cmd = storyLineService.readUserInputString();
		if (cmd.equalsIgnoreCase("QUIT") || cmd.equalsIgnoreCase("Q")) {
			return true;
		} else {
			switch (cmd.toUpperCase()) {
			case "NORTH":
			case "N":
				moveNorth();
				break;
			case "SOUTH":
			case "S":
				moveSouth();
				break;
			case "WEST":
			case "W":
				moveWest();
				break;
			case "EAST":
			case "E":
				moveEast();
				break;
			default:
				storyLineService.displayExploreError();
			}
		}

		System.out.flush();
		return false;
	}

	private void moveNorth() {
		if (spaceship.getCurrentRoomX() == 0) {
			storyLineService.printOutofSpaceShipalert();
		} else {
			spaceship.IncOrDecCurrentRoomX(-1);
		}

	}

	private void moveSouth() {
		if (spaceship.getCurrentRoomX() == rows - 1) {
			storyLineService.printOutofSpaceShipalert();
		} else {
			spaceship.IncOrDecCurrentRoomX(1);
		}

	}

	private void moveWest() {
		if (spaceship.getCurrentRoomY() == 0) {
			storyLineService.printOutofSpaceShipalert();
		} else {
			spaceship.IncOrDecCurrentRoomY(-1);
		}

	}

	private void moveEast() {
		if (spaceship.getCurrentRoomY() == columns - 1) {
			storyLineService.printOutofSpaceShipalert();
		} else {
			spaceship.IncOrDecCurrentRoomY(1);
		}
	}
}
