package com.dumptruckman.minecraft.actionmenu.plugin;

import com.dumptruckman.minecraft.actionmenu.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class TestPlugin extends JavaPlugin implements Listener {

    private final Menu menu = new Menu(new MenuModel(), "Test Menu");
    private MenuView view;
    private Sign sign;
    {
        menu.getModel().add(MenuItem.createItem("Explode\nSome\nShit", null));
        menu.getModel().add(MenuItem.createItem("Option 2", null));
        menu.getModel().add(MenuItem.createItem("Item with\nSome\n" + ChatColor.GOLD + "Color!\n" + ChatColor.RED + ChatColor.MAGIC + "WEEEE!!", null));
    }

    @Override
    public void onEnable() {
        //getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void playerInteract(@NotNull final PlayerInteractEvent event) {

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void blockPlaced(@NotNull final SignChangeEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (event.getBlock().getState() instanceof Sign) {
                    sign = (Sign) event.getBlock().getState();
                    if (view == null) {
                        view = new SignMenuView(sign);
                    }
                    view.showMenu(menu, event.getPlayer());
                }
            }
        });
    }

    @EventHandler
    public void itemChange(@NotNull final PlayerItemHeldEvent event) {
        if ((event.getNewSlot() > event.getPreviousSlot() && !(event.getPreviousSlot() == 0 && event.getNewSlot() == 8))
                || (event.getNewSlot() == 0 && event.getPreviousSlot() == 8)) {
            menu.selectNext(event.getPlayer());
        } else if (event.getNewSlot() < event.getPreviousSlot() || (event.getNewSlot() == 8 && event.getPreviousSlot() == 0)) {
            menu.selectPrevious(event.getPlayer());
        }
        if (view != null) {
            view.showMenu(menu, event.getPlayer());
        }
    }
}
