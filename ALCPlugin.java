package com.github.tuyapin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ALCPlugin extends JavaPlugin
{
  private static ALCPlugin plugin;
  private Load romaToHiraData;
  private Commands command;

  public void onEnable()
  {
    plugin = this;

    loadConfiguration();
    plugin.saveConfig();
    
    command = new Commands(this);
    getCommand("alc").setExecutor(command);
    
    this.romaToHiraData = new Load(this);
    new JapaneseComvert(this);
    this.romaToHiraData.a();

    getLogger().info("ALC enabled!");
  }

  public void onDisable()
  {
    plugin.saveConfig();

    getLogger().info("ALC disabled!");
  }
 
  public void reload()
  {
	  this.romaToHiraData = null;
	  this.romaToHiraData = new Load(this);
	  this.romaToHiraData.a();
	  
	  getLogger().info("ALC Reload!");
  }

  public static ALCPlugin getPlugin()
  {
    return plugin;
  }

  public void disablePlugin() {
    getPluginLoader().disablePlugin(this);
  }


  public Load getRomaToHiraData() {
    return this.romaToHiraData;
  }

  public String getVersion()
  {
    return getDescription().getVersion();
  }
  
  public void loadConfiguration()
  {
    FileConfiguration config;
    (
      config = plugin.getConfig())
      .options().copyDefaults(true);
    plugin.saveConfig();
  }

  
}