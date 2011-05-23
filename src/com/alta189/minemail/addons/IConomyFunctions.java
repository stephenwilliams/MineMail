package com.alta189.minemail.addons;


import org.bukkit.entity.Player;
import com.iConomy.system.Holdings;
import com.alta189.minemail.addons.IConomyHandler;

public class IConomyFunctions {
	IConomyHandler instance;

	IConomyFunctions(IConomyHandler plugin) {
		instance = plugin;
	}

	public Boolean takeBalance(Player player, Double amount) {
		if (instance.plugin.isFree(player)) {
			//this.instance.plugin.addons.msgFormat.formatAndSend("<header>MineMail <c1>- You were not charged for this.", player);
			//If player isFree then return true
			return true;
		}else {
			if (instance.hasAccount(player) == true) {
				Holdings balance = instance.getHoldings(player.getName());
				if (instance.hasEnough(amount, balance) == true) {
					//if player is not Free and has enough money return true
					this.instance.plugin.addons.msgFormat.formatAndSend("<header>MineMail <c1>- You were charged " + this.instance.formatAmount(amount), player);
					//player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "iConomy" + ChatColor.WHITE + "]" + ChatColor.GREEN + "Balance: " + balance);
					balance.subtract(amount);
					return true;

				} else { 
					this.instance.plugin.addons.msgFormat.formatAndSend("<header>MineMail <error>- You do not have enough money", player);
				}
				
			}
		}
		return false;
	}
	
}
			
		
		//if player is not free and does not have enough money return false

	

		
	
