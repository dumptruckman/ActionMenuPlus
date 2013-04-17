/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.plugin;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import com.dumptruckman.minecraft.actionmenu.MenuView;
import com.dumptruckman.minecraft.actionmenu.views.OneAtATimeSignView;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class TestPlugin extends JavaPlugin implements Listener {

    private final Menu menu = Menu.newMenu("Test Menu");
    private MenuView view;
    private Sign sign;
    {
        menu.getModel().add(new MenuItem("Explode\nSome\nShit"));
        menu.getModel().add(new MenuItem("Option 2"));
        menu.getModel().add(new MenuItem("Item with\nSome\n" + ChatColor.GOLD + "Color!\n" + ChatColor.RED + ChatColor.MAGIC + "WEEEE!!"));
    }

    @Override
    public void onEnable() {
        //getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void blockPlaced(@NotNull final SignChangeEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (event.getBlock().getState() instanceof Sign) {
                    sign = (Sign) event.getBlock().getState();
                    if (view == null) {
                        view = new OneAtATimeSignView(sign);
                    }
                    view.updateView(menu, event.getPlayer());
                }
            }
        });
    }


}
