package com.eng1.game.screens;

import com.badlogic.gdx.Screen;

import java.util.function.Supplier;

/**
 * @version v2
 * @since v2 -- In v2, all screens are now loaded dynamically via
 * reflection as this streamlines the developer process --
 * developers only need to add a screen here and pass in its
 * constructor reference to gain access to it throughout
 * the program.
 */
public enum Screens {
    MENU(MenuScreen::new),
    PREFERENCES(PreferencesScreen::new),
    APPLICATION(MainScreen::new),
    ENDGAME(EndScreen::new),
    CHARACTER(CharacterScreen::new);

    private final Supplier<Screen> screenSupplier;

    Screens(Supplier<Screen> screenSupplier) {
        this.screenSupplier = screenSupplier;
    }
}
