package com.github.tuyapin.AkalaboChat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public final class Event
  implements Listener
{
<<<<<<< HEAD
  private final ALCPlugin plugin;

  public Event(ALCPlugin plugin)
  {
	  this.plugin = plugin;
	  plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @SuppressWarnings({ "static-access" })
  @EventHandler(priority=EventPriority.LOWEST)
  public final void onPlayerChat(PlayerChatEvent event) {
	  if(event.getMessage().startsWith("/")) return;
	  Converter ca = new Converter();
	  if(event.getMessage().getBytes().length == event.getMessage().length())
	  {
		  plugin.getServer().broadcastMessage(ChatColor.GOLD + "[ALC]" + ca.b(event.getMessage()));
	  }
  }
=======
    private final ALCPlugin plugin;
    
    public Event(ALCPlugin plugin)
    {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings({ "static-access" })
    @EventHandler(priority=EventPriority.LOWEST)
    public final void onPlayerChat(PlayerChatEvent event) {
        if(event.getMessage().startsWith("/")) return;
        Converter ca = new Converter();
        if(event.getMessage().getBytes().length == event.getMessage().length())
        {
            plugin.getServer().broadcastMessage(ChatColor.GOLD + "[ALC]" + ca.b(event.getMessage()));
        }
    }
>>>>>>> Bukkit 1.3.2-R0.2 #2364
}