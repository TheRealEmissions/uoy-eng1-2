package com.eng1.game.audio.sounds;

import lombok.Getter;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.function.Supplier;
import com.eng1.game.audio.AudioManager;
import com.eng1.game.audio.sounds.elements.ClickSound;
import com.eng1.game.audio.sounds.elements.EatSound;
import com.eng1.game.audio.sounds.elements.InteractSound;
import com.eng1.game.utils.EnumMapOfSuppliers;


public class SoundsManager implements AudioManager {

    @Getter
    private static final SoundsManager instance = new SoundsManager();

    private static final Supplier<GameSounds> EAT = EatSound::new;
    private static final Supplier<GameSounds> INTERACT = InteractSound::new;
    private static final Supplier<GameSounds> CLICK = ClickSound::new;

    @Getter
    private static final EnumMap<Sounds, Supplier<GameSounds>> sounds = new EnumMap<Sounds, Supplier<GameSounds>>(Sounds.class);

    @Getter
    private static final EnumMapOfSuppliers<Sounds, GameSounds> supplierSounds = new EnumMapOfSuppliers<Sounds, GameSounds>(Sounds.class);

    private SoundsManager() {
    }

    public void onEnable() {
    }

    public void onDisable() {

        for (Supplier<GameSounds> gameSoundsSupplier : sounds.values()) {
            GameSounds sound = (GameSounds) gameSoundsSupplier;
            sound.dispose();
        }

    }


    static{
        sounds.put(Sounds.EAT, EAT);
        sounds.put(Sounds.INTERACT, INTERACT);
        sounds.put(Sounds.CLICK, CLICK);

        supplierSounds.put(Sounds.EAT, EAT);
        supplierSounds.put(Sounds.INTERACT, INTERACT);
        supplierSounds.put(Sounds.CLICK, CLICK);
    }


}
