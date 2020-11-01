package de.killi199.plugin;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomLocations extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        System.out.println("RandomLocations enabled");
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(this, this);
        new RunnableManager(this).runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        System.out.println("RandomLocations disabled");
    }
}
