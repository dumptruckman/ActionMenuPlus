package com.dumptruckman.minecraft.actionmenu.views;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OneAtATimeSignView extends SignMenuView {

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
                    getSign().setLine(i, lines[i]);
                } else {
                    getSign().setLine(i, "");
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                getSign().setLine(i, "");
            }
        }
        updateSign(viewer);
    }
}
