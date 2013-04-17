/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.plugin;

import com.dumptruckman.minecraft.actionmenu.MenuItem;
import com.dumptruckman.minecraft.actionmenu.prefab.Menus;
import com.dumptruckman.minecraft.actionmenu.prefab.SingleViewMenu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {

    SingleViewMenu menu;

    @Override
    public void onEnable() {
        menu = Menus.createSimpleInventoryMenu(this, "Test Menu", 9)
                .addItem(new MenuItem("Test 1").setItemStack(new ItemStack(Material.WOOL)))
                .addItem(new MenuItem("Test 2"))
                .addItem(new MenuItem("WOOOHOOOOOO").setItemStack(new ItemStack(Material.DIAMOND)).setSelectable(false));
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void playerJoin(final PlayerJoinEvent event) {
        getServer().getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                menu.updateView(menu, event.getPlayer());
            }
        }, 40L);
    }
}
