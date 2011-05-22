package com.alta189.minemail.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
			this.plugin.addons.msgFormat.formatAndSend("<header>MineMail - <c1>You have no new mail.", player);
		}
		
	}
	public void onReadMailWithPaper(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (player.getItemInHand().equals(Material.PAPER) && this.plugin.addons.managePaper.status(event.getPlayer().getName().toLowerCase())) {
			if (event.getAction() != Action.LEFT_CLICK_BLOCK) {
				if (plugin.mmServer.getUnreadCount(player.getName().toLowerCase()) >= 1) {
					plugin.mmServer.getMail(player);
				} else {
					this.plugin.addons.msgFormat.formatAndSend("<header>MineMail - <c1>You have no new mail.", player);
				}
			}
		}
	}
}
