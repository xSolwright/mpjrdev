package cubixcraft.punishments.punisher;

import me.xsolwright.punisher.Punisher;
import me.xsolwright.punisher.listeners.InventoryClick;
import me.xsolwright.punisher.listeners.PlayerChat;
import me.xsolwright.punisher.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
  public String prefix = ChatColor.RED + "" + ChatColor.BOLD + "[Punishments] " + ChatColor.RESET;
  private static Main instance;

  public void onEnable()
  {
    instance = this;

    PluginManager pm = Bukkit.getServer().getPluginManager();
    pm.registerEvents(new InventoryClick(), instance);
    pm.registerEvents(new PlayerJoin(), instance);
    pm.registerEvents(new PlayerChat(), instance);

    registerCommands();

    saveConfig();
  }

  public void onDisable()
  {
  }

  public static Main getInstance() {
    return instance;
  }

  private void registerCommands() {
    getCommand("punish").setExecutor(new Punisher());
  }
}