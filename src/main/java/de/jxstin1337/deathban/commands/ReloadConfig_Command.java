package de.jxstin1337.deathban.commands;

// created by Justin Fiedler on 01.10.2022 with IntelliJ

import de.jxstin1337.deathban.configs.Default_Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReloadConfig_Command implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("deathban")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        Default_Config.reloadConfig();
                        p.sendMessage(Default_Config.getPrefix() + "Die Config wurde neu geladen.");
                    } else {
                        Default_Config.reloadConfig();
                        sender.sendMessage(Default_Config.getPrefix() + "Die Config wurde neu geladen.");
                    }
                }
            } else {
                sender.sendMessage(Default_Config.getPrefix() + "Benutze: /deathban <reload | rl>");
            }
        }
        return false;
    }


}
