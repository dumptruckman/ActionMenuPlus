package com.dumptruckman.minecraft.actionmenu;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

public class MenuModel extends Observable {

    private final List<MenuItem> contents;

    public MenuModel() {
        this(new ArrayList<MenuItem>());
    }

    MenuModel(@NotNull final List<MenuItem> backingList) {
        this.contents = backingList;
    }

    public int size() {
        return contents.size();
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public boolean contains(@NotNull final Object o) {
        return contents.contains(o);
    }

    public boolean add(@NotNull final MenuItem menuItem) {
        try {
            return contents.add(menuItem);
        } finally {
            change();
        }
    }

    public boolean remove(@NotNull final Object o) {
        try {
            return contents.remove(o);
        } finally {
            change();
        }
    }

    public boolean containsAll(@NotNull final Collection<?> c) {
        return contents.containsAll(c);
    }

    public boolean addAll(@NotNull final Collection<? extends MenuItem> c) {
        try {
            return contents.addAll(c);
        } finally {
            change();
        }
    }

    public boolean addAll(final int index, @NotNull final Collection<? extends MenuItem> c) {
        try {
            return contents.addAll(index, c);
        } finally {
            change();
        }
    }

    public boolean removeAll(@NotNull final Collection<?> c) {
        try {
            return contents.removeAll(c);
        } finally {
            change();
        }
    }

    public boolean retainAll(@NotNull final Collection<?> c) {
        try {
            return contents.retainAll(c);
        } finally {
            change();
        }
    }

    public void clear() {
        try {
            contents.clear();
        } finally {
            change();
        }
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof MenuModel && contents.equals(o);
    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }

    @NotNull
    public MenuItem get(final int index) {
        return contents.get(index);
    }

    @NotNull
    public MenuItem set(final int index, @NotNull final MenuItem element) {
        try {
            return contents.set(index, element);
        } finally {
            change();
        }
    }

    public void add(final int index, @NotNull final MenuItem element) {
        try {
            contents.add(index, element);
        } finally {
            change();
        }
    }

    @NotNull
    public MenuItem remove(final int index) {
        try {
            return contents.remove(index);
        } finally {
            change();
        }
    }

    public int indexOf(@NotNull final Object o) {
        return contents.indexOf(o);
    }

    public int lastIndexOf(@NotNull final Object o) {
        return contents.lastIndexOf(o);
    }

    private void change() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "contents=" + contents +
                '}';
    }
}
