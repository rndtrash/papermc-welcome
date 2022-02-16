package rndtrash.papermc.welcome;

import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ivan Kuzmenko on 17.02.2022.
 *
 * @author Copyright (c) 2022 Ivan Kuzmenko.
 */
public class WelcomePlugin extends JavaPlugin {
  protected String motd;
  protected String newbieLocal;
  protected String newbieGlobal;

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();

    motd = getConfig().getString("motd", "");
    newbieLocal = getConfig().getString("newbie.local", "");
    newbieGlobal = getConfig().getString("newbie.global", "");
  }

  /**
   * Say the message of the day.
   */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("motd")) {
      sayMotd(sender);
      return true;
    } else if (sender instanceof Player && command.getName().equalsIgnoreCase("first")) {
      welcomeNewbie((Player) sender);
      return true;
    }

    return false;
  }

  /**
   * Called when a player joins the game.
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    sayMotd(player);

    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
    if (!offlinePlayer.hasPlayedBefore()) {
      Bukkit.broadcastMessage(newbieGlobal.replace("{u}", player.getName()));
      welcomeNewbie(player);
    }
  }

  /**
   * Say the message of the day.
   */
  public void sayMotd(CommandSender p) {
    p.sendMessage(motd);
  }

  /**
   * Say the welcome message.
   */
  public void welcomeNewbie(Player p) {
    p.chat(newbieLocal.replace("{u}", p.getName()));
  }
}
