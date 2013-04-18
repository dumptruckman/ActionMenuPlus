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
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class InventoryClickListener<V extends InventoryView> extends MenuListener<V, InventoryClickEvent> {

    @NotNull
    private final Map<String, V> viewMap = new HashMap<String, V>();
    @NotNull
    private final InventoryAction inventoryAction;
    private final boolean topInventory;

    protected InventoryClickListener(@NotNull final Plugin plugin, @NotNull final EventPriority priority,
                                     final boolean cancelAfter, final boolean ignoreCancelled,
                                     final boolean updateViewAfterEvent,
                                     @NotNull final InventoryAction inventoryAction, boolean topInventory) {
        super(plugin, InventoryClickEvent.class, null, priority, cancelAfter, ignoreCancelled, updateViewAfterEvent);
        this.inventoryAction = inventoryAction;
        this.topInventory = topInventory;
    }

    @NotNull
    public InventoryAction getInventoryAction() {
        return inventoryAction;
    }

    @Override
    public void putView(@NotNull final V view, @NotNull final Menu menu) {
        super.putView(view, menu);
        viewMap.put(view.getInventory().getTitle(), view);
    }

    @Override
    public void removeView(@NotNull final V view) {
        super.removeView(view);
        viewMap.remove(view.getInventory().getTitle());
    }

    @Override
    protected void onEvent(@NotNull final InventoryClickEvent event) {
        if (!getInventoryAction().isActionSame(event)) {
            return;
        }
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        final Player player = (Player) event.getWhoClicked();
        final InventoryView view;
        if (topInventory) {
            view = viewMap.get(event.getView().getTopInventory().getTitle());
        } else {
            view = viewMap.get(event.getView().getBottomInventory().getTitle());
        }
        if (view == null) {
            return;
        }
        if (!view.getInventory().getViewers().contains(player)) {
            return;
        }
        final Menu menu = getMenu(view);
        if (menu == null) {
            return;
        }
        onInventoryClick(event, view, menu);
        if (isUpdatingViewAfter()) {
            view.updateView(menu, player);
        }
        if (isCancellingAfter()) {
            event.setCancelled(true);
        }
    }

    protected abstract void onInventoryClick(@NotNull final InventoryClickEvent event,
                                             @NotNull final InventoryView view, @NotNull final Menu menu);
}
