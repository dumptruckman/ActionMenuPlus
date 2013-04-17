/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * An action to be performed for a player.
 * <p/>
 * This is simply a callback interface for {@link MenuItem} to use to execute some task when the menu item is "used".
 */
public interface Action {

    /**
     * Performs the designated action for the given user.
     *
     * @param user the player who caused the action to occur.
     */
    void performAction(@Nullable final Player user);
}
