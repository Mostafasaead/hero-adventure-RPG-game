package com.hero.game.service;

import java.sql.Connection;
import java.util.List;

import com.hero.game.dao.PlayerDAO;
import com.hero.game.model.GameCharacter;
import com.hero.game.model.GameCharacterEnum;
import com.hero.game.model.Hulk;
import com.hero.game.model.IronMan;
import com.hero.game.model.Player;
import com.hero.game.model.Thanos;

public class PlayerManager implements PlayerService {

	PlayerDAO playerDao;
	StoryLineService ui;

	public PlayerManager(Connection db, StoryLineService ui) {
		playerDao = new PlayerDAO(db);
		this.ui = ui;
	}

	public Player selectPlayer() {
		List<Player> players = playerDao.getPlayers();
		if (players != null && players.size() > 0) {
			ui.displayPlayers(players);

			int selected = ui.readUserInputInt();
			if (selected > 0 && selected <= players.size()) {
				return players.get(selected - 1);
			} else if (selected == players.size() + 2) {
				deletePlayer();
				return selectPlayer();
			} else if (selected == players.size() + 1) {
				return addPlayer();
			} else {
				ui.displayInvalidOptionMessage();
			}
		}
		return addPlayer();
	}

	private void deletePlayer() {
		ui.confirmUserDeletion();
		String name = ui.readUserInputString();
		playerDao.deletePlayer(name);
	}

	public Player addPlayer() {
		ui.userIntroMessage();
		String name = ui.readUserInputString();
		ui.userHeroMessage();
		String hero = ui.readUserInputString();
		GameCharacter heroCharacter;
		GameCharacter enemyCharacter = new Thanos(GameCharacterEnum.Thanos);
		enemyCharacter.setHealth(100);
		switch (hero) {
		case "1":
			heroCharacter = new IronMan(GameCharacterEnum.IronMan);
			break;
		case "2":
			heroCharacter = new Hulk(GameCharacterEnum.Hulk);
			break;
		default:
			heroCharacter = new Hulk(GameCharacterEnum.Hulk);
			break;
		}

		Player selectedPlayer = new Player(name, heroCharacter, enemyCharacter);
		playerDao.persistPlayer(selectedPlayer);
		return selectedPlayer;
	}

	@Override
	public void savePlayer(Player player) {
		playerDao.updatePlayer(player);
		ui.exitMessage();
	}

}
