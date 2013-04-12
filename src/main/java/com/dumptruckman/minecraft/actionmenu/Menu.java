package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Menu extends MenuItem {

    private final boolean multiSelection = false;
    @NotNull
    private final Map<Player, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;

    public Menu(@NotNull final MenuModel model, @NotNull final String menuTitle) {
        super(menuTitle, Action.NO_ACTION);
        this.model = model;
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
    public MenuSelector getSelector(@NotNull final Player sender) {
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
}
