package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Action {

    Action NO_ACTION = new Action() {
        @Override
        public void performAction(@NotNull final Player user) { }
    };

    void performAction(@NotNull final Player user);
}
