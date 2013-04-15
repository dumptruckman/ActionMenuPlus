package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Action {

    void performAction(@NotNull final Player user);
}
