package rndtrash.papermc.welcome;

import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;


/**
 * Created by Ivan Kuzmenko on 17.02.2022.
 *
 * @author Copyright (c) 2022 Ivan Kuzmenko.
 */
public class WelcomePlugin extends JavaPlugin
{
	protected FileConfiguration config;
	
	protected string MOTD;
	protected string NewbieLocal;
	protected string NewbieGlobal;
	
	@Override
	public void onEnable()
	{
		PaperLib.suggestPaper(this);

		saveDefaultConfig();
		config = getConfig();
		
		MOTD = config.getString("motd", "");
		NewbieLocal = config.getString("newbie.local", "");
		NewbieGlobal = config.getString("newbie.global", "");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (command.getName().equalsIgnoreCase("motd"))
			SayMOTD(sender);
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if (player == null)
			return;
		
		SayMOTD(p);
		
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getName());
		if (offlinePlayer.hasPlayedBefore())
		{
			Bukkit.broadcastMessage(NewbieGlobal.replace("{u}", player.getName()));
			player.chat(NewbieLocal.replace("{u}", player.getName()));
		}
	}
	
	protected void SayMOTD(CommandSender p)
	{
		p.sendMessage(MOTD);
	}
}
