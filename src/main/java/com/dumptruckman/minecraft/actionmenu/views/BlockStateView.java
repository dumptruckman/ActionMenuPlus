/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.views;

import com.dumptruckman.minecraft.actionmenu.MenuView;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class BlockStateView<S extends BlockState> implements MenuView {

    @NotNull
    private final S blockState;

    protected BlockStateView(@NotNull final S blockState) {
        this.blockState = blockState;
    }

    @NotNull
    public final S getBlockState() {
        return blockState;
    }

    public void update(@NotNull final Player viewer) {
        blockState.update(true);
    }
}
