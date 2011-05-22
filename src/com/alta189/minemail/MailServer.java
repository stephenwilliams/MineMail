package com.alta189.minemail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MailServer {
	public MineMail plugin;
	
	public MailServer(MineMail instance) {
		this.plugin = instance;
	}
	
	
	
	public int getUnreadCount(String playername)  {
		int count = 0;
		try {
			
			String query = "SELECT COUNT(*) as count FROM mails WHERE receiver = '" + playername + "' AND read = 0;";
			
			ResultSet result = plugin.dbManage.sqlQuery(query);
			
			count = result.getInt("count");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	}
	
	public boolean sendMail(String sender, String receiver, String message) {
		
	    String query = "INSERT INTO mails (sender, receiver, message) values ('" + sender + "', '" + receiver + "', '" + message + "');";
	      plugin.dbManage.insertQuery(query);
	      return true;
	  }
	
	public void getMail (Player player)  {
		try {
			String query = "SELECT id, sender, receiver, message, read FROM mails WHERE receiver = '" + player.getName().toLowerCase() + "' AND read=0;";
		    
		    ResultSet result = plugin.dbManage.sqlQuery(query);
		    
		   if (result == null) { 
			   plugin.log.info(plugin.logPrefix + "No result at get mail!");
			   return;
		   }
				while (result.next()) {
				      String sender = result.getString("sender");
				      String message = result.getString("message");
				      this.setRead(result.getInt("id"));
				      player.sendMessage(ChatColor.GREEN + "From: " + ChatColor.RED + sender + ChatColor.GREEN + " - " + ChatColor.RED + message);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
	    
	}
	
	public void ScheduleWipe() {
		plugin.ScheduledWipe = true; 
		@SuppressWarnings("unused")
		int DelayedWipe = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

	            public void run() {
	            	
	            	if(plugin.dbManage.checkTable("mails")){
						
						String query = "DELETE FROM mails;";
						
						plugin.dbManage.updateQuery(query);
						if (!plugin.dbManage.checkTable("mails")) { //check if the table exists, if not create it
							String query1 = "CREATE  TABLE mails (  'id' INTEGER PRIMARY KEY,  'sender' VARCHAR(80) NOT NULL ,  'receiver' VARCHAR(80) NOT NULL ,  'message' TEXT NOT NULL ,  'read' INT NOT NULL DEFAULT 0);";
							plugin.dbManage.createTable(query1);
						}
						for (Player player : plugin.getServer().getOnlinePlayers()) {
							player.sendMessage(ChatColor.GREEN + "MineMail - All messages have been wiped by Admin");
						}
					} else {
						plugin.log.severe(plugin.logPrefix + "Could not wipe database.");
					}
	            	
	                plugin.ScheduledWipe = false;
	            }
	        }, plugin.DelayWipeTime * 20L);
		
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.GREEN + "MineMail - A wipe has been scheduled by an admin. Please read your messages within 1 minute!");
		}
	
	}
	
	public void setRead(int ID) {
		String query = "UPDATE mails SET read=1 WHERE id=" + ID + ";";
	    
	   plugin.dbManage.updateQuery(query);
	}
}

