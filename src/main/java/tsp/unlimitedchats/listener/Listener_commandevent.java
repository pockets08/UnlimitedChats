package tsp.unlimitedchats.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import tsp.unlimitedchats.util.FileUtils;
import tsp.unlimitedchats.util.Utils;

public class Listener_commandevent implements Listener {

    @EventHandler
    public void commandevent(PlayerCommandPreprocessEvent e) {
        for (String chat : FileUtils.config.getConfigurationSection("chats").getKeys(false)) {
            for (String command : FileUtils.getStringList("chats." + chat + ".commands")) {
                if (e.getMessage().startsWith("/" + command)) {
                    if (e.getPlayer().hasPermission(FileUtils.getString("chats." + chat + ".permission"))) {
                        String msg = Utils.getMessage(e.getMessage().split(" "));
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.hasPermission(FileUtils.getString("chats." + chat + ".permission"))) {
                                Utils.sendMessage(player, FileUtils.getString("chats." + chat + ".format")
                                        .replace("$message", msg)
                                        .replace("$sender", e.getPlayer().getName()));
                            }
                        }
                        Bukkit.getConsoleSender().sendMessage(Utils.translate(e.getPlayer(), FileUtils.getString("chats." + chat + ".format")
                                .replace("$message", msg)
                                .replace("$sender", e.getPlayer().getName())));
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

}
