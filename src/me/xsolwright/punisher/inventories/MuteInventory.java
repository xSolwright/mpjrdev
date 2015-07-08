package me.xsolwright.punisher.inventories;

import cubixcraft.punishments.punisher.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MuteInventory
{
  public static void openMute(Player p)
  {
    Inventory mute = Bukkit.getServer().createInventory(null, 27, Main.getInstance().prefix + ChatColor.RED + "- Mute");

    ItemStack arrow = new ItemStack(Material.ARROW);
    ItemMeta arrowMeta = arrow.getItemMeta();

    arrowMeta.setDisplayName(ChatColor.YELLOW + "<- Back");
    arrow.setItemMeta(arrowMeta);

    ItemStack mute12 = new ItemStack(Material.WOOD_SPADE);
    ItemMeta mute12Meta = mute12.getItemMeta();

    mute12Meta.setDisplayName(ChatColor.RED + "12 Hour Mute");
    mute12.setItemMeta(mute12Meta);

    ItemStack mute1 = new ItemStack(Material.STONE_SPADE);
    ItemMeta mute1Meta = mute1.getItemMeta();

    mute1Meta.setDisplayName(ChatColor.RED + "1 Day Mute");
    mute1.setItemMeta(mute1Meta);

    ItemStack mute7 = new ItemStack(Material.IRON_SPADE);
    ItemMeta mute7Meta = mute7.getItemMeta();

    mute7Meta.setDisplayName(ChatColor.RED + "1 Week Mute");
    mute7.setItemMeta(mute7Meta);

    ItemStack mute30 = new ItemStack(Material.GOLD_SPADE);
    ItemMeta mute30Meta = mute30.getItemMeta();

    mute30Meta.setDisplayName(ChatColor.RED + "1 Month Mute");
    mute30.setItemMeta(mute30Meta);

    ItemStack mutePerm = new ItemStack(Material.DIAMOND_SPADE);
    ItemMeta mutePermMeta = mutePerm.getItemMeta();

    mutePermMeta.setDisplayName(ChatColor.RED + "Permanent Mute");
    mutePerm.setItemMeta(mutePermMeta);

    mute.setItem(0, arrow);
    mute.setItem(11, mute12);
    mute.setItem(12, mute1);
    mute.setItem(13, mute7);
    mute.setItem(14, mute30);
    mute.setItem(15, mutePerm);

    p.openInventory(mute);
  }
}