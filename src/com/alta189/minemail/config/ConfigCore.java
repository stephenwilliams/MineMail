package com.alta189.minemail.config;

import com.alta189.minemail.MineMail;

public class ConfigCore {
	private MineMail plugin;
	
	//Declare Config Classes\\
	public MainSettingsFile settingsFile;
	
	public ConfigCore(MineMail instance) {
		this.plugin = instance;
		
		//Assign Config Classes\\
		this.settingsFile = new MainSettingsFile(plugin);
	}
	
	public void initialize() { //Initializes all files instead of one
		settingsFile.load(false);
	}
	
	public void delete() { //Delete all file
		
	}


}
