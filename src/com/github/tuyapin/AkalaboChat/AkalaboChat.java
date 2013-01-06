package com.github.tuyapin.AkalaboChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class AkalaboChat extends JavaPlugin
{
    public static AkalaboChat plugin;
    public static LoadFiles files;
    
    public static boolean source = true;
    
    Logger logger = Logger.getLogger("Minecraft");
    
    public void onEnable()
    {
        plugin = this;
        this.logger.info("[ALC]AkalaboChat is Enabled!");
        
        //Check new version
        try {
            
            URL url = new URL("https://dl.dropbox.com/u/81948878/Bukkit/AkalaboChat.txt");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            
            http.connect();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String v = plugin.getDescription().getVersion().replace(".", "");
            String n = br.readLine();//.replace(".", "");
            if(Integer.valueOf(v) < Integer.valueOf(n.replace(".", "")))
            {
                this.logger.info("[ALC]New Version found! ->" + n);
            }
            
            Metrics metrics = new Metrics(this);
            metrics.start();
            
        } catch (Exception e) {}
        
        getCommand("alc").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Event(), this);
        
        files = new LoadFiles(this);
        files.load();
        
        this.loadConfiguration();
    }
    
    public void onDisable()
    {
        this.logger.fine("[ALC]AkalaboChat is Disabled!");
    }
    
    public void reload()
    {
        files = new LoadFiles(this);
        files.load();
    }
    
    public void exception(Exception e)
    {
        plugin.logger.warning("[ALC]Error : " + e.getClass().getName()  + "[" + e.getMessage() + "]");
        plugin.logger.warning("[ALC]Error : \tat " + e.getStackTrace()[0]);
    }
    
    public String getVersion()
    {
        return this.getDescription().getVersion();
    }
    
    public void loadConfiguration()
    {
        this.getConfig().options().copyDefaults(true);
        source = this.getConfig().getBoolean("alc.chat.source");
        this.saveConfig();
    }
}
