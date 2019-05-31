package com.hero.game.model;

import java.io.Serializable;

public class Spaceship implements ConsoleMap, Serializable {
	private static final int spaceshipRows = 6;
	private static final int spaceshipColumns = 4;
	Room[][] rooms = new Room[spaceshipRows][spaceshipColumns];
	Integer x;
	Integer y;

	public Spaceship(GameCharacter hero, GameCharacter enemy) {
		// init rooms
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				rooms[i][j] = new Room();
			}
		}
		// select a room for user randomly
		x = (int) (Math.random() * spaceshipRows);
		y = (int) (Math.random() * spaceshipColumns);

		int enemies = 10;
		for (int i = 0; i < enemies; i++) {
			rooms[(int) (Math.random() * spaceshipRows)][(int) (Math.random() * spaceshipColumns)]
					.setOccupiedCharacter(enemy.getGameCharacter());
		}
		// select a room for princess which is different from user's room
		int princessX = (int) (Math.random() * spaceshipRows);
		int princessY = (int) (Math.random() * spaceshipColumns);
		if (princessX == x && princessY == y) {
			princessX = (int) (Math.random() * spaceshipRows);
			princessY = (int) (Math.random() * spaceshipColumns);
		}
		rooms[princessX][princessY].setOccupiedCharacter(hero.getGameCharacter());
		// at least four monsters are very near to princess
		rooms[princessX][princessY == 0 ? princessY + 1 : princessY - 1].setOccupiedCharacter(enemy.getGameCharacter());
		rooms[princessX == 0 ? princessX + 1 : princessX - 1][princessY].setOccupiedCharacter(enemy.getGameCharacter());
	}

	public void print() {
		for (int c = 0; c < spaceshipColumns; c++) {
			System.out.print(" ___ ");
		}
		System.out.println();

		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				String consoleFigure = "|___|";
				if (i == x && j == y) {
					consoleFigure = "|_U_|";
					rooms[i][j].setOccupiedCharacter(null);
				}
				System.out.print(consoleFigure);
			}
			System.out.println();
		}
	}

	public Integer getCurrentRoomX() {
		return x;
	}

	public Integer getCurrentRoomY() {
		return y;
	}

	public Room getCurrentRoom() {
		return rooms[x][y];
	}

	public void IncOrDecCurrentRoomY(int i) {
		y += i;
	}

	public void IncOrDecCurrentRoomX(int i) {
		x += i;
	}

	public static void main(String[] args) {
		GameCharacter hero = new IronMan(GameCharacterEnum.IronMan);
		GameCharacter enemy = new IronMan(GameCharacterEnum.Thanos);
		Spaceship spaceship = new Spaceship(hero, enemy);
		spaceship.print();
	}
}
