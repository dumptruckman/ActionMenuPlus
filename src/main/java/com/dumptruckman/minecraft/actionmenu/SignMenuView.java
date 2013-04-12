package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SignMenuView implements MenuView {

    @NotNull
    private final Sign sign;

    public SignMenuView(@NotNull final Sign sign) {
        this.sign = sign;
    }

    @Override
    public void showMenu(@NotNull final Menu menu, @NotNull final Player viewer) {
        final MenuItem item = menu.getSelectedItem(viewer);
        if (item != null) {
            final String[] lines = item.getText().split("\n");
            for (int i = 0; i < 4; i++) {
                if (i < lines.length) {
                    sign.setLine(i, lines[i]);
                } else {
                    sign.setLine(i, "");
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                sign.setLine(i, "");
            }
        }
        sign.update(true);
    }
}
