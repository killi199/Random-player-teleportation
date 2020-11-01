package de.killi199.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class RunnableManager extends BukkitRunnable {
    private int leftTime;
    private boolean isRunning = false;
    // in seconds
    private int timeBetweenLocationChange = 300;

    public RunnableManager(){
        leftTime = timeBetweenLocationChange;
    }

    public void run(){
        if(!isRunning){
            return;
        }
        printLeftTime();

        if(leftTime <= 0){
            leftTime = timeBetweenLocationChange;
            if(Bukkit.getOnlinePlayers().size() >= 2){
                List<Player> players = convertToCollection(Bukkit.getOnlinePlayers());
                Collections.shuffle(players);
                changeAllPositions(players);
            }
            else{
                Bukkit.broadcastMessage("Not the correct player count: " + Bukkit.getOnlinePlayers().size());
            }
        }

        leftTime -=1;
    }

    public void startRunnable(){
        resetTime();
        this.isRunning = true;
    }

    public void stopRunnable(){
        this.isRunning = false;
    }

    public boolean isRunning(){
        return this.isRunning;
    }

    public void resetTime(){
        this.leftTime = this.timeBetweenLocationChange;
    }

    public void setTimeBetweenLocationChange(int timeBetweenLocationChange) {
        this.timeBetweenLocationChange = timeBetweenLocationChange;
    }

    private void printLeftTime(){
        if(leftTime % 10 == 0){
            System.out.println("Left time: " + leftTime + " seconds");
        }
        if(leftTime <= 10){
            Bukkit.broadcastMessage("Left time: " + leftTime + " seconds");
        }
    }

    private List<Player> convertToCollection(Collection<? extends Player> collection){
        List<Player> players = new ArrayList<>();
        for(Player pl: collection){
            players.add(pl);
        }
        return players;
    }

    private void changeAllPositions(List<Player> players){
        Iterator<Player> playerIterator = players.iterator();
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
}
