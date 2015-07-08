package me.xsolwright.punisher.inventories;

import cubixcraft.punishments.punisher.Main;
import me.xsolwright.punisher.Punisher;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PunishInventory
{
  public static void openPunish(Player p)
  {
    Inventory punish = Bukkit.getServer().createInventory(null, 54, Main.getInstance().prefix.trim());

    ItemStack ban = new ItemStack(Material.REDSTONE_BLOCK);
    ItemMeta banMeta = ban.getItemMeta();

    banMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Ban");
    ban.setItemMeta(banMeta);

    ItemStack mute = new ItemStack(Material.BOOK_AND_QUILL);
    ItemMeta muteMeta = mute.getItemMeta();

    muteMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Mute");
    mute.setItemMeta(muteMeta);

    ItemStack warn = new ItemStack(Material.PAPER);
    ItemMeta warnMeta = warn.getItemMeta();

    warnMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Warn");
    warn.setItemMeta(warnMeta);

    ItemStack kick = new ItemStack(Material.DEAD_BUSH);
    ItemMeta kickMeta = kick.getItemMeta();

    kickMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Kick");
    kick.setItemMeta(kickMeta);

    ItemStack skull = new ItemStack(Material.SKULL_ITEM);
    ItemMeta skullMeta = skull.getItemMeta();

    skullMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + (String)Punisher.targetHM.get(p.getName()));
    skull.setItemMeta(skullMeta);

    ItemStack unPunish = new ItemStack(Material.RED_ROSE);
    ItemMeta unPunishMeta = unPunish.getItemMeta();

    unPunishMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Un-Punish");
    unPunish.setItemMeta(unPunishMeta);

    punish.setItem(0, ban);
    punish.setItem(8, warn);
    punish.setItem(22, skull);
    punish.setItem(27, mute);
    punish.setItem(35, kick);
    punish.setItem(49, unPunish);

    p.openInventory(punish);
  }
}