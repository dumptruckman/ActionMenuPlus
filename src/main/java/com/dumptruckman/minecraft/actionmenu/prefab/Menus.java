/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.prefab;

import com.dumptruckman.minecraft.actionmenu.listeners.InventoryClickActionListener;
import com.dumptruckman.minecraft.actionmenu.listeners.InventoryClickSelectionListener;
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.InventoryAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menus {

    private static final Map<Plugin, InventoryClickActionListener> MENU_INVENTORY_CLICK_ACTION_LISTENERS = new HashMap<Plugin, InventoryClickActionListener>();
    private static final Map<Plugin, InventoryClickSelectionListener> MENU_INVENTORY_CLICK_SELECTION_LISTENERS = new HashMap<Plugin, InventoryClickSelectionListener>();


    public static SingleViewMenu createSimpleInventoryMenu(@NotNull final Plugin plugin, @NotNull final String title,
                                                           final int inventorySize) {
        final PluginManager pm = plugin.getServer().getPluginManager();
        if (!MENU_INVENTORY_CLICK_ACTION_LISTENERS.containsKey(plugin)) {
            pm.registerEvents(new PluginDisableListener(plugin), plugin);
            InventoryClickActionListener listener = new InventoryClickActionListener(plugin, EventPriority.NORMAL, true, false, false, InventoryAction.RIGHT_CLICK, true, true);
            pm.registerEvents(listener, plugin);
            MENU_INVENTORY_CLICK_ACTION_LISTENERS.put(plugin, listener);
        }
        if (!MENU_INVENTORY_CLICK_SELECTION_LISTENERS.containsKey(plugin)) {
            InventoryClickSelectionListener listener = new InventoryClickSelectionListener(plugin, EventPriority.NORMAL, true, false, false, InventoryAction.LEFT_CLICK, true);
            pm.registerEvents(listener, plugin);
            MENU_INVENTORY_CLICK_SELECTION_LISTENERS.put(plugin, listener);
        }
        SimpleInventoryMenu menu = new SimpleInventoryMenu(plugin, title, inventorySize);
        MENU_INVENTORY_CLICK_ACTION_LISTENERS.get(plugin).putView(menu, menu);
        MENU_INVENTORY_CLICK_SELECTION_LISTENERS.get(plugin).putView(menu, menu);
        return menu;
    }

    private static class PluginDisableListener implements Listener {

        private final Plugin plugin;

        PluginDisableListener(@NotNull final Plugin plugin) {
            this.plugin = plugin;
        }

        @EventHandler
        public void pluginDisable(@NotNull final PluginDisableEvent event) {
            if (event.getPlugin().equals(plugin)) {
                MENU_INVENTORY_CLICK_ACTION_LISTENERS.remove(plugin);
            }
        }
    }
}
