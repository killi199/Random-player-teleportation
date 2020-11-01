package de.killi199.plugin.Commands;

import de.killi199.plugin.RandomLocations;
import de.killi199.plugin.RunnableManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopTimerCommand implements CommandExecutor {

    private final RandomLocations plugin;

    public StopTimerCommand(RandomLocations plugin){
        this.plugin = plugin;
        plugin.getCommand("stopTimer").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        RunnableManager runnableManager = plugin.getRunnableManager();
        if(runnableManager.isRunning()){
            runnableManager.stopRunnable();
            Bukkit.broadcastMessage("Timer stopped by " + commandSender.getName());
            return true;
        }
        else{
            commandSender.sendMessage("The timer isn't running");
            return false;
        }
    }
}
