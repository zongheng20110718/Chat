package com.github.tuyapin.AkalaboChat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class Event implements Listener {
    
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
        if(AkalaboChat.delNGChat)
        {
            if(e.getMessage().startsWith("[NGChat]"))
            {
                return;
            }
        }
        //
        AkalaboChat.plugin.getServer().broadcastMessage(ChatColor.GOLD + "[ALC]" + Converter.convert(e.getMessage()));
    }

}
