/**
 * Copyright (c) 2013. dumptruckman
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.dumptruckman.minecraft.actionmenu.listeners;

import com.dumptruckman.minecraft.actionmenu.Menu;
import com.dumptruckman.minecraft.actionmenu.MenuView;
import com.dumptruckman.minecraft.actionmenu.listeners.interaction.ViewIdentifier;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public abstract class MenuListener<V extends MenuView, E extends Event> implements Listener, EventExecutor {

    @NotNull
    private final Map<V, Menu> menuMap = new WeakHashMap<V, Menu>();
    @Nullable
    private final ViewIdentifier<E> identifier;
    @NotNull
    private final Class<E> eventClass;
    private final boolean cancelAfter;
    private final boolean ignoreCancelled;
    private final boolean updateViewAfterEvent;

    protected MenuListener(@NotNull final Plugin plugin, @NotNull final Class<E> eventClass,
                           @Nullable final ViewIdentifier<E> identifier, @NotNull final EventPriority priority,
                           final boolean cancelAfter, final boolean ignoreCancelled,
                           final boolean updateViewAfterEvent) {
        RegisteredListener registeredListener = new RegisteredListener(this, this, priority, plugin, false);
        getHandlerList(eventClass).register(registeredListener);
        this.identifier = identifier;
        this.eventClass = eventClass;
        this.cancelAfter = cancelAfter;
        this.ignoreCancelled = ignoreCancelled;
        this.updateViewAfterEvent = updateViewAfterEvent;
    }

    @NotNull
    private HandlerList getHandlerList(@NotNull final Class<E> eventClass) {
        Method method;
        try {
            method = eventClass.getDeclaredMethod("getHandlerList");
        } catch (NoSuchMethodException ignore) {
            throw new IllegalArgumentException("[ActionMenuPlus] " + eventClass.getName() + " cannot be listened for!");
        }
        if (method == null) {
            throw new IllegalArgumentException("[ActionMenuPlus] " + eventClass.getName() + " cannot be listened for!");
        }
        HandlerList handlerList = null;
        try {
            method.setAccessible(true);
            Object handlerListObj = method.invoke(null);
            if (handlerListObj == null || !(handlerListObj instanceof HandlerList)) {
                throw new IllegalArgumentException("[ActionMenuPlus] " + eventClass.getName() + " cannot be listened for!");
            }
            handlerList = (HandlerList) handlerListObj;
        } catch (IllegalAccessException ignore) {
            throw new IllegalArgumentException("[ActionMenuPlus] " + eventClass.getName() + " cannot be listened for!");
        } catch (InvocationTargetException ignore) {
            throw new IllegalArgumentException("[ActionMenuPlus] " + eventClass.getName() + " cannot be listened for!");
        }
        return handlerList;
    }

    public boolean isCancellingAfter() {
        return cancelAfter;
    }

    public boolean isUpdatingViewAfter() {
        return updateViewAfterEvent;
    }

    public void putView(@NotNull final V view, @NotNull final Menu menu) {
        menuMap.put(view, menu);
    }

    public void removeView(@NotNull final V view) {
        menuMap.remove(view);
    }

    @Nullable
    public Menu getMenu(@NotNull final MenuView view) {
        return menuMap.get(view);
    }

    @NotNull
    public Set<V> getViews() {
        return menuMap.keySet();
    }

    @Nullable
    protected MenuView getViewInteractingWith(@NotNull final E event) {
        if (identifier != null) {
            return identifier.getViewFromEvent(event);
        } else {
            return null;
        }
    }

    @Override
    public void execute(final Listener listener, final Event event) throws EventException {
        if (ignoreCancelled && event instanceof Cancellable && ((Cancellable) event).isCancelled()) {
            return;
        }
        onEvent(eventClass.cast(event));
    }

    protected abstract void onEvent(@NotNull final E event);
}
