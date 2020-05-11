package tsp.unlimitedchats.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tsp.unlimitedchats.util.FileUtils;
import tsp.unlimitedchats.util.Utils;

public class Listener_chatevent implements Listener {

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        for (String chat : FileUtils.config.getConfigurationSection("chats").getKeys(false)) {
            if (e.getMessage().startsWith(FileUtils.getString("chats." + chat + ".prefix"))) {
                if (FileUtils.getString("chats." + chat + ".prefix").isEmpty()) {
                    return;
                }
                if (e.getPlayer().hasPermission(FileUtils.getString("chats." + chat + ".permission"))) {
                    String msg = Utils.getMessage(e.getMessage().split(" "));
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission(FileUtils.getString("chats." + chat + ".permission"))) {
                            Utils.sendMessage(player, FileUtils.getString("chats." + chat + ".format")
                                    .replace("$message", msg));
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(Utils.translate(e.getPlayer(), FileUtils.getString("chats." + chat + ".format")
                            .replace("$message", msg)));
                    e.setCancelled(true);
                    break;
                }
                Utils.sendMessage(e.getPlayer(), FileUtils.getString("noPermission"));
                e.setCancelled(true);
                break;
            }
        }
    }

}
