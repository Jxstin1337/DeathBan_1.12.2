package de.jxstin1337.deathban.events;

// created by Justin Fiedler on 20.09.2022 with IntelliJ

import de.jxstin1337.deathban.Main;
import de.jxstin1337.deathban.configs.Default_Config;
import de.jxstin1337.deathban.configs.Player_Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Death_Event implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() == null) return;
        if (!(event.getEntity().getPlayer().hasPermission(
                Default_Config.getConfigFile().getString("DeathBan.Permissions.bypassBan")))) {
            Player player = event.getEntity().getPlayer();
            Player_Config.setPlayerBanTime(player.getUniqueId().toString(),
                    Default_Config.getConfigFile().getLong("DeathBan.BanSeconds"));
            player.spigot().respawn();
            new BukkitRunnable() {

                @Override
                public void run() {
                    player.kickPlayer(Default_Config.getConfigFile()
                            .getString("DeathBan.Messages.kickBanMessage")
                            .replaceAll("&", "§"));
                }
            }.runTaskLater(Main.getPlugin(), 20L);
        }
    }

}
