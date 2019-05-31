package com.hero.game.service;

import com.hero.game.model.Player;

public interface PlayerService {

	public Player selectPlayer();

	public Player addPlayer();

	public void savePlayer(Player player);
}
