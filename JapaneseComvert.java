package com.github.tuyapin;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public final class JapaneseComvert
  implements Listener
{
  private final ALCPlugin plugin;

  public JapaneseComvert(ALCPlugin plugin)
  {
	  this.plugin = plugin;
	  plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @SuppressWarnings({ "static-access" })
  @EventHandler(priority=EventPriority.LOWEST)
  public final void onPlayerChat(PlayerChatEvent event) {
	  if(event.getMessage().startsWith("/")) return;
	  Com ca = new Com();
	  if(event.getMessage().getBytes().length == event.getMessage().length())
	  {
		  plugin.getServer().broadcastMessage(ChatColor.GOLD + "[ALC]" + ca.b(event.getMessage()));
	  }
  }
}