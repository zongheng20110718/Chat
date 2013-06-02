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
    
    public static final Pattern URL = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+", Pattern.CASE_INSENSITIVE);
    
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
        //http:// and https://を除外
        if(e.getMessage().contains("http://") || e.getMessage().contains("https://"))
        {
            String URL = "";
            Matcher matcher = Event.URL.matcher(e.getMessage());
            if(matcher.find())
            {
                URL = matcher.group();
            }
            String text = Converter.convert(e.getMessage().replaceAll(URL, "%s"));
            if(!AkalaboChat.source)
            {
                e.setCancelled(true);
                text = "<" + e.getPlayer().getName() + "> " + text;
                AkalaboChat.plugin.getServer().broadcastMessage(String.format(text, URL));
            } else {
                AkalaboChat.plugin.getServer().broadcastMessage(ChatColor.GOLD + "[" + AkalaboChat.header + "] " + String.format(text, URL));
            }
        } else {
            String text = e.getMessage();
            if(!AkalaboChat.source)
            {
                e.setCancelled(true);
                text = "<" + e.getPlayer().getName() + "> " + Converter.convert(text);
                AkalaboChat.plugin.getServer().broadcastMessage(text);
            } else {
                AkalaboChat.plugin.getServer().broadcastMessage(ChatColor.GOLD + "[" + AkalaboChat.header + "] " + Converter.convert(text));
            }
        }
    }

}
