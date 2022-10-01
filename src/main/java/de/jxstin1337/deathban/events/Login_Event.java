package de.jxstin1337.deathban.events;

// created by Justin Fiedler on 20.09.2022 with IntelliJ

import de.jxstin1337.deathban.configs.Default_Config;
import de.jxstin1337.deathban.configs.Player_Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class Login_Event implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (Player_Config.isPlayerBanned(player.getUniqueId().toString())) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER,
                    Default_Config.getConfigFile().getString("DeathBan.Messages.isBannedMessage")
                            .replaceAll("&", "§")
                            .replaceAll("%time%", Player_Config.convertMillisInDate(player)));
        } else {
            Player_Config.setPlayerBanTime(player.getUniqueId().toString(), -1);
            event.allow();
        }
    }

}
