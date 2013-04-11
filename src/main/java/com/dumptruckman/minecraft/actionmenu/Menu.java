package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Menu implements MenuItem {

    private final boolean multiSelection = false;
    @NotNull
    private final Map<Player, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;
    @NotNull
    protected final MenuItem backingItem;


    Menu(@NotNull final MenuModel model, @NotNull final MenuItem backingItem) {
        this.model = model;
        this.backingItem = backingItem;
        if (!multiSelection) {
            menuSelectorMap = new HashMap<Player, MenuSelector>(1);
        } else {
            menuSelectorMap = new WeakHashMap<Player, MenuSelector>();
        }
        setupSelectors(model);
    }

    private void setupSelectors(@NotNull final MenuModel model) {
        if (!multiSelection) {
            menuSelectorMap.put(null, new MenuSelector(model));
        } else {
            menuSelectorMap.clear();
        }
    }

    @Nullable
    public MenuItem getSelectedItem(@NotNull final Player sender) {
        return getSelector(sender).getSelectedItem();
    }

    public int getSelectedIndex(@NotNull final Player sender) {
        return getSelector(sender).getSelectedIndex();
    }

    public void selectNext(@NotNull final Player sender) {
        getSelector(sender).selectNext();
    }

    public void selectPrevious(@NotNull final Player sender) {
        getSelector(sender).selectPrevious();
    }

    public void selectIndex(@NotNull final Player sender, final int index) {
        getSelector(sender).selectIndex(index);
    }

    @NotNull
    protected MenuSelector getSelector(@NotNull final Player sender) {
        if (!multiSelection) {
            return menuSelectorMap.get(null);
        } else {
            if (!menuSelectorMap.containsKey(sender)) {
                menuSelectorMap.put(sender, new MenuSelector(model));
            }
            return menuSelectorMap.get(sender);
        }
    }

    @NotNull
    public MenuModel getModel() {
        return model;
    }

    public void setModel(@NotNull final MenuModel model) {
        this.model = model;
        this.setupSelectors(model);
    }

    @Override
    public void performAction(@NotNull final Player sender) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSelectable() {
        return backingItem.isSelectable();
    }

    @Override
    public void setSelectable(final boolean selectable) {
        backingItem.setSelectable(selectable);
    }
}
