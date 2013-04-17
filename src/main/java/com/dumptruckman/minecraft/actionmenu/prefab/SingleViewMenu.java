/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.prefab;

import com.dumptruckman.minecraft.actionmenu.*;
import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuItem;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class SingleViewMenu extends Menu implements MenuView {

    @NotNull
    private final MenuView view;

    protected SingleViewMenu(@NotNull final String title, @NotNull final MenuView view) {
        super(title);
        this.view = view;
    }

    protected SingleViewMenu(@NotNull final String title, @NotNull final MenuView view, @NotNull final MenuModel model) {
        super(title, model);
        this.view = view;
    }

    protected SingleViewMenu(@NotNull final String title, @NotNull final MenuView view, @NotNull final MenuModel model, final boolean multiSelection, final boolean wrapSelections) {
        super(title, model, multiSelection, wrapSelections);
        this.view = view;
    }

    @NotNull
    public MenuView getView() {
        return view;
    }

    @Override
    public void updateView(@NotNull final Menu menu, @NotNull final Player viewer) {
        view.updateView(menu, viewer);
    }

    @NotNull
    @Override
    public SingleViewMenu addItem(@NotNull final MenuItem item) {
        return (SingleViewMenu) super.addItem(item);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public SingleViewMenu setAction(@NotNull final Action action) {
        return (SingleViewMenu) super.setAction(action);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public SingleViewMenu setSelectable(final boolean selectable) {
        return (SingleViewMenu) super.setSelectable(selectable);
    }

    /** {@inheritDoc} */
    @NotNull
    @Override
    public SingleViewMenu setText(@NotNull final String text) {
        return (SingleViewMenu) super.setText(text);
    }

    /** {@inheritDoc} */
    @Override
    public SingleViewMenu setImage(@Nullable final Image image) {
        return (SingleViewMenu) super.setImage(image);
    }

    /** {@inheritDoc} */
    @Override
    public SingleViewMenu setItemStack(@Nullable final org.bukkit.inventory.ItemStack itemStack) {
        return (SingleViewMenu) super.setItemStack(itemStack);
    }

    /** {@inheritDoc} */
    @Override
    public SingleViewMenu setBlock(@Nullable final org.bukkit.block.Block block) {
        return (SingleViewMenu) super.setBlock(block);
    }

    /** {@inheritDoc} */
    @Override
    public SingleViewMenu setEffect(@Nullable final org.bukkit.Effect effect) {
        return (SingleViewMenu) super.setEffect(effect);
    }
}
