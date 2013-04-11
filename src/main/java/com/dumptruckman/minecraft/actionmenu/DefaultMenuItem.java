package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

class DefaultMenuItem implements MenuItem {

    private boolean selectable = true;

    public void performAction(@NotNull final Player sender) {

    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(final boolean selectable) {
        this.selectable = selectable;
    }
}
