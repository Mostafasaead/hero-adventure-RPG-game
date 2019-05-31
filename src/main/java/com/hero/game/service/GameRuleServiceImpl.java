package com.hero.game.service;

import com.hero.game.model.GameCharacterEnum;
import com.hero.game.model.Player;

public class GameRuleServiceImpl implements GameRuleService {
	StoryLineService ui;
	private static final String RUN = "run";
	private static final String FIGHT = "fight";

	public GameRuleServiceImpl(StoryLineService ui) {
		this.ui = ui;
	}

	@Override
	public boolean apply(Player player) {
		GameCharacterEnum gameCharacterEnum = player.getSpaceship().getCurrentRoom().getOccupiedCharacter();
		if ((gameCharacterEnum == GameCharacterEnum.Hulk || gameCharacterEnum == GameCharacterEnum.IronMan)
				&& player.getCharacter().isHero()) {
			ui.displayWinMessage();
			return true;
		} else if (player.getStones() == 5) {
			ui.displayWinMessage();
			return true;
		} else if (gameCharacterEnum == GameCharacterEnum.Thanos) {
			ui.displayEnemy();

			System.out.println();
			String userAnswer = "";
			do {
				ui.displayAnswerPrompt();
				userAnswer = ui.readUserInputString();
				if (userAnswer.equalsIgnoreCase(FIGHT)) {
					double heroHealthLost = fightMonster();
					double enemyHealthLost = fightMonster();
					player.modifyHealth((int) heroHealthLost);
					player.modifyEnemyHealth((int) enemyHealthLost);
					player.IncLevel();
					if (heroHealthLost < enemyHealthLost)
						player.modifyStones(+1);
					if (player.getHealth() == 0) {
						ui.displayLooseMessage();
						return true;
					}
					break;
				} else if (userAnswer.equalsIgnoreCase(RUN)) {
					if (player.getStones() == 0) {
						ui.displayNoStonesMessage();
					} else {
						player.modifyStones(-1);
//						System.out.println(riddlesDao.getRiddleHint(player.getLevel()));
					}

				} else {
					ui.displayInCorrectAnswerMessage();
				}
			} while (true);
			System.out.println(player);
		}
		return false;

	}

	private double fightMonster() {
		System.out.println("Fighting Monster...");
		return Math.random() * 25 - 40;
	}
}
