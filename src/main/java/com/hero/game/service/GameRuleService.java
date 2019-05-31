package com.hero.game.service;

import com.hero.game.model.Player;

public interface GameRuleService {
	boolean apply(Player player);
}
