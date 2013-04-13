package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * An object which can display a {@link Menu}.
 * <p/>
 * This interface allows a Menu to be displayed on anything that can implement it.
 */
public interface MenuView {


    void showMenu(@NotNull final Menu menu, @NotNull final Player viewer);
}
