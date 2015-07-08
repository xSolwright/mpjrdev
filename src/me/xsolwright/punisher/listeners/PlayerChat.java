package me.xsolwright.punisher.listeners;

import cubixcraft.punishments.punisher.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@SuppressWarnings("unused")
public class PlayerChat
  implements Listener
{
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent e)
  {
    Player p = e.getPlayer();
    if (Main.getInstance().getConfig().contains("Punish.Mute." + p.getName())) {
      long endTime = Main.getInstance().getConfig().getLong("Punish.Mute." + e.getPlayer().getName() + ".End");
      if ((System.currentTimeMillis() / 1000L >= endTime) && (endTime != -1L)) {
        Main.getInstance().getConfig().set("Punish.Mute." + e.getPlayer().getName(), null);
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
      } else {
        e.setCancelled(true);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Mute." + p.getName() + ".Reason")));
      }
    }
  }
}