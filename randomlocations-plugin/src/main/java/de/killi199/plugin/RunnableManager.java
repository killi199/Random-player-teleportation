package de.killi199.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.Iterator;

public class RunnableManager extends BukkitRunnable {
    int leftTime = TIME_BETWEEN_LOCATION_CHANGE;
    Plugin plugin;

    public RunnableManager(Plugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){
        if(leftTime % 10 == 0){
            System.out.println("Left time: " + leftTime + " seconds");
        }
        if(leftTime <= 10){
            Bukkit.broadcastMessage("Left time: " + leftTime + " seconds");
        }
        if(leftTime <= 0){
            leftTime = TIME_BETWEEN_LOCATION_CHANGE;
            if(Bukkit.getOnlinePlayers().size() >= 2){
                Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                Iterator<? extends Player> playerIterator = players.iterator();
                Player lastPlayer = playerIterator.next();
                String firstPlayerName = lastPlayer.getName();
                Location firstPlayerLocation = lastPlayer.getLocation();
                while(playerIterator.hasNext()){
                    Player currentPlayer = playerIterator.next();
                    System.out.println(currentPlayer.getName());
                    lastPlayer.teleport(currentPlayer);
                    lastPlayer.sendMessage("You changed your position with " + currentPlayer.getName());
                    lastPlayer = currentPlayer;
                }

                lastPlayer.teleport(firstPlayerLocation);
                lastPlayer.sendMessage("You changed your position with " + firstPlayerName);
            }
            else{
                Bukkit.broadcastMessage("Not the correct player count: " + Bukkit.getOnlinePlayers().size());
            }
        }

        leftTime -=1;
    }

    // in seconds
    private static final int TIME_BETWEEN_LOCATION_CHANGE = 300;
}
