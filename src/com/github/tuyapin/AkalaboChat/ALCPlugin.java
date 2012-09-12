package com.github.tuyapin.AkalaboChat;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class ALCPlugin extends JavaPlugin
{
	private static ALCPlugin plugin;
	private LoadFiles romaToHiraData;
	private Logger logger;

	public void onEnable()
	{
		plugin = this;
		logger = Logger.getLogger("Minecraft");

		loadConfiguration();
    
		getCommand("alc").setExecutor(new Commands(this));
    
		this.romaToHiraData = new LoadFiles(this);
		new Event(this);
		this.romaToHiraData.a();

		logger.info("ALC enabled!");
	}

	public void onDisable()
	{
		logger.info("ALC disabled!");
	}
 
	public void reload()
	{
		this.romaToHiraData = null;
		this.romaToHiraData = new LoadFiles(this);
		this.romaToHiraData.a();
		
		logger.info("ALC Reload!");
	}

	public static ALCPlugin getPlugin()
	{
		return plugin;
	}

	public LoadFiles getRomaToHiraData() {
		return this.romaToHiraData;
	}

	public String getVersion()
	{
		return getDescription().getVersion();
	}
  
	public void loadConfiguration()
	{
		plugin.saveConfig();
	}  
}