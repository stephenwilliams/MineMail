package com.alta189.minemail.addons;

import java.util.logging.Logger;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

import com.alta189.minemail.MineMail;
import com.iConomy.*;

public class IConomyHandler extends ServerListener{
	public final Logger log = Logger.getLogger("Minecraft");
	 private MineMail plugin;

	    public IConomyHandler(MineMail plugin) {
	        this.plugin = plugin;
	    }

	    
@Override
public void onPluginDisable(PluginDisableEvent event) {
    if (plugin.iConomy != null) {
        if (event.getPlugin().getDescription().getName().equals("iConomy")) {
            plugin.iConomy = null;
            log.info("[MineMail] un-hooked from iConomy.");
        }
    }
}
@Override
public void onPluginEnable(PluginEnableEvent event) {
    if (plugin.iConomy == null) {
        Plugin iConomy = plugin.getServer().getPluginManager().getPlugin("iConomy");

        if (iConomy != null) {
            if (iConomy.isEnabled() && iConomy.getClass().getName().equals("com.iConomy.iConomy")) {
                plugin.iConomy = (iConomy)iConomy;
                log.info("[MineMail] hooked into iConomy.");
            }
        }
    }
}
}
