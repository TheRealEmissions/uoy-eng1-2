package com.eng1.game.game.player;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eng1.game.assets.skins.SkinAssets;

public interface PlayerStatistic {
    float PROGRESS_BAR_MINIMUM = 0f;
    Skin uiSkin = SkinAssets.UI.get();

    ProgressBar getProgressBar();
    String getLabel();
    float get();
    void set(float value);
    void increase(float amount);
    void decrease(float amount);
    float getDefault();
    void setTotal(float total);
    void increaseTotal(float amount);
    float getTotal();
    float getMaxTotal();
    void reset();

    static void dispose() {
        uiSkin.dispose();
    }
}
