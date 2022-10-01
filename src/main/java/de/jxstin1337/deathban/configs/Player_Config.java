package de.jxstin1337.deathban.configs;

// created by Justin Fiedler on 20.09.2022 with IntelliJ

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Player_Config {

    public static File file = new File("plugins//DeathBan//players.yml");
    public static YamlConfiguration configFile = YamlConfiguration.loadConfiguration(file);

    public static void saveConfig() {
        if (file.exists()) {
            try {
                configFile.save(file);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            try {
                configFile.set("Players", "");
                configFile.save(file);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }

    public static void setPlayerBanTime(String uuid, long seconds) {
        long current = System.currentTimeMillis();
        long banSecs = seconds * 1000;
        long result = current + banSecs;
        if (configFile.getString("Players." + uuid) == null) {
            List<String> list = configFile.getStringList("Players");
            list.add(uuid);
            configFile.set("Players", list);
        }
        if (seconds == -1) {
            configFile.set("Players." + uuid, -1);
        } else {
            configFile.set("Players." + uuid, result);
        }
        saveConfig();
    }

    public static boolean isPlayerBanned(String uuid) {
        if (configFile.get("Players." + uuid) == null) return false;
        if (configFile.getLong("Players." + uuid) == -1) return false;
        return configFile.getLong("Players." + uuid) > System.currentTimeMillis();
    }

    public static String convertMillisInDate(Player p) {
        Date remaining = new Date(configFile.getLong("Players." + p.getUniqueId().toString()));
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(remaining);
    }

}
