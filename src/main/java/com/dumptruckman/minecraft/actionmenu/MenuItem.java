package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface MenuItem {

    void performAction(@NotNull final Player sender);

    boolean isSelectable();

    void setSelectable(final boolean selectable);
}
