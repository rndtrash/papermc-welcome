package rndtrash.papermc.welcome;

import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;

public class WelcomePlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();
  }
}
