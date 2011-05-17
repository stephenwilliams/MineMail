package com.alta189.minemail.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

import com.alta189.minemail.MineMail;

public class MineMailPlayerListener extends PlayerListener{
	private MineMail plugin;
	
	public MineMailPlayerListener(MineMail instance) {
		this.plugin = instance;
	}

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		int unRead = plugin.mmServer.getUnreadCount(player.getName().toLowerCase());
		if (unRead >= 1) {
			player.sendMessage(ChatColor.GREEN + "MineMail - You have " + ChatColor.RED + unRead + ChatColor.GREEN + " new messages.");
		} else {
			player.sendMessage(ChatColor.GREEN
					+ "MineMail - You have no new mail.");
		}
		
	}
}
