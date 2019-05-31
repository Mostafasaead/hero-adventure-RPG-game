package com.hero.game.service;

import java.util.List;

import com.hero.game.model.Player;

public interface StoryLineService {
	void printStory();

	void printGuidness();

	void printFinishMessage();

	void printOutofSpaceShipalert();

	void displayExploreError();

	String readUserInputString();

	int readUserInputInt();

	void destroy();

	void displayPlayers(List<Player> users);

	void displayInvalidOptionMessage();

	void confirmUserDeletion();

	void userIntroMessage();

	void exitMessage();

	void displayWinMessage();

	void displayAnswerPrompt();

	void displayEnemy();

	void displayLooseMessage();

	void displayNoStonesMessage();

	void displayCorrectAnswerMessage();

	void displayInCorrectAnswerMessage();

	void printHelp();

	void userHeroMessage();

	void userEnemyMessage();

	void displayUnFinishedGameMessage();
}
