package HighlightPath;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.ModSlider;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;

import java.io.IOException;
import java.util.Properties;

@SpireInitializer
public class HighlightPathInitializer {
    public static Color highlightColor;

    public HighlightPathInitializer() {

    }

    public static void initialize() {
        HighlightPathInitializer mod = new HighlightPathInitializer();
    }

}
