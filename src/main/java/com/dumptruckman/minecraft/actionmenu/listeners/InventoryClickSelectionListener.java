/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.InventoryAction;
import com.dumptruckman.minecraft.actionmenu.views.InventoryView;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class InventoryClickSelectionListener<V extends InventoryView> extends InventoryClickListener<V> {

    public InventoryClickSelectionListener(@NotNull final Plugin plugin, @NotNull final EventPriority priority,
                                           final boolean cancelAfter, final boolean ignoreCancelled,
                                           final boolean updateViewAfterEvent,
                                           @NotNull final InventoryAction inventoryAction) {
        super(plugin, priority, cancelAfter, ignoreCancelled, updateViewAfterEvent, inventoryAction);
    }

    @Override
    protected void onInventoryClick(@NotNull final InventoryClickEvent event, @NotNull final InventoryView view, @NotNull final Menu menu) {
        menu.selectIndex((Player) event.getWhoClicked(), event.getSlot());
        view.indexSelected(menu, (Player) event.getWhoClicked(), event.getSlot());
    }
}
