package net.tuyapin.alc;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.tuyapin.alc.engine.EnumEngine;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

public class AkalaboChat extends JavaPlugin
{
	private static AkalaboChat plugin;

    public static boolean enable = true;
    public static EnumEngine engine = EnumEngine.ALC;
    public static String header = "ALC";
    public static boolean source = true;
    public static boolean chatcolor = true;
    public static String apikey = "dj0zaiZpPUNESFBQU0dNQmRhciZzPWNvbnN1bWVyc2VjcmV0Jng9MmM-";
    /* from 1.7.2.31 */
    public static boolean ignore = false;
    public static boolean dynmap = false;
    public static boolean ignoreuser = true;

    public boolean dynmapEnabled = false;

    private Map<String, String> dictionary;
    private List<String> japanese;
    private List<String> ignores;

    Logger logger = Logger.getLogger("Minecraft");

    public void onEnable()
    {
        plugin = this;
        this.logger.info("[ALC]AkalaboChat is Enabled!");

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
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        LoadFiles files = new LoadFiles(this);
        files.load();

        this.dictionary = files.getKanjiWords();
        this.japanese = files.getKanaWords();
        this.ignores = files.getIgnoreWords();

        //this.reloadConfig();
        FileConfiguration configuration = plugin.getConfig();

        AkalaboChat.enable = configuration.getBoolean("enable");
        AkalaboChat.engine = EnumEngine.getEnum(configuration.getString("engine"));
        AkalaboChat.header = configuration.getString("header");
        AkalaboChat.source = configuration.getBoolean("source");
        AkalaboChat.chatcolor = configuration.getBoolean("chatcolor");
        if(configuration.getString("apikey") != null)
        {
            AkalaboChat.apikey = configuration.getString("apikey");
        }
        AkalaboChat.ignore = configuration.getBoolean("ignore");
        AkalaboChat.dynmap = configuration.getBoolean("dynmap");
        AkalaboChat.ignoreuser = configuration.getBoolean("ignoreuser");
        this.saveConfig();
    }

    public void onDisable()
    {
        this.logger.fine("[ALC]AkalaboChat is Disabled!");
    }

    public void reload()
    {
        LoadFiles files = new LoadFiles(this);
        files.load();

        this.dictionary = files.getKanjiWords();
        this.japanese = files.getKanaWords();
        this.ignores = files.getIgnoreWords();
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

	public Map<String, String> getDictionary()
	{
		return dictionary;
	}

	public List<String> getJapanese()
	{
		return japanese;
	}

	public List<String> getIgnores()
	{
		return ignores;
	}

	public static AkalaboChat getPlugin()
	{
		return plugin;
	}
}
