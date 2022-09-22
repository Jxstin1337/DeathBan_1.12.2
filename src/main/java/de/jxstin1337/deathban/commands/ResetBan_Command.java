package de.jxstin1337.deathban.commands;

// created by Justin Fiedler on 20.09.2022 with IntelliJ

import de.jxstin1337.deathban.configs.Player_Config;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetBan_Command implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("resetban")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("wolfysystem.resetban")) {
                    if (args.length == 1) {
                        if (Bukkit.getOfflinePlayer(args[0]) != null) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                            if (Player_Config.isPlayerBanned(target.getUniqueId().toString())) {
                                Player_Config.setPlayerBanTime(target.getUniqueId().toString(), -1);
                                p.sendMessage("Der Bann vom Spieler " + args[0] + " wurde zurückgesetzt");
                            } else {
                                p.sendMessage("Der angegebene Spieler ist derzeit nicht gebannt.");
                            }
                        } else {
                            p.sendMessage("Der angegebene Spieler existiert nicht.");
                        }
                    } else {
                        p.sendMessage("Benutze: /resetban <Spieler>");
                    }
                }
            } else {
                if (args.length == 1) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        if (Player_Config.isPlayerBanned(target.getUniqueId().toString())) {
                            Player_Config.setPlayerBanTime(target.getUniqueId().toString(), -1);
                            sender.sendMessage("Der Bann vom Spieler " + args[0] + " wurde zurückgesetzt");
                        } else {
                            sender.sendMessage("Der angegebene Spieler ist derzeit nicht gebannt.");
                        }
                    } else {
                        sender.sendMessage("Der angegebene Spieler existiert nicht.");
                    }
                } else {
                    sender.sendMessage("Benutze: /resetban <Spieler>");
                }
            }
        }
        return false;
    }

}
