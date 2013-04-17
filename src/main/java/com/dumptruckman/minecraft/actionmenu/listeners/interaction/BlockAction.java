/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners.interaction;

import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;

public enum BlockAction {
    LEFT_CLICK_BLOCK, RIGHT_CLICK_BLOCK,
    LEFT_CLICK_AIR, RIGHT_CLICK_AIR,
    LEFT_CLICK_ANY, RIGHT_CLICK_ANY,
    PHYSICAL;

    public boolean isActionSame(@NotNull final Action action) {
        switch (action) {
            case LEFT_CLICK_AIR:
                if (this != LEFT_CLICK_AIR && this != LEFT_CLICK_ANY) {
                    return false;
                }
                break;
            case LEFT_CLICK_BLOCK:
                if (this != LEFT_CLICK_BLOCK && this != LEFT_CLICK_ANY) {
                    return false;
                }
                break;
            case RIGHT_CLICK_AIR:
                if (this != RIGHT_CLICK_AIR && this != RIGHT_CLICK_ANY) {
                    return false;
                }
                break;
            case RIGHT_CLICK_BLOCK:
                if (this != RIGHT_CLICK_BLOCK && this != RIGHT_CLICK_ANY) {
                    return false;
                }
                break;
            case PHYSICAL:
                if (this != PHYSICAL) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return true;
    }
}
