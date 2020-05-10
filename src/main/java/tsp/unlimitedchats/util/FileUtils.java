package tsp.unlimitedchats.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class FileUtils {

    private static File configFile = new File(Utils.getInstance().getDataFolder(), "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static String getString(String identifier) {
        return Utils.colorize(config.getString(identifier));
    }

    public static List<String> getStringList(String identifier) {
        return config.getStringList(identifier);
    }

}
