package com.alta189.minemail.listeners;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.alta189.minemail.MineMail;
import com.iConomy.iConomy;

public class ServerMonitor extends ServerListener {
	private MineMail plugin;
	
	public ServerMonitor(MineMail instance) {
		this.plugin = instance;
	}
	@Override
	public void onPluginDisable(PluginDisableEvent event) {
	    	if (plugin.iConomy != null) {
	    		if (event.getPlugin().getDescription().getName().equals("iConomy")) {
	    			plugin.iConomy = null;
	    			plugin.log.info(plugin.logPrefix + "un-hooked from iConomy.");
	    		}
	    	}
	}

	@Override
	public void onPluginEnable(PluginEnableEvent event) {
		if (plugin.iConomy == null) {
			Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");

			if (iConomy != null) {
				if (iConomy.isEnabled() && iConomy.getClass().getName().equals("com.iConomy.iConomy")) {
					String iConomyVersion = plugin.getServer().getPluginManager().getPlugin("iConomy").getDescription().getVersion();
					plugin.iConomy = (iConomy)iConomy;
					plugin.log.info(plugin.logPrefix + "hooked into iConomy v " + iConomyVersion + ".");
				}
			}
		}	
	}	
}
