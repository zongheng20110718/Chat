package net.tuyapin.alc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class Event implements Listener {

    public static final Pattern URL = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?", Pattern.CASE_INSENSITIVE);

    @EventHandler(priority=EventPriority.LOWEST)
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

        String text = e.getMessage();

        //http:// and https://を除外
        if(text.contains("http://") || e.getMessage().contains("https://"))
        {
            String URL = "";
            Matcher matcher = Event.URL.matcher(text);
            if(matcher.find())
            {
                URL = matcher.group();
            }
            text = String.format(Converter.convert(text.replaceAll(URL, "%s")), URL);
        } else {
            text = Converter.convert(text);
        }

        if(!AkalaboChat.source)
        {
            e.setCancelled(true);
            text = "<" + e.getPlayer().getName() + "> " + text;
        }

        text = ChatColor.GOLD + "[" + AkalaboChat.header + "]" + ChatColor.RESET + text;

        try {
        	AkalaboChat.plugin.getServer().broadcastMessage(text);
		} catch (Exception e2) {
			// TODO: handle exception
		}
        //AkalaboChat.plugin.getServer().broadcastMessage(new String(text.getBytes("UTF-8"), "UTF-8"));
    }

}
