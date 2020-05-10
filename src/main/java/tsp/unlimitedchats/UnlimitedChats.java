package tsp.unlimitedchats;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tsp.unlimitedchats.command.Command_unlimitedchats;
import tsp.unlimitedchats.listener.Listener_chatevent;
import tsp.unlimitedchats.listener.Listener_commandevent;
import tsp.unlimitedchats.listener.Listener_servercommandevent;
import tsp.unlimitedchats.util.Metrics;
import tsp.unlimitedchats.util.Utils;

public class UnlimitedChats extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Metrics metrics = new Metrics(this, Utils.METRICS_ID);

        getCommand("unlimitedchats").setExecutor(new Command_unlimitedchats());

        Bukkit.getPluginManager().registerEvents(new Listener_chatevent(), this);
        Bukkit.getPluginManager().registerEvents(new Listener_commandevent(), this);
        Bukkit.getPluginManager().registerEvents(new Listener_servercommandevent(), this);
    }

}
