/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.views;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OneAtATimeSignView extends BlockStateView<Sign> {

    public OneAtATimeSignView(@NotNull final Sign sign) {
        super(sign);
    }

    @Override
    public void updateView(@NotNull final Menu menu, @NotNull final Player viewer) {
        final MenuItem item = menu.getSelectedItem(viewer);
        if (item != null) {
            final String[] lines = item.getText().split("\n");
            for (int i = 0; i < 4; i++) {
                if (i < lines.length) {
                    getBlockState().setLine(i, lines[i]);
                } else {
                    getBlockState().setLine(i, "");
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                getBlockState().setLine(i, "");
            }
        }
        update(viewer);
    }
}
