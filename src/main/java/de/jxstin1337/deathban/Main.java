package de.jxstin1337.deathban;

import de.jxstin1337.deathban.commands.ResetBan_Command;
import de.jxstin1337.deathban.configs.Default_Config;
import de.jxstin1337.deathban.configs.Player_Config;
import de.jxstin1337.deathban.events.Death_Event;
import de.jxstin1337.deathban.events.Login_Event;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        registerListeners();
        registerCommands();
        Player_Config.saveConfig();
        Default_Config.saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Death_Event(), getPlugin());
        pm.registerEvents(new Login_Event(), getPlugin());
    }

    private void registerCommands() {
        getCommand("resetban").setExecutor(new ResetBan_Command());
    }
}
