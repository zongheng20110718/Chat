package net.tuyapin.alc;

<<<<<<< HEAD
import java.util.logging.Logger;

import net.tuyapin.alc.engine.EnumEngine;

import org.bukkit.configuration.file.FileConfiguration;
=======
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

>>>>>>> 172
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class AkalaboChat extends JavaPlugin
{
    public static AkalaboChat plugin;
    public static LoadFiles files;
<<<<<<< HEAD

    public static boolean enable = true;
    public static EnumEngine engine = EnumEngine.ALC;
    public static String header = "ALC";
    public static boolean source = true;
    public static boolean chatcolor = true;
    public static String apikey = "dj0zaiZpPUNESFBQU0dNQmRhciZzPWNvbnN1bWVyc2VjcmV0Jng9MmM-";

    Logger logger = Logger.getLogger("Minecraft");

=======
    
    public static boolean source = true;
    public static String header = "ALC";
    public static boolean useChatColor = false;
    
    Logger logger = Logger.getLogger("Minecraft");
    
>>>>>>> 172
    public void onEnable()
    {
        plugin = this;
        this.logger.info("[ALC]AkalaboChat is Enabled!");
<<<<<<< HEAD

        try {

        	/*
        	 * http://tuyapin.net/application does not work.
        	 */

            //URL url = new URL("http://tuyapin.net/application/akalabochat.txt");
            //HttpURLConnection http = (HttpURLConnection)url.openConnection();

            //http.connect();

            //BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            //String v = plugin.getDescription().getVersion().replace(".", "");
            //String n = br.readLine();//.replace(".", "");
            //if(Integer.valueOf(v) < Integer.valueOf(n.replace(".", "")))
            //{
            //    this.logger.info("[ALC]New Version found! ->" + n);
            //}
            Metrics metrics = new Metrics(this);
            metrics.start();

        } catch (Exception e) {}

        this.getCommand("alc").setExecutor(new Commands());
        this.getServer().getPluginManager().registerEvents(new Event(), this);

        files = new LoadFiles(this);
        files.load();


        this.reloadConfig();
        FileConfiguration configuration = plugin.getConfig();

        AkalaboChat.enable = configuration.getBoolean("enable");
        AkalaboChat.engine = EnumEngine.getEnum(configuration.getString("engine"));
        AkalaboChat.header = configuration.getString("header");
        AkalaboChat.source = configuration.getBoolean("source");
        AkalaboChat.chatcolor = configuration.getBoolean("chatcolor");
        if(!configuration.getString("apikey").equals(""))
        {
        	AkalaboChat.apikey = configuration.getString("apikey");
        }
        this.saveConfig();
    }

=======
        
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
    
>>>>>>> 172
    public void onDisable()
    {
        this.logger.fine("[ALC]AkalaboChat is Disabled!");
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public void reload()
    {
        files = new LoadFiles(this);
        files.load();
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public void exception(Exception e)
    {
        plugin.logger.warning("[ALC]Error : " + e.getClass().getName()  + "[" + e.getMessage() + "]");
        plugin.logger.warning("[ALC]Error : \tat " + e.getStackTrace()[0]);
    }
<<<<<<< HEAD

=======
    
>>>>>>> 172
    public String getVersion()
    {
        return this.getDescription().getVersion();
    }
<<<<<<< HEAD
=======
    
    public void loadConfiguration()
    {
        if(this.getConfig().isBoolean("showSourceChat"))
        {
            source = this.getConfig().getBoolean("showSourceChat");
        } else {
            source = true;
            this.getConfig().createSection("showSourceChat");
            this.getConfig().set("showSourceChat", true);
        }
        header = this.getConfig().getString("header");
        useChatColor = this.getConfig().getBoolean("useChatColor");
        this.saveConfig();
    }
>>>>>>> 172
}
