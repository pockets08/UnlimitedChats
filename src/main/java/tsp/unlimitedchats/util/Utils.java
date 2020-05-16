package tsp.unlimitedchats.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Utils {

    public final static int METRICS_ID = 7476;
    public final static String PREFIX = "&7[&9UnlimitedChats&7] ";

    public static boolean isPAPIInstalled() {
        return Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(translate(sender, message));
    }

    public static String translate(CommandSender sender, String message) {
        if (isPAPIInstalled()) {
            if (sender instanceof Player) {
                message = PlaceholderAPI.setPlaceholders((Player) sender, message);
            }
        }
        return colorize(message);
    }

    public static String getMessage(String[] args) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }

        return builder.toString();
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Plugin getInstance() {
        return Bukkit.getPluginManager().getPlugin("UnlimitedChats");
    }

}
