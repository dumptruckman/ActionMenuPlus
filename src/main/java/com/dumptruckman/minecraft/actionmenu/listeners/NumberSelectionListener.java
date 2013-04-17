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
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.ViewIdentifier;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class NumberSelectionListener<V extends MenuView> extends MenuListener<V, PlayerItemHeldEvent> {

    private final boolean alsoPerformAction;

    public NumberSelectionListener(@NotNull final Plugin plugin,
                                   @NotNull final ViewIdentifier<PlayerItemHeldEvent> identifier,
                                   @NotNull final EventPriority priority, final boolean cancelAfter,
                                   final boolean ignoreCancelled, final boolean updateViewAfterEvent,
                                   final boolean alsoPerformAction) {
        super(plugin, PlayerItemHeldEvent.class, identifier, priority, cancelAfter, ignoreCancelled, updateViewAfterEvent);
        this.alsoPerformAction = alsoPerformAction;
    }

    @Override
    protected void onEvent(@NotNull final PlayerItemHeldEvent event) {
        final MenuView view = getViewInteractingWith(event);
        if (view == null) {
            return;
        }
        final Menu menu = getMenu(view);
        if (menu == null) {
            return;
        }
        menu.selectIndex(event.getPlayer(), event.getNewSlot());
        if (alsoPerformAction) {
            final MenuItem item = menu.getItem(event.getNewSlot());
            if (item != null) {
                item.performAction(event.getPlayer());
            }
        }
        if (isUpdatingViewAfter()) {
            view.updateView(menu, event.getPlayer());
        }
        if (isCancellingAfter()) {
            event.setCancelled(true);
        }
    }

}
