package com.alta189.minemail.addons;

import org.bukkit.ChatColor;

public class MessageFormatter {
	private AddonManager manageAddon;
	
	public MessageFormatter(AddonManager instance) {
		this.manageAddon = instance;
	}

	public ChatColor convertColorID(Integer colorID) {
		ChatColor result = null;
		
		switch (colorID) {
		  case 0: 
			  result = ChatColor.AQUA;  
		    break;
		  case 1: 
			  result = ChatColor.BLACK;
		    break;
		  case 2: 
			  result = ChatColor.BLUE;
		    break;
		  case 3: 
		    result = ChatColor.DARK_AQUA;
		    break;
		  case 4: 
		    result = ChatColor.DARK_GRAY;
		    break;
		  case 5: 
			    result = ChatColor.DARK_GREEN;
			    break;
		  case 6: 
			    result = ChatColor.DARK_PURPLE;
			    break;
		  case 7: 
			    result = ChatColor.DARK_RED;
			    break;
		  case 8: 
			    result = ChatColor.GOLD;
			    break;
		  case 9: 
			    result = ChatColor.GRAY;
			    break;
		  case 10: 
			    result = ChatColor.GREEN;
			    break;
		  case 11: 
			    result = ChatColor.LIGHT_PURPLE;
			    break;
		  case 12: 
			    result = ChatColor.RED;
			    break;
		  case 13: 
			    result = ChatColor.WHITE;
			    break;
		  case 14: 
			    result = ChatColor.YELLOW;
			    break;
			    
		  default: 
			  result = ChatColor.WHITE;
		}
		
		return result;
	}
}
