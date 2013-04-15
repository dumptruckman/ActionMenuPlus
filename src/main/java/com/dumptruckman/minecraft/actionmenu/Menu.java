package com.dumptruckman.minecraft.actionmenu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Represents a menu, which contains a menu model (the menu items) and manages the selection of menu items.
 * <p/>
 * This is the main class for menus in ActionMenuPlus.  This and {@link MenuView} are the only classes you must track
 * externally.
 * <p/>
 * All of the methods for manipulating the selection of the menu are available here.  Additionally, some simple methods
 * exist here for manipulating the model of the menu.  For more advanced model operations refer to the model returned
 * by {@link #getModel()}.
 */
public class Menu extends MenuItem implements Iterable<MenuItem> {

    private final boolean multiSelection;
    private final boolean wrapSelections;
    @NotNull
    private final Map<Player, MenuSelector> menuSelectorMap;
    @NotNull
    private MenuModel model;

    /**
     * Constructs a Menu with the given menu item text and an empty model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     */
    protected Menu(@NotNull final String title) {
        this(title, new MenuModel());
    }

    /**
     * Constructs a Menu with the given menu item text and model.
     * <p/>
     * This Menu will have a single selection for all users and the selector will wrap around the contents of the menu.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     */
    protected Menu(@NotNull final String title, @NotNull final MenuModel model) {
        this(title, model, false, true);
    }

    /**
     * Constructs a Menu with the given menu item text and model.
     * <p/>
     * With this constructor you may specify whether or not to use a single selection for all users or a unique
     * selection for each user.  You may also specify whether the menu selection should wrap around the menu contents.
     * See {@link com.dumptruckman.minecraft.actionmenu.MenuSelector#isWrapping()} for details on selection wrapping.
     *
     * @param title the text of this menu which can act as it's title or what it will appear as in other menus.
     * @param model the model to use for the menu which holds the menu items.
     * @param multiSelection true to use a unique selection for every menu user or false to use a single selection for
     *                       all users.
     * @param wrapSelections true to enable selection wrapping. See {@link MenuSelector#isWrapping()} for details on
     *                       selection wrapping.
     */
    protected Menu(@NotNull final String title, @NotNull final MenuModel model,
                   final boolean multiSelection, final boolean wrapSelections) {
        super(title);
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

    /**
     * Gets the selected item for the given user.
     *
     * @param user the user to get the selected item for.
     * @return the selected item of the menu for the given user or null if no item is selected.
     */
    @Nullable
    public final MenuItem getSelectedItem(@NotNull final Player user) {
        return getSelector(user).getSelectedItem();
    }

    /**
     * Gets the selected index for the given user.
     * <p/>
     * A less-than-zero results indicates that there no valid selection for the menu for the given user.
     *
     * @param user the user to get the selected index for.
     * @return the selected index of the menu for the given user.
     */
    public final int getSelectedIndex(@NotNull final Player user) {
        return getSelector(user).getSelectedIndex();
    }

    public final void selectNext(@NotNull final Player user) {
        getSelector(user).selectNext();
    }

    public final void selectPrevious(@NotNull final Player user) {
        getSelector(user).selectPrevious();
    }

    public final void selectIndex(@NotNull final Player user, final int index) {
        getSelector(user).selectIndex(index);
    }

    @NotNull
    private MenuSelector getSelector(@NotNull final Player user) {
        if (!multiSelection) {
            return menuSelectorMap.get(null);
        } else {
            if (!menuSelectorMap.containsKey(user)) {
                menuSelectorMap.put(user, new MenuSelector(model, wrapSelections));
            }
            return menuSelectorMap.get(user);
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

    /** {@inheritDoc} */
    @Override
    public ListIterator<MenuItem> iterator() {
        return getModel().iterator();
    }

    @NotNull
    public Menu addItem(@NotNull final MenuItem item) {
        getModel().add(item);
        return this;
    }

    @Nullable
    public MenuItem getItem(final int index) {
        if (index >= 0 && index < getModel().size()) {
            return getModel().get(index);
        }
        return null;
    }

    public void performSelectedAction(@NotNull final Player user) {
        final MenuItem item = getSelectedItem(user);
        if (item != null) {
            item.performAction(user);
        }
    }
}
