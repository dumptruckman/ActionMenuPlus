package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Menu {

    @NotNull
    private final MenuSelector.SelectorMode selectorMode;
    @NotNull
    private final Map<CommandSender, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;

    Menu(@NotNull final MenuSelector.SelectorMode selectorMode, @NotNull final MenuModel model) {
        this.selectorMode = selectorMode;
        this.model = model;
        if (selectorMode == MenuSelector.SelectorMode.SINGLE_SELECTOR) {
            Map<CommandSender, MenuSelector> map = new HashMap<CommandSender, MenuSelector>(1);
            map.put(null, null);
            menuSelectorMap = Collections.unmodifiableMap(map);
        } else {
            menuSelectorMap = new WeakHashMap<CommandSender, MenuSelector>();
        }
    }

    @NotNull
    public MenuSelector getSelector(@NotNull final CommandSender sender) {
        if (selectorMode == MenuSelector.SelectorMode.SINGLE_SELECTOR) {
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
