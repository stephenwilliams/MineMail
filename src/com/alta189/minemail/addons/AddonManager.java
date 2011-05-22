package com.alta189.minemail.addons;

import com.alta189.minemail.MineMail;

public class AddonManager {
	public MineMail plugin;
	
	//Declare any addons here \\
	public PermissionsHandler PermManager = new PermissionsHandler(this);
	public MessageFormatter msgFormat = new MessageFormatter(this);
	public IConomyHandler iConomyManager = new IConomyHandler();
	public sqlFormatter formatSQL = new sqlFormatter(this);
	public ReadPaper managePaper = new ReadPaper(this);
	
	public AddonManager(MineMail instance) {
		this.plugin = instance;
	}
	
	public void initialize() {
		
		this.PermManager.setupPermissions();
		
		
	}

	public void close() {
		
	}
}
