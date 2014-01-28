package net.tuyapin.alc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< HEAD
=======
import org.bukkit.ChatColor;
>>>>>>> 172
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class Event implements Listener {
<<<<<<< HEAD

    public static final Pattern URL = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?", Pattern.CASE_INSENSITIVE);

=======
    
    public static final Pattern URL = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+", Pattern.CASE_INSENSITIVE);
    
>>>>>>> 172
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
<<<<<<< HEAD

        //送信します。

        String text = e.getMessage();

        //http:// and https://を除外
        if(text.contains("http://") || e.getMessage().contains("https://"))
        {
            String URL = "";
            Matcher matcher = Event.URL.matcher(text);
=======
        //http:// and https://を除外
        if(e.getMessage().contains("http://") || e.getMessage().contains("https://"))
        {
            String URL = "";
            Matcher matcher = Event.URL.matcher(e.getMessage());
>>>>>>> 172
            if(matcher.find())
            {
                URL = matcher.group();
            }
<<<<<<< HEAD
            text = String.format(Converter.convert(text.replaceAll(URL, "%s")), URL);
        } else {
        	text = Converter.convert(text);
        }

        if(!AkalaboChat.source)
        {
            e.setCancelled(true);
            text = "<" + e.getPlayer().getName() + "> " + text;
        }
        AkalaboChat.plugin.getServer().broadcastMessage(text);
=======
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
>>>>>>> 172
    }

}
