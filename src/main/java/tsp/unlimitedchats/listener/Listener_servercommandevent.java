package tsp.unlimitedchats.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import tsp.unlimitedchats.util.FileUtils;
import tsp.unlimitedchats.util.Utils;

public class Listener_servercommandevent implements Listener {

    @EventHandler
    public void servercmdevent(ServerCommandEvent e) {
        for (String chat : FileUtils.config.getConfigurationSection("chats").getKeys(false)) {
            for (String command : FileUtils.getStringList("chats." + chat + ".commands")) {
                if (e.getCommand().startsWith(command)) {
                    String msg = Utils.getMessage(e.getCommand().split(" "));
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(FileUtils.getString("chats." + chat + ".permission"))) {
                            Utils.sendMessage(player, FileUtils.getString("chats." + chat + ".format")
                                    .replace("$message", msg));
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(Utils.translate(e.getSender(), FileUtils.getString("chats." + chat + ".format")
                            .replace("$message", msg)));
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

}
