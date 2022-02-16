package rndtrash.papermc.welcome;

import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Ivan Kuzmenko on 17.02.2022.
 *
 * @author Copyright (c) 2022 Ivan Kuzmenko.
 */
public class WelcomePlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    PaperLib.suggestPaper(this);

    saveDefaultConfig();
  }
}
