package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TextMenu extends Menu implements TextMenuItem {

    public TextMenu(@NotNull final MenuModel model, @NotNull final String text) {
        super(model, new DefaultTextMenuItem(text));
    }

    @NotNull
    @Override
    public String getText() {
        return ((TextMenuItem) backingItem).getText();
    }

    @Override
    public void setText(@NotNull final String text) {
        ((TextMenuItem) backingItem).setText(text);
    }


}
