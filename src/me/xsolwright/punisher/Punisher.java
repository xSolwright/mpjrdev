package me.xsolwright.punisher;

import java.util.HashMap;

import me.xsolwright.punisher.inventories.PunishInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import cubixcraft.punishments.punisher.Main;

public class Punisher
  implements CommandExecutor
{
  public static HashMap<String, String> targetHM = new HashMap<String, String>();

  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (commandLabel.equalsIgnoreCase("punish")) {
      if (!(sender instanceof Player)) { CommandError.noConsole(sender); return false;
      }
      Player p = (Player)sender;

      if (!p.hasPermission("punish.open")) { CommandError.noPermission(p); return false; }
      if (args.length < 2) { p.sendMessage(Main.getInstance().prefix + ChatColor.RED + "/punish <player> <reason>"); return false;
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 1; i < args.length; i++) {
        sb.append(args[i]).append(" ");
      }
      String msg = sb.toString();

      OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);
      Main.getInstance().getConfig().set("Punish." + target.getName() + ".Reason", msg);
      targetHM.put(p.getName(), target.getName());
      PunishInventory.openPunish(p);
      Main.getInstance().saveConfig();
      Main.getInstance().reloadConfig();
    }
    return true;
  }
}