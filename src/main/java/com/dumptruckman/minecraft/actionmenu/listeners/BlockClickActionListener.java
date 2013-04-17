/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuView;
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.BlockAction;
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.ViewIdentifier;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BlockClickActionListener<V extends MenuView> extends BlockClickListener<V> {

    public BlockClickActionListener(@NotNull final Plugin plugin,
                                    @NotNull final ViewIdentifier<PlayerInteractEvent> identifier,
                                    @NotNull final EventPriority priority, final boolean cancelAfter,
                                    final boolean ignoreCancelled, final boolean updateViewAfterEvent,
                                    @NotNull final BlockAction blockAction) {
        super(plugin, identifier, priority, cancelAfter, ignoreCancelled, updateViewAfterEvent, blockAction);
    }

    @Override
    protected void onPlayerInteract(@NotNull final PlayerInteractEvent event,
                                    @NotNull final MenuView view, @NotNull final Menu menu) {
        menu.performAction(event.getPlayer());
    }
}
