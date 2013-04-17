/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners.interaction;

import com.dumptruckman.minecraft.actionmenu.MenuView;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public interface ViewIdentifier<E extends Event> {

    @NotNull
    MenuView getViewFromEvent(@NotNull final E event);
}
