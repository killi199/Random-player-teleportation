package de.killi199.plugin.Commands;

import de.killi199.plugin.RandomLocations;
import de.killi199.plugin.RunnableManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private final RandomLocations plugin;

    public StartCommand(RandomLocations plugin){
        this.plugin = plugin;
        plugin.getCommand("startTimer").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        RunnableManager runnableManager = plugin.getRunnableManager();
        if(runnableManager.isRunning()){
            commandSender.sendMessage("Timer is already running");
            return false;
        }
        else{
            runnableManager.startRunnable();
            Bukkit.broadcastMessage("Timer started");
            return true;
        }
    }
}
