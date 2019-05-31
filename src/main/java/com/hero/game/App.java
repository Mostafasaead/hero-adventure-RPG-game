package com.hero.game;

import java.sql.Connection;

import com.hero.game.model.Player;
import com.hero.game.model.Spaceship;
import com.hero.game.service.GameRuleService;
import com.hero.game.service.GameRuleServiceImpl;
import com.hero.game.service.PlayerManager;
import com.hero.game.service.StoryLineService;
import com.hero.game.service.StoryLineServiceImpl;
import com.hero.game.util.DBConnection;

public class App {
	public static void main(String[] args) {
		Connection db = DBConnection.getConnection();
		try {
			StoryLineService storyLineService = new StoryLineServiceImpl();
			storyLineService.printStory();
			PlayerManager usermanager = new PlayerManager(db, storyLineService);
			GameRuleService gameRules = new GameRuleServiceImpl(storyLineService);

			Player player = usermanager.selectPlayer();
			player.setStoryLineService(storyLineService);
			boolean gotResult = false;
			System.out.println("Let's go!!");
			while (!gotResult) {
				if (player.explore()) { // returns true if user wants to quit
					break;
				}
				gotResult = gameRules.apply(player);
			}
			if (gotResult) {
				player.setSpaceship(new Spaceship(null, null));
				player.modifyHealth(100 - player.getHealth());
				storyLineService.printFinishMessage();
			} else {
				storyLineService.displayUnFinishedGameMessage();
			}
			usermanager.savePlayer(player);
		} finally {
			DBConnection.closeConnection();
		}
		System.out.println("Hello World!");
	}
}
