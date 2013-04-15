package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Represents a menu.
 * <p/>
 * This is the main class for menus in ActionMenuPlus.  This and {@link MenuView} are the only classes you must track
 * externally.
 * <p/>
 * All of the methods for manipulating the menu you should need are available here.
 */
public class Menu extends MenuItem {

    private final boolean multiSelection;
    private final boolean wrapSelections;
    @NotNull
    private final Map<Player, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;

    Menu(@NotNull final MenuModel model, @NotNull final String menuTitle, final boolean multiSelection, final boolean wrapSelections) {
        super(menuTitle, null);
        this.model = model;
        this.multiSelection = multiSelection;
        this.wrapSelections = wrapSelections;
        if (!multiSelection) {
            menuSelectorMap = new HashMap<Player, MenuSelector>(1);
        } else {
            menuSelectorMap = new WeakHashMap<Player, MenuSelector>();
        }
        setupSelectors(model);
    }

    private void setupSelectors(@NotNull final MenuModel model) {
        if (!multiSelection) {
            menuSelectorMap.put(null, new MenuSelector(model, wrapSelections));
        } else {
            menuSelectorMap.clear();
        }
    }

    @Nullable
    public final MenuItem getSelectedItem(@NotNull final Player sender) {
        return getSelector(sender).getSelectedItem();
    }

    public final int getSelectedIndex(@NotNull final Player sender) {
        return getSelector(sender).getSelectedIndex();
    }

    public final void selectNext(@NotNull final Player sender) {
        getSelector(sender).selectNext();
    }

    public final void selectPrevious(@NotNull final Player sender) {
        getSelector(sender).selectPrevious();
    }

    public final void selectIndex(@NotNull final Player sender, final int index) {
        getSelector(sender).selectIndex(index);
    }

    @NotNull
    private MenuSelector getSelector(@NotNull final Player sender) {
        if (!multiSelection) {
            return menuSelectorMap.get(null);
        } else {
            if (!menuSelectorMap.containsKey(sender)) {
                menuSelectorMap.put(sender, new MenuSelector(model, wrapSelections));
            }
            return menuSelectorMap.get(sender);
        }
    }

    @NotNull
    public final MenuModel getModel() {
        return model;
    }

    public final void setModel(@NotNull final MenuModel model) {
        this.model = model;
        this.setupSelectors(model);
    }
}
