package me.xsolwright.punisher.inventories;

import cubixcraft.punishments.punisher.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BanInventory
{
  public static void openBan(Player p)
  {
    Inventory ban = Bukkit.getServer().createInventory(null, 27, Main.getInstance().prefix + ChatColor.RED + "- Ban");

    ItemStack arrow = new ItemStack(Material.ARROW);
    ItemMeta arrowMeta = arrow.getItemMeta();

    arrowMeta.setDisplayName(ChatColor.YELLOW + "<- Back");
    arrow.setItemMeta(arrowMeta);

    ItemStack ban12 = new ItemStack(Material.WOOD_SWORD);
    ItemMeta ban12Meta = ban12.getItemMeta();

    ban12Meta.setDisplayName(ChatColor.RED + "12 Hour Ban");
    ban12.setItemMeta(ban12Meta);

    ItemStack ban1 = new ItemStack(Material.STONE_SWORD);
    ItemMeta ban1Meta = ban1.getItemMeta();

    ban1Meta.setDisplayName(ChatColor.RED + "1 Day Ban");
    ban1.setItemMeta(ban1Meta);

    ItemStack ban7 = new ItemStack(Material.IRON_SWORD);
    ItemMeta ban7Meta = ban7.getItemMeta();

    ban7Meta.setDisplayName(ChatColor.RED + "1 Week Ban");
    ban7.setItemMeta(ban7Meta);

    ItemStack ban30 = new ItemStack(Material.GOLD_SWORD);
    ItemMeta ban30Meta = ban30.getItemMeta();

    ban30Meta.setDisplayName(ChatColor.RED + "1 Month Ban");
    ban30.setItemMeta(ban30Meta);

    ItemStack banPerm = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta banPermMeta = banPerm.getItemMeta();

    banPermMeta.setDisplayName(ChatColor.RED + "Permanent Ban");
    banPerm.setItemMeta(banPermMeta);

    ban.setItem(0, arrow);
    ban.setItem(11, ban12);
    ban.setItem(12, ban1);
    ban.setItem(13, ban7);
    ban.setItem(14, ban30);
    ban.setItem(15, banPerm);

    p.openInventory(ban);
  }
}