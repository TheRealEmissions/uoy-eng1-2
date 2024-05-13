package com.eng1.game.screens;

import com.badlogic.gdx.Screen;
import com.eng1.game.HeslingtonHustle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    MAIN(MainScreen::new),
    END(EndScreen::new),
    CHARACTER(CharacterScreen::new);

    private static final HeslingtonHustle parent = HeslingtonHustle.getInstance();

    private final Supplier<Screen> screenSupplier;
    private @Nullable Screen screen = null;

    Screens(Supplier<Screen> screenSupplier) {
        this.screenSupplier = screenSupplier;
    }

    public @NotNull Screen get() {
        if (screen == null) {
            screen = screenSupplier.get();
        }
        return screen;
    }

    public void change() {
        parent.setScreen(get());
    }
}
