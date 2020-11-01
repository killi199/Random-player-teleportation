package de.killi199.plugin;

import de.killi199.plugin.Commands.StartCommand;
import de.killi199.plugin.Commands.StartTimerCommand;
import de.killi199.plugin.Commands.StopTimerCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomLocations extends JavaPlugin {
    private RunnableManager runnableManager;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TimerListener(), this);
        runnableManager = new RunnableManager();
        runnableManager.runTaskTimer(this, 0, 20);
        initializeCommands();
        System.out.println("RandomLocations enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("RandomLocations disabled");
    }

    public RunnableManager getRunnableManager() {
        return runnableManager;
    }

    private void initializeCommands(){
        new StopTimerCommand(this);
        new StartTimerCommand(this);
    }
}
