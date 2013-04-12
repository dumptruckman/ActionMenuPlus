package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface MenuView {

    public abstract void showMenu(@NotNull final Menu menu, @NotNull final Player viewer);
}
