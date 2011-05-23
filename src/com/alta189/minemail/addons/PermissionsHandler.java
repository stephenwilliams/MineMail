package com.alta189.minemail.addons;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsHandler {
	private AddonManager manageAddon;
	private static PermissionHandler Permissions = null;
	public Boolean enablePermissions = false;
	
	public PermissionsHandler(AddonManager instance) {
		this.manageAddon = instance;
	}
	

	public void setupPermissions()
	  {
	    Plugin perms = manageAddon.plugin.getServer().getPluginManager().getPlugin("Permissions");
	    if (perms == null) {
	    	manageAddon.plugin.log.info(manageAddon.plugin.logPrefix + "No Permissions detected.");
	    	return;
	    }
	    PluginDescriptionFile permpdfFile = perms.getDescription();
	    
	    if (Permissions == null)
	    {
	      {
	    	manageAddon.plugin.getServer().getPluginManager().enablePlugin(perms);
	        Permissions = ((Permissions)perms).getHandler();
	        manageAddon.plugin.log.info(manageAddon.plugin.logPrefix + " Hooked into Permissions version " + permpdfFile.getVersion() + ".");
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
			  } else if (type.equals("paper")) {
			      if (Permissions != null) {
				        if (Permissions.has(player, "minemail.paper")) {
				        	return true;
				        }
				  } 
			  } else if (type.equals("free")) {
			      if (Permissions != null) {
				        if (Permissions.has(player, "minemail.free")) {
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

