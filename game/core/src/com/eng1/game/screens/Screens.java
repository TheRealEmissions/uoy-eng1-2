package com.eng1.game.screens;

import com.badlogic.gdx.Screen;
import com.eng1.game.HeslingtonHustle;
import com.eng1.game.game.Score;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @version v2
 * @since v2 -- In v2, all screens are now loaded dynamically via
 * this enum as this streamlines the developer process --
 * developers only need to add a screen here and pass in its
 * constructor reference to gain access to it throughout
 * the program.
 */
public enum Screens {
    MENU(MenuScreen::new),
    PREFERENCES(PreferencesScreen::new),
    MAIN(MainScreen::new),
    END(EndScreen::new),
    HIGHSCORE(() -> new HighScoreEntryScreen(Score.calculateScorePercentage())),
    LEADERBOARD(LeaderboardScreen::new),
    CHARACTER(CharacterScreen::new),
    LOADING(LoadingScreen::new),
    PLAY(PlayScreen::new),

    INSTRUCTION(InstructionScreen::new);

    private static final HeslingtonHustle parent = HeslingtonHustle.getInstance();

    /**
     * The supplier for the screen instance.
     * This is used to lazily load the screen instance.
     */
    private final Supplier<Screen> screenSupplier;

    /**
     * The screen instance if it has been loaded.
     */
    private @Nullable Screen screen = null;

    /**
     * Constructor for the Screens enum.
     *
     * @param screenSupplier A Supplier that provides instances of Screen when needed.
     *                       This is used to dynamically load screens as required by the game.
     */
    Screens(Supplier<Screen> screenSupplier) {
        this.screenSupplier = screenSupplier;
    }

    public boolean isLoaded() {
        return screen != null;
    }

    /**
     * Retrieves the current Screen instance.
     * If the screen instance has not been loaded yet, it is loaded using the screenSupplier.
     *
     * @return The current Screen instance. This is guaranteed to be non-null.
     */
    public @NotNull Screen get() {
        // Check if the screen instance has been loaded
        if (screen == null) {
            // If it hasn't, load it using the screenSupplier
            screen = screenSupplier.get();
        }
        // Return the screen instance
        return screen;
    }

    /**
     * Sets the current screen to the screen associated with this enum constant.
     * This is done by calling the setScreen method on the parent (HeslingtonHustle) instance,
     * passing in the current Screen instance (retrieved by calling the get method).
     */
    public void setAsCurrent() {
        parent.setScreen(get());
    }

    public boolean isCurrent() {
        return parent.getScreen().equals(get());
    }

    /**
     * Disposes all loaded screens.
     * This method iterates over all enum constants and checks if their screen instance has been loaded.
     * If a screen instance has been loaded, it is disposed.
     */
    public static void disposeAll() {
        // Iterate over all enum constants
        for (Screens screen : values()) {
            // Check if the screen instance has been loaded
            if (screen.isLoaded()) {
                // Assert that the screen instance is not null
                assert screen.screen != null;
                // Dispose the screen instance
                screen.screen.dispose();
            }
        }
    }
}
