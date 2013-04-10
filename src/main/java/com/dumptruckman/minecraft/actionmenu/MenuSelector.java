package com.dumptruckman.minecraft.actionmenu;

public interface MenuSelector {

    Menu getMenu();

    MenuItem getSelectedItem();

    enum SelectorMode {
        SINGLE_SELECTOR, MULTI_SELECTOR
    }
}
