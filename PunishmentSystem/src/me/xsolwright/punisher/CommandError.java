package me.xsolwright.punisher;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cubixcraft.punishments.punisher.Main;

public class CommandError
{
  public static void noConsole(CommandSender sender)
  {
    sender.sendMessage(Main.getInstance().prefix + ChatColor.RED + "The console cannot display the GUI.");
  }

  public static void noPermission(Player p) {
    p.sendMessage(Main.getInstance().prefix + ChatColor.RED + "" + ChatColor.BOLD + "You do not have permission to do this.");
  }
}