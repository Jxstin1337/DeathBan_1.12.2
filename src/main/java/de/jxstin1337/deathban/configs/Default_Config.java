package de.jxstin1337.deathban.configs;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Default_Config {

    public static File file = new File("plugins//DeathBan//config.yml");
    public static YamlConfiguration configFile = YamlConfiguration.loadConfiguration(file);

    public static YamlConfiguration getConfigFile() {
        return configFile;
    }

    public static void saveConfig() {
        if (file.exists()) {
            try {
                configFile.save(file);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            try {
                configFile.set("DeathBan.Prefix", "&4&lDeath&1&lBan &7» ");
                configFile.set("DeathBan.Messages.kickBanMessage",
                        "&cDu wurdest für 5 Minuten gebannt, da du gestorben bist.");
                configFile.set("DeathBan.Messages.isBannedMessage",
                        "&cDu bist aktuell für 5 Minuten gebannt, da du gestorben bist.");
                configFile.set("DeathBan.Permissions.canBan", "deathban.ban");
                configFile.set("DeathBan.Permissions.bypassBan", "deathban.bypass");
                configFile.set("DeathBan.BanSeconds", 300);
                configFile.save(file);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        }
    }

    public static String getPrefix() {
        return configFile.getString("DeathBan.Prefix")
                .replace("&", "§");
    }
}
