/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.views;

import com.dumptruckman.minecraft.actionmenu.MenuView;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class SignMenuView implements MenuView {

    @NotNull
    private final Sign sign;

    protected SignMenuView(@NotNull final Sign sign) {
        this.sign = sign;
    }

    @NotNull
    public final Sign getSign() {
        return sign;
    }

    public final void updateSign(@NotNull final Player viewer) {
        sign.update(true);
    }
}
