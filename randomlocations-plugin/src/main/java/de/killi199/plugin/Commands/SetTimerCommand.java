package de.killi199.plugin.Commands;

import de.killi199.plugin.RandomLocations;
import de.killi199.plugin.RunnableManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetTimerCommand implements CommandExecutor {
    RandomLocations plugin;

    public SetTimerCommand(RandomLocations plugin){
        this.plugin = plugin;
        plugin.getCommand("setTimer").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        RunnableManager runnableManager = plugin.getRunnableManager();
        if(runnableManager.isRunning()){
            commandSender.sendMessage("Timer is running please stop the Timer");
            return false;
        }

        if(strings.length == 1){
            String inputString = strings[0];
            try{
                runnableManager.setTimeBetweenLocationChange(Integer.parseInt(inputString));
                Bukkit.broadcastMessage("Time was set to: " + inputString + " by " + commandSender.getName());
                return true;
            } catch (NumberFormatException ex){
                commandSender.sendMessage(inputString + " is not a number");
            }
        }
        else{
            commandSender.sendMessage("Wrong amount of Parameters");
        }

        return false;
    }
}
