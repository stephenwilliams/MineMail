package com.alta189.minemail.addons;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.iConomy.system.Holdings;
import com.alta189.minemail.addons.IConomyHandler;

public class IConomyFunctions {
	IConomyHandler instance;

	IConomyFunctions(IConomyHandler plugin) {
		instance = plugin;
	}

	public Boolean takeBalance(Player player, Double amount, Holdings balance) {

		if (instance.plugin.isFree(player)) {
			if (instance.hasAccount(player) == true) {
				player.sendMessage("<header>MineMail <c1>- You weren't charged for this!");
					return true;
				}

			}else {
				if (!instance.plugin.isFree(player)) {
					if (instance.hasAccount(player) == true) {
						if (instance.hasEnough(amount, balance) == true) {
							balance.subtract(amount);
							player.sendMessage("<header>MineMail <c1>- You were charged for this!");
							player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "iConomy" + ChatColor.WHITE + "]" + ChatColor.GREEN + "Balance: " + balance);
							return true;
						}
					}
				}
			}
		return false;
	}
}
	