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

    private final Supplier<Screen> screenSupplier;
    private @Nullable Screen screen = null;

    Screens(Supplier<Screen> screenSupplier) {
        this.screenSupplier = screenSupplier;
    }

    public boolean isLoaded() {
        return screen != null;
    }

    public @NotNull Screen get() {
        if (screen == null) {
            screen = screenSupplier.get();
        }
        return screen;
    }

    public void setAsCurrent() {
        parent.setScreen(get());
    }

    public boolean isCurrent() {
        return parent.getScreen().equals(get());
    }

    public static void disposeAll() {
        for (Screens screen : values()) {
            if (screen.isLoaded()) {
                assert screen.screen != null;
                screen.screen.dispose();
            }
        }
    }
}
