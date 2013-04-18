/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import com.dumptruckman.minecraft.actionmenu.MenuView;
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.InventoryAction;
import com.dumptruckman.minecraft.actionmenu.views.InventoryView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class InventoryClickActionListener<V extends InventoryView> extends InventoryClickListener<V> {

    private final boolean alsoSelect;

    public InventoryClickActionListener(@NotNull final Plugin plugin, @NotNull final EventPriority priority,
                                        final boolean cancelAfter, final boolean ignoreCancelled,
                                        final boolean updateViewAfterEvent,
                                        @NotNull final InventoryAction inventoryAction,
                                        final boolean topInventory, final boolean alsoSelect) {
        super(plugin, priority, cancelAfter, ignoreCancelled, updateViewAfterEvent, inventoryAction, topInventory);
        this.alsoSelect = alsoSelect;
    }

    @Override
    protected void onInventoryClick(@NotNull final InventoryClickEvent event, @NotNull final InventoryView view, @NotNull final Menu menu) {
        MenuItem item = menu.getItem(event.getSlot());
        if (item != null && item.isSelectable()) {
            item.performAction((Player) event.getWhoClicked());
            if (alsoSelect) {
                menu.selectIndex((Player) event.getWhoClicked(), event.getSlot());
            }
        }
    }
}
