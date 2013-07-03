/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners.interaction;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public enum InventoryAction {
    LEFT_CLICK, RIGHT_CLICK,
    SHIFT_LEFT_CLICK, SHIFT_RIGHT_CLICK,
    ALL_CLICK, ALL_NON_SHIFT_CLICK, ALL_SHIFT_CLICK;

    public boolean isActionSame(@NotNull final InventoryClickEvent event) {
        switch (this) {
            case LEFT_CLICK:
                return event.getClick() == ClickType.LEFT;
            case RIGHT_CLICK:
                return event.getClick() == ClickType.RIGHT;
            case SHIFT_LEFT_CLICK:
                return event.getClick() == ClickType.SHIFT_LEFT;
            case SHIFT_RIGHT_CLICK:
                return event.getClick() == ClickType.SHIFT_RIGHT;
            case ALL_CLICK:
                return true;
            case ALL_NON_SHIFT_CLICK:
                return (event.getClick() != ClickType.SHIFT_LEFT) && (event.getClick() != ClickType.SHIFT_RIGHT);
            case ALL_SHIFT_CLICK:
                return (event.getClick() == ClickType.SHIFT_LEFT) || (event.getClick() == ClickType.SHIFT_RIGHT);
            default:
                return false;
        }
    }
}
