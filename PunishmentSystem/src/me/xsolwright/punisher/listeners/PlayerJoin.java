package me.xsolwright.punisher.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import cubixcraft.punishments.punisher.Main;

@SuppressWarnings("unused")
public class PlayerJoin implements Listener
{
  @EventHandler
  public void onPlayerJoin(PlayerLoginEvent e)
  {
    if (Main.getInstance().getConfig().contains("Punish.Ban." + e.getPlayer().getName())) {
      long endTime = Main.getInstance().getConfig().getLong("Punish.Ban." + e.getPlayer().getName() + ".End");
      if ((System.currentTimeMillis() / 1000L >= endTime) && (endTime != -1L)) {
        Main.getInstance().getConfig().set("Punish.Ban." + e.getPlayer().getName(), null);
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
      } else {
        e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Ban." + e.getPlayer().getName() + ".Reason")));
      }
    }
  }
}