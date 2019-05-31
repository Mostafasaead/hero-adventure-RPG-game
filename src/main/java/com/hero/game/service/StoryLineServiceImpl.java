package com.hero.game.service;

import java.util.List;
import java.util.Scanner;

import org.hsqldb.rights.User;

import com.hero.game.model.Player;

public class StoryLineServiceImpl implements StoryLineService {
	Scanner scan = new Scanner(System.in);

	@Override
	public void printStory() {
		// TODO Auto-generated method stub
		System.out.println("Thanos is Here \n" + "He is looking for the stones to destroy our world\n"
				+ "You are the hero now try to stop him and find the stones before him\n"
				+ "Look for the stones in the spaceship with N,E,W,S keys  \n" + "Choose your destiny fight or run");
		System.out.println("\n--------------------------------------\n");

		System.out.flush();
	}

	@Override
	public void printGuidness() {
		System.out.println("\n Choose your direction?\n" + "NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s)\n"
				+ "Type Quit/Q/q to exit");

	}

	@Override
	public void printFinishMessage() {
		System.out.println("Thanks for trying make our world better");
	}

	@Override
	public void displayUnFinishedGameMessage() {
		System.out.println("Your game will be saved.You can resume the game by selecting same user");
	}

	@Override
	public void printOutofSpaceShipalert() {
		System.out.println("Oops, You are going to lost in the space, correct your direction");

	}

	@Override
	public void displayExploreError() {
		System.err.println("Invalid input.Only N/E/W/S/Q are allowed");
	}

	@Override
	public String readUserInputString() {
		String input = scan.nextLine();
		return input;
	}

	@Override
	public int readUserInputInt() {
		int input = scan.nextInt();
		scan.nextLine();// takes care of \n character after number
		return input;
	}

	@Override
	public void destroy() {
		scan.close();

	}

	@Override
	public void displayPlayers(List<Player> players) {
		printHeader();
		int i = 0;
		for (Player player : players) {
			System.out.println(++i + ")   " + player.getName() + "\t" + player.getStones() + "\t" + player.getHealth());
		}
		System.out.println(++i + ")   create New Player");
		System.out.println(++i + ")   Delete A Player");
		System.out.flush();
	}

	private void printHeader() {
		System.out.println("Select a player from the following");
		System.out.println("----------------------------------");
		System.out.println("\tName\tStones\tHealth");
		System.out.println("----------------------------------");
		System.out.flush();
	}

	@Override
	public void displayInvalidOptionMessage() {
		System.out.println("Invalid option.Try again");
	}

	@Override
	public void confirmUserDeletion() {
		System.out.println("To confirm deletion - enter the name of user.To cancel - enter #cancel");

	}

	@Override
	public void userIntroMessage() {
		System.out.println("Let's get introduced");
		System.out.println("What should I call you?");

	}

	@Override
	public void exitMessage() {
		System.out.println("Goodbye!");

	}

	@Override
	public void displayWinMessage() {
		System.out.println("Congratulations you saved the world, You Have the five stones now");

	}

	@Override
	public void displayAnswerPrompt() {
		System.out.print("\nEnter #help for available commands. Make your choice(run, fight):");
		System.out.flush();

	}

	@Override
	public void displayEnemy() {
		System.out.println("Thanos is here this choose your destiny, fight or run ");

	}

	@Override
	public void displayLooseMessage() {
		System.out.println("Thanos are going to destroy our world, we are sorry :(");

	}

	@Override
	public void displayNoStonesMessage() {
		System.out.println("You don't have enough stones to Run!");

	}

	@Override
	public void displayCorrectAnswerMessage() {
		System.out.println("Wondrful hit! one more stone");

	}

	@Override
	public void displayInCorrectAnswerMessage() {
		System.out.println("you injured! focus on your next hit");

	}

	@Override
	public void printHelp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void userHeroMessage() {
		System.out.println("Choose you Avenger");
		System.out.println("1- IronMan");
		System.out.println("1- Hulk");

	}

	@Override
	public void userEnemyMessage() {
		// TODO Auto-generated method stub please choose you enemy

	}

}
