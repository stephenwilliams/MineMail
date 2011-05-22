	package com.alta189.minemail.command;

	
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
				Boolean payed = false;
				//iConomy Support\\
				
				if (!this.plugin.isFree(player)) {
					if (this.plugin.config.settingsFile.iConomyEnabled && this.plugin.iConomy != null) {
						if (this.plugin.addons.iConomyManager.hasAccount(player) && this.plugin.addons.iConomyManager.hasValidAccount(player)) {
							if (this.plugin.addons.iConomyManager.hasEnough(this.plugin.config.settingsFile.costReceive, this.plugin.addons.iConomyManager.getHoldings(player.getName()))) {
								this.plugin.addons.iConomyManager.subtract(this.plugin.config.settingsFile.costReceive, this.plugin.addons.iConomyManager.getHoldings(player.getName()));
								payed = true;
							} else {
								player.sendMessage("<error>You do not have enough in your iConomy Account");
							}
						} else {
							player.sendMessage("<error>You do not have a valid iConomy Account");
						}
					}
					
				}
				if (payed) {
					player.sendMessage("<header>MineMail <c1>- " + this.plugin.addons.iConomyManager.formatAmount(this.plugin.config.settingsFile.costReceive) + " was subtracted from your account");
				}
				plugin.mmServer.getMail(player);
			} else {
				player.sendMessage("<header>MineMail - <c1>No Messages");
			}
			
		}
		
		public void write(Player player, Command cmd, String commandLabel, String[] args) {
			String receiver = args[1].toLowerCase();
			String message = "";
			Integer count = 3;
			Boolean formatErrors = false;
			Boolean payed = false;

			while (count <= args.length) {
				if (count == 3) {
					message = args[count - 1];
				} else {
					message = message + " " + args[count - 1];
				}
				count = count + 1;
			}
			
			//iConomy Support\\
			
			if (!this.plugin.isFree(player)) {
				if (this.plugin.config.settingsFile.iConomyEnabled && this.plugin.iConomy != null) {
					if (this.plugin.addons.iConomyManager.hasAccount(player) && this.plugin.addons.iConomyManager.hasValidAccount(player)) {
						if (this.plugin.addons.iConomyManager.hasEnough(this.plugin.config.settingsFile.costSend, this.plugin.addons.iConomyManager.getHoldings(player.getName()))) {
							this.plugin.addons.iConomyManager.subtract(this.plugin.config.settingsFile.costSend, this.plugin.addons.iConomyManager.getHoldings(player.getName()));
						} else {
							player.sendMessage("<error>You do not have enough in your iConomy Account");
						}
					} else {
						player.sendMessage("<error>You do not have a valid iConomy Account");
					}
				}
				
			}
			
			if (this.plugin.addons.formatSQL.check(message)) {
				message = this.plugin.addons.formatSQL.fix(message);
				formatErrors = true;
			}
				plugin.mmServer.sendMail(player.getName(), receiver, message);
				
				if (formatErrors) {
					player.sendMessage("<c1>Your message has been sent, <error>but there were format errors.");
					player.sendMessage("<error>Use /mail format to see what characters are not allowed");
					if (payed) {
						player.sendMessage("<header>MineMail <c1>- " + this.plugin.addons.iConomyManager.formatAmount(this.plugin.config.settingsFile.costSend) + " was subtracted from your account");
					}
				} else {
					player.sendMessage("<c1>Your message has been sent");
					if (payed) {
						player.sendMessage("<header>MineMail <c1>- " + this.plugin.addons.iConomyManager.formatAmount(this.plugin.config.settingsFile.costSend) + " was subtracted from your account");
					}
				}
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
			player.sendMessage("<c1>/mail format <help>- Shows characters that will be removed from your msg");
			if (plugin.isAdmin(player, "paper")) {
				player.sendMessage("<c1>/mail paper <help>- Toggles reading mail by clicking with paper in hand.");
			}
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
			} else if (args[0].equalsIgnoreCase("help")) {
				player.sendMessage("<error>You do not have permission to use this command!");
			}
		}
		
		public void formatHelp(Player player, Command cmd, String commandLabel, String[] args) {
			
		}
		
		public void paper(Player player, Command cmd, String commandLabel, String[] args) {
			if (plugin.isAdmin(player, "paper")) {
				Boolean read = this.plugin.addons.managePaper.toggleReader(player.getName().toLowerCase());
				if (read) {
					player.sendMessage("<header>MineMail - <c1>Paper read is enabled");
				} else {
					player.sendMessage("<header>MineMail - <c1>Paper read is disabled");
				}
			} else {
				player.sendMessage("<error>You do not have permission to use this command!");
			}
		}
	}
