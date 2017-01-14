package com.jaredscarito.timbertrees.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by TheWolfBadger on 1/13/17.
 */
public class TimberTrees extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {}
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreakEvent(BlockBreakEvent evt) {
        if(isLog(evt.getBlock()) && !evt.isCancelled()) {
            Location loc = evt.getBlock().getLocation();
            while(isLog(loc.getBlock())) {
                loc.getWorld().dropItemNaturally(loc, new ItemStack(loc.getBlock().getType()));
                loc.getBlock().setType(Material.AIR);
                loc.add(0, 1, 0);
            }
        }
    }
    public boolean isLog(Block b) {
        Material t = b.getType();
        if(t == Material.LOG || t == Material.LOG_2) {
            return true;
        }
        return false;
    }
}
