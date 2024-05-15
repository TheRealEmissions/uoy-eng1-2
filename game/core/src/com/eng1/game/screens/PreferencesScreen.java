package com.eng1.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.eng1.game.assets.skins.SkinAssets;
import com.eng1.game.settings.Preferences;
import lombok.experimental.UtilityClass;

/**
 * Represents the preferences screen of the game.
 * Allows the player to adjust game settings such as volume and enable/disable music and sound effects.
 * Currently redundant apart from ability to quit game
 *
 */
public class PreferencesScreen implements Screen {
    private final Stage stage; // Stage for handling UI elements
    private final Skin uiSkin = SkinAssets.UI.get(); // Skin for UI elements
    private final Table table = new Table(); // Table for organizing UI elements

    @UtilityClass
    private class TableContents {
        private static final Skin UI_SKIN = SkinAssets.UI.get();
        public static final Slider VOLUME_MUSIC_SLIDER = new Slider(0f, 1f, 0.01f, false, UI_SKIN);
        public static final Slider SOUND_MUSIC_SLIDER = new Slider(0f, 1f, 0.01f, false, UI_SKIN);
        public static final CheckBox MUSIC_CHECKBOX = new CheckBox(null, UI_SKIN);
        public static final CheckBox SOUND_EFFECTS_CHECKBOX = new CheckBox(null, UI_SKIN);
        public static final TextButton BACK_BUTTON = new TextButton("Back", UI_SKIN);
        public static final TextButton QUIT_BUTTON = new TextButton("Quit", UI_SKIN);
        public static final Label TITLE_LABEL = new Label("Preferences", UI_SKIN);
        public static final Label VOLUME_MUSIC_LABEL = new Label("Music Volume", UI_SKIN);
        public static final Label VOLUME_SOUND_LABEL = new Label("Sound Volume", UI_SKIN);
        public static final Label MUSIC_TOGGLE_LABEL = new Label("Music", UI_SKIN);
        public static final Label SOUND_TOGGLE_LABEL = new Label("Sound Effect", UI_SKIN);

        static {
            VOLUME_MUSIC_SLIDER.setValue(Preferences.MUSIC.getVolume());
            SOUND_MUSIC_SLIDER.setValue(Preferences.SOUND.getVolume());
            MUSIC_CHECKBOX.setChecked(Preferences.MUSIC.isEnabled());
            SOUND_EFFECTS_CHECKBOX.setChecked(Preferences.SOUND.isEnabled());

            TITLE_LABEL.setFontScale(2f);
            BACK_BUTTON.getLabel().setFontScale(1.6f);
            QUIT_BUTTON.getLabel().setFontScale(1.6f);
            VOLUME_MUSIC_LABEL.setFontScale(1.6f);
            VOLUME_SOUND_LABEL.setFontScale(1.6f);
            MUSIC_TOGGLE_LABEL.setFontScale(1.6f);
            SOUND_TOGGLE_LABEL.setFontScale(1.6f);
            MUSIC_CHECKBOX.getImage().setScale(2f);
            SOUND_EFFECTS_CHECKBOX.getImage().setScale(2f);
        }

        public static void dispose() {
            SkinAssets.UI.dispose(UI_SKIN);
        }
    }


    /**
     * Constructor for the PreferencesScreen class.
     * Initializes the parent orchestrator and creates a new stage for UI rendering.
     */
    public PreferencesScreen() {
        // create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
    }

    private void setTableContents() {
        table.setOrigin(Align.center);
        table.setPosition(0, 0);

        float width = Gdx.graphics.getWidth();
        float itemWidth = TableContents.BACK_BUTTON.getWidth() * 8f;
        float itemHeight = TableContents.BACK_BUTTON.getHeight() * 2f;

        table.add(TableContents.TITLE_LABEL).colspan(2);
        table.row().pad(10, 0, 0, 10);
        table.add(TableContents.VOLUME_MUSIC_LABEL).left();
        table.add(TableContents.VOLUME_MUSIC_SLIDER)
            .height(itemHeight)
            .width(itemWidth)
            .pad(10, 15, 10, 0);
        table.row().pad(10, 0, 0, 10);
        table.add(TableContents.MUSIC_TOGGLE_LABEL).left();
        table.add(TableContents.MUSIC_CHECKBOX)
            .pad(10, 0, 10, 0);
        table.row().pad(10, 0, 0, 10);
        table.add(TableContents.VOLUME_SOUND_LABEL).left();
        table.add(TableContents.SOUND_MUSIC_SLIDER)
            .height(itemHeight)
            .width(itemWidth)
            .pad(10, 15, 10, 0);
        table.row().pad(10, 0, 25, 10);
        table.add(TableContents.SOUND_TOGGLE_LABEL).left();
        table.add(TableContents.SOUND_EFFECTS_CHECKBOX)
            .pad(10, 0, 10, 0);
        table.row().pad(10, 0, 0, 10);
        table.add(TableContents.BACK_BUTTON)
            .colspan(2)
            .height(itemHeight)
            .width(itemWidth)
            .pad(10, 0, 10, 0);

        table.row().pad(10, 0, 0, 10);
        table.add(TableContents.QUIT_BUTTON)
            .colspan(2) // this was 50 for some reason... v2 fixed this.
            .height(itemHeight)
            .width(itemWidth)
            .pad(10, 0, 10, 0);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        stage.addActor(table);

        // Create a table that fills the screen. Everything else will go inside this table.
        table.setFillParent(true);
        table.setTransform(true);

        if (table.getChildren().isEmpty()) {
            setTableContents();
        }

        final Slider volumeMusicSlider = TableContents.VOLUME_MUSIC_SLIDER;
        volumeMusicSlider.addListener(event -> {
            Preferences.MUSIC.setVolume(volumeMusicSlider.getValue());
            return false;
        });

        // sound volume
        final Slider soundMusicSlider = TableContents.SOUND_MUSIC_SLIDER;
        soundMusicSlider.addListener(event -> {
            Preferences.SOUND.setVolume(soundMusicSlider.getValue());
            return false;
        });

        // music on/off
        final CheckBox musicCheckbox = TableContents.MUSIC_CHECKBOX;
        musicCheckbox.addListener(event -> {
            boolean enabled = musicCheckbox.isChecked();
            Preferences.MUSIC.setEnabled(enabled);
            return false;
        });

        // sound on/off
        final CheckBox soundEffectsCheckbox = TableContents.SOUND_EFFECTS_CHECKBOX;
        soundEffectsCheckbox.addListener(event -> {
            boolean enabled = soundEffectsCheckbox.isChecked();
            Preferences.SOUND.setEnabled(enabled);
            return false;
        });

        // return to main screen button
        TableContents.BACK_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (Screens.MAIN.isLoaded()) {
                    Screens.MAIN.setAsCurrent();
                } else {
                    Screens.MENU.setAsCurrent();
                }
            }
        });

        // quit game button
        TableContents.QUIT_BUTTON.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when the screen size is changed
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Not needed
    }

    @Override
    public void resume() {
        // Not needed
    }

    @Override
    public void hide() {
        // Not needed
    }

    @Override
    public void dispose() {
        TableContents.dispose();
        SkinAssets.UI.dispose(uiSkin);
        stage.dispose();
    }

}
