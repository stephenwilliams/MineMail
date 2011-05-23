package com.alta189.minemail.addons;

import org.bukkit.entity.Player;
import com.iConomy.system.Holdings;
import com.alta189.minemail.addons.IConomyHandler;

public class IConomyFunctions {
	IConomyHandler instance;

	IConomyFunctions(IConomyHandler plugin) {
		instance = plugin;
	}

	public void takeBalance(Player Player, Double amount, Holdings balance) {

		if (Player.isOp() && instance.plugin.isFree(Player)) {
			if (instance.hasAccount(Player) == true) {
				if (instance.hasEnough(amount, balance) == true) {
					balance.subtract(amount);
				}

			}else {
				if (!Player.isOp() && instance.plugin.isFree(Player)) {
					if (instance.hasAccount(Player) == true) {
						if (instance.hasEnough(amount, balance) == true) {
							balance.subtract(amount);
						}
			}
		}
			}
		}
	}
}
