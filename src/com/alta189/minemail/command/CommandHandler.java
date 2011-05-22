	package com.alta189.minemail.command;

	import org.bukkit.ChatColor;
import org.bukkit.command.Command;
	import org.bukkit.entity.Player;

	import com.alta189.minemail.MineMail;

	public class CommandHandler {
		private MineMail plugin;
		
		public CommandHandler(MineMail instance) {
			this.plugin = instance;
		}
		
		public void read(Player player, Command cmd, String commandLabel, String[] args) {
			if (plugin.mmServer.getUnreadCount(player.getName().toLowerCase()) >= 1) {
				plugin.mmServer.getMail(player);
			} else {
				player.sendMessage("<header>MineMail - <c1>No Messages");
			}
			
		}
		
		public void write(Player player, Command cmd, String commandLabel, String[] args) {
			String receiver = args[1].toLowerCase();
			String message = "";
			Integer count = 3;

			while (count <= args.length) {
				if (count == 3) {
					message = args[count - 1];
				} else {
					message = message + " " + args[count - 1];
				}
				count = count + 1;
			}

				plugin.mmServer.sendMail(player.getName(), receiver, message);
				player.sendMessage("<c1>Your message has been sent");
	
				plugin.notifyReceiver(receiver); 
			
		}
		
		public void wipe(Player player, Command cmd, String commandLabel, String[] args) {
			try {
				if (plugin.isAdmin(player, "wipe")){
					if(plugin.dbManage.checkTable("mails")){
						if (!plugin.ScheduledWipe) plugin.mmServer.ScheduleWipe();
						player.sendMessage("<header>The database will be wiped in 1 minute!");
					} else {
						player.sendMessage("<error>Could not wipe database.");
					}
				} else {
					player.sendMessage("<error>You do not have permission to use this command!");
				}
			} catch (Exception ex) {
				plugin.log.severe(plugin.logPrefix + "Error at command delete: " + ex);
			}
		}
		
		public void help(Player player, Command cmd, String commandLabel, String[] args) {
			player.sendMessage("<header>---   MineMail Help   ---");
			player.sendMessage("<c1>/mail write [player] [message] <help>- Send a message");
			player.sendMessage("<c1>/mail read <help>- Read your messages");
			this.admin(player, cmd, commandLabel, args);
		}
		
		public void reload(Player player, Command cmd, String commandLabel, String[] args) {
			if (plugin.isAdmin(player, "reload")) {
				player.sendMessage("<header>---   MineMail Reloading   ---");
				//Reload Database\\
				plugin.dbManage.close();
				plugin.log.info("[MineMail] Database Closed.");
				plugin.dbManage.initialize();
				plugin.log.info("[MineMail] Database Loaded.");
				player.sendMessage("<c1>Database has been reloaded");
				
				//Reload Settings\\
				plugin.log.info("[MineMail] Settings Reloading.");
				plugin.config.initialize();
				plugin.log.info("[MineMail] Settings Reloaded.");
				player.sendMessage("<c1>Settings have been reloaded");
				player.sendMessage("<header>---   MineMail Reloaded   ---");
				
			} else {
				player.sendMessage("<error>You do not have permission to use this command!");
			}
		}
		
		public void admin(Player player, Command cmd, String commandLabel, String[] args) {
			if (plugin.isAdmin(player, "wipe|reload")) {
				player.sendMessage("<c1>/mail reload <help>- Reload Mail System and settings!");
				player.sendMessage("<c1>/mail wipe <help>- Wipes the database");
				
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			}
		}
		
		public void formatHelp(Player player, Command cmd, String commandLabel, String[] args) {
			
		}
	}
