package me.xsolwright.punisher.listeners;

import me.xsolwright.punisher.CommandError;
import cubixcraft.punishments.punisher.Main;
import me.xsolwright.punisher.Punisher;
import me.xsolwright.punisher.inventories.BanInventory;
import me.xsolwright.punisher.inventories.MuteInventory;
import me.xsolwright.punisher.inventories.PunishInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick
  implements Listener
{
  @SuppressWarnings("deprecation")
@EventHandler
  public void onInventoryInteract(InventoryClickEvent e)
  {
    Player p = (Player)e.getWhoClicked();

    if (e.getInventory().getName().equalsIgnoreCase(Main.getInstance().prefix)) {
      e.setCancelled(true);
      if ((e.getCurrentItem().getType() == null) || (e.getCurrentItem().getType() == Material.AIR) || (e.getCurrentItem().getType() == Material.SKULL_ITEM)) {
        p.closeInventory();
        Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
        Punisher.targetHM.remove(p.getName());
      } else if ((e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) && (p.hasPermission("punish.ban"))) {
            BanInventory.openBan(p);
      } else if ((e.getCurrentItem().getType() == Material.BOOK_AND_QUILL) && (p.hasPermission("punish.mute"))) {
        MuteInventory.openMute(p);
      } else if ((e.getCurrentItem().getType() == Material.PAPER) && (p.hasPermission("punish.warn"))) {
        p.closeInventory();
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        if (target == null) {
          p.sendMessage(Main.getInstance().prefix + ChatColor.RED + (String)Punisher.targetHM.get(p.getName()) + " is not online.");
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          target.sendMessage(Main.getInstance().prefix + ChatColor.GOLD + "" + ChatColor.BOLD + p.getName() + " warned you for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          p.sendMessage(Main.getInstance().prefix + ChatColor.GOLD + "Successfully warned " + target.getName() + ".");
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if ((e.getCurrentItem().getType() == Material.DEAD_BUSH) && (p.hasPermission("punish.kick"))) {
        p.closeInventory();
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        if (target == null) {
          p.sendMessage(Main.getInstance().prefix + ChatColor.RED + (String)Punisher.targetHM.get(p.getName()) + " is not online.");
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          target.kickPlayer(ChatColor.RED + "" + ChatColor.BOLD + p.getName() + " kicked you for > Reason: " + ChatColor.YELLOW + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " kicked " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if ((e.getCurrentItem().getType() == Material.BOOK_AND_QUILL) && (p.hasPermission("punish.mute"))) {
        MuteInventory.openMute(p);
      } else if ((e.getCurrentItem().getType() == Material.RED_ROSE) && (p.hasPermission("punish.unpunish"))) {
        if ((Main.getInstance().getConfig().contains("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()))) || (Main.getInstance().getConfig().contains("Punish.Mute." + (String)Punisher.targetHM.get(p.getName())))) {
          p.closeInventory();
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()), null);
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()), null);
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " un-punished " + (String)Punisher.targetHM.get(p.getName()) + ".");
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          p.closeInventory();
          p.sendMessage(Main.getInstance().prefix + ChatColor.RED + (String)Punisher.targetHM.get(p.getName()) + " has not been punished.");
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else {
        p.closeInventory();
        CommandError.noPermission(p);
        Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
        Punisher.targetHM.remove(p.getName());
      }

    }

    if (e.getInventory().getName().equalsIgnoreCase(Main.getInstance().prefix + ChatColor.RED + "- Mute")) {
      e.setCancelled(true);
      if ((e.getCurrentItem().getType() == null) || (e.getCurrentItem().getType() == Material.AIR)) {
        p.closeInventory();
        Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
        Punisher.targetHM.remove(p.getName());
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
      } else if (e.getCurrentItem().getType() == Material.WOOD_SPADE) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 3600L * 12L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 12 hours > Reason: &b&l" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 3600L * 12L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 12 hours > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.STONE_SPADE) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 1 day > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 1 day > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.IRON_SPADE) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 7L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 1 week > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 7L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 1 week > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.GOLD_SPADE) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 30L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 1 month > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 30L;
          Main.getInstance().getConfig().set("Punish.Muted." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Muted." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted for 1 month > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.DIAMOND_SPADE) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long endTime = -1L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted permanently > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long endTime = -1L;
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " muted " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Mute." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were muted permanently > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.ARROW) {
        PunishInventory.openPunish(p);
      }

    }

    if (e.getInventory().getName().equalsIgnoreCase(Main.getInstance().prefix + ChatColor.RED + "- Ban")) {
      e.setCancelled(true);
      if ((e.getCurrentItem().getType() == null) || (e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) && (p.hasPermission("punish.ban"))) {
    	  BanInventory.openBan(p);
        Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
        Punisher.targetHM.remove(p.getName());
        Main.getInstance().saveConfig();
        Main.getInstance().reloadConfig();
      } else if (e.getCurrentItem().getType() == Material.WOOD_SWORD) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 3600L * 12L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 12 hours > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 3600L * 12L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 12 hours > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason")));
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.STONE_SWORD) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 1 day > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 1 day > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason")));
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.IRON_SWORD) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 7L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 1 week > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 7L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString())); Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 1 week > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason")));
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.GOLD_SWORD) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 30L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 1 month > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long startTime = System.currentTimeMillis();
          long endTime = startTime * 24L * 3600L * 30L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned for 1 month > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason")));
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
        Player target = Bukkit.getServer().getPlayer((String)Punisher.targetHM.get(p.getName()));
        p.closeInventory();
        if (target == null) {
          long endTime = -1L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned permanently > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          Punisher.targetHM.remove(p.getName());
        } else {
          long endTime = -1L;
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".End", Long.valueOf(endTime));
          Bukkit.broadcastMessage(Main.getInstance().prefix + ChatColor.RED + p.getName() + " banned " + (String)Punisher.targetHM.get(p.getName()) + " for: " + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", "&9&lYou were banned permanently > Reason: &e" + Main.getInstance().getConfig().getString(new StringBuilder("Punish.").append((String)Punisher.targetHM.get(p.getName())).append(".Reason").toString()));
          Main.getInstance().getConfig().set("Punish." + (String)Punisher.targetHM.get(p.getName()) + ".Reason", null);
          Main.getInstance().saveConfig();
          Main.getInstance().reloadConfig();
          target.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("Punish.Ban." + (String)Punisher.targetHM.get(p.getName()) + ".Reason")));
          Punisher.targetHM.remove(p.getName());
        }
      } else if (e.getCurrentItem().getType() == Material.ARROW) {
        PunishInventory.openPunish(p);
      }
    }
  }
}