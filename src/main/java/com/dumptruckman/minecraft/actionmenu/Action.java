package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Represents an action to be performed for a player.
 * <p/>
 * This is simply a callback interface for {@link MenuItem} to use to execute some task when the menu item is "used".
 */
public interface Action {

    /**
     * Performs the designated action for the given user.
     *
     * @param user the player who caused the action to occur.
     */
    void performAction(@Nullable final Player user);
}
