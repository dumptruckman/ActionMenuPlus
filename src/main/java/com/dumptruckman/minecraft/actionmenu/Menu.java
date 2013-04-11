package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Menu implements MenuItem {

    private final boolean multiSelection = false;
    @NotNull
    private final Map<CommandSender, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;

    Menu(@NotNull final MenuModel model) {
        this.model = model;
        if (!multiSelection) {
            Map<CommandSender, MenuSelector> map = new HashMap<CommandSender, MenuSelector>(1);
            map.put(null, null);
            menuSelectorMap = Collections.unmodifiableMap(map);
        } else {
            menuSelectorMap = new WeakHashMap<CommandSender, MenuSelector>();
        }
    }

    @Nullable
    public MenuItem getSelectedItem(@NotNull final CommandSender sender) {
        return getSelector(sender).getSelectedItem();
    }

    public int getSelectedIndex(@NotNull final CommandSender sender) {
        return getSelector(sender).getSelectedIndex();
    }

    @NotNull
    protected MenuSelector getSelector(@NotNull final CommandSender sender) {
        if (!multiSelection) {
            return menuSelectorMap.get(null);
        } else {
            return menuSelectorMap.get(sender);
        }
    }

    @NotNull
    public MenuModel getModel() {
        return model;
    }

    public void setModel(@NotNull final MenuModel model) {
        this.model = model;
    }


}
