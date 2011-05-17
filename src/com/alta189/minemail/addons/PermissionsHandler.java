package com.alta189.minemail.addons;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;


import com.alta189.minemail.MineMail;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsHandler {
	private MineMail plugin;
	private static PermissionHandler Permissions = null;
	public Boolean enablePermissions = false;
	
	public PermissionsHandler(MineMail instance) {
		this.plugin = instance;
	}
	

	public void setupPermissions()
	  {
	    Plugin perms = plugin.getServer().getPluginManager().getPlugin("Permissions");
	    if (perms == null) {
	    	plugin.log.info(plugin.logPrefix + "No Permissions detected.");
	    	return;
	    }
	    PluginDescriptionFile permpdfFile = perms.getDescription();
	    
	    if (Permissions == null)
	    {
	      {
	        plugin.getServer().getPluginManager().enablePlugin(perms);
	        Permissions = ((Permissions)perms).getHandler();
	        plugin.log.info(plugin.logPrefix + " Hooked into Permissions version " + permpdfFile.getVersion() + ".");
	        enablePermissions = true;
	      }
	    }
	  }
	  
	public boolean hasPermissions(Player player, String type) {
		  if (enablePermissions) {
			  if (type.equals("admin")) {
			      if (Permissions != null) {
			        if (Permissions.has(player, "minemail.admin")) {
			        	return true;
			        }
			      } 
			  } else if (type.equals("reload")) {
			      if (Permissions != null) {
				        if (Permissions.has(player, "minemail.reload")) {
				        	return true;
				        }
				  } 
			  } else if (type.equals("wipe")) {
			      if (Permissions != null) {
				        if (Permissions.has(player, "minemail.wipe")) {
				        	return true;
				        }
				  } 
			  }
			  return false;
		  } else {
			  return false;
		  }
	}
}

