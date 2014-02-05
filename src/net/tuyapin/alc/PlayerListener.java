package net.tuyapin.alc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

@SuppressWarnings("deprecation")
public class PlayerListener implements Listener {

    private List<String> ignoreusers;

    public static final Pattern URL = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?", Pattern.CASE_INSENSITIVE);

	@EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(PlayerLoginEvent e)
    {
    	if(this.ignoreusers == null)
    	{
    		this.ignoreusers = AkalaboChat.getPlugin().getIgnores();
    	}
    	this.ignoreusers.add(e.getPlayer().getDisplayName());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(PlayerChatEvent e)
    {
        //コマンド無視
        if(e.getMessage().startsWith("/"))
        {
            return;
        }
        //2Byte文字を含む場合も除去(NihongoMOD,MinecraftIM対策)
        if(e.getMessage().getBytes().length != e.getMessage().length())
        {
            return;
        }

        //送信します。

        String text = this.convert(e.getMessage());

        text = ChatColor.GOLD + "[" + AkalaboChat.header + "]" + ChatColor.RESET + text;

        AkalaboChat.getPlugin().getServer().broadcastMessage(text);
    }

    private String convert(String text)
    {
        //user名が含まれている場合、それらを除外します。
        // dispname -> alcuser[dispname]
        Map<String, String> buffer = new HashMap<String, String>();

        if(AkalaboChat.ignoreuser)
        {
        	for(String string : this.ignoreusers)
        	{
        		if(text.contains(string))
        		{
        			buffer.put("alcuser" + string, string);
        			text = text.replace(string, "alcuser" + string);
        		}
        	}
        }

        //http:// and https://を除外
        if(text.contains("http://") || text.contains("https://"))
        {
            String URL = "";
            Matcher matcher = PlayerListener.URL.matcher(text);
            if(matcher.find())
            {
                URL = matcher.group();
            }
            text = String.format(Converter.convert(text.replaceAll(URL, "%s")), URL);
        } else {
            text = Converter.convert(text);
        }

        //user名を元に戻します。
        //alcuser[dispname] -> dispname
        if(AkalaboChat.ignoreuser)
        {
        	for(String key : buffer.keySet())
        	{
        		if(text.contains(key))
        		{
        			text = text.replace(key, buffer.get(key));
        		}
        	}
        }

        return text;
	}
}
