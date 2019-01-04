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
public class HighlightPathInitializer implements PostInitializeSubscriber{
    public static String highlightColor;
    public static Color highlightColorObject;

    public HighlightPathInitializer() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        HighlightPathInitializer mod = new HighlightPathInitializer();
    }

    @Override
    public void receivePostInitialize() {
        try {
            SpireConfig highlightPathsConfig = new SpireConfig("HighlightPaths", "HighlightPathsConfig");
            highlightPathsConfig.load();
            if(!highlightPathsConfig.has("circleColor")) {
                highlightPathsConfig.setString("circleColor", "#26004d");
            }
            highlightPathsConfig.save();
            highlightColor = highlightPathsConfig.getString("circleColor");
        } catch (IOException e) {
            e.printStackTrace();
        }
        highlightColorObject = Color.valueOf(highlightColor);
    }
}
