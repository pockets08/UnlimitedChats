package tsp.unlimitedchats.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tsp.unlimitedchats.util.Utils;

public class Command_unlimitedchats implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            Utils.sendMessage(sender, Utils.PREFIX + "Running &9UnlimitedChats " + Utils.getInstance().getDescription().getVersion());
            Utils.sendMessage(sender, Utils.PREFIX + "Created by &9" + Utils.getInstance().getDescription().getAuthors());
            return true;
        }
        if (!sender.hasPermission("uc.admin")) {
            Utils.sendMessage(sender, Utils.PREFIX + "&cNo permission!");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            Utils.getInstance().saveConfig();
            Utils.sendMessage(sender, Utils.PREFIX + "&aConfiguration file has been reloaded!");
            return true;
        }
        Utils.sendMessage(sender, Utils.PREFIX + "Reload the plugin using &6/uc reload");
        return true;
    }

}
