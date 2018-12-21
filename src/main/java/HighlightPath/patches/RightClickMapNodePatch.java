package HighlightPath.patches;

import HighlightPath.HighlightPathInitializer;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import org.lwjgl.Sys;


public class RightClickMapNodePatch {

    @SpirePatch(
            clz = MapRoomNode.class,
            method = "update"
    )
    public static class ClickNode {
        public static void Prefix(MapRoomNode __instance) {
            if (__instance.hb.hovered && InputHelper.justClickedRight) {
                boolean isHighlighted = HighlightedField.isHighlighted.get(__instance);
                HighlightedField.isHighlighted.set(__instance, !isHighlighted);
            }
        }
    }

    @SpirePatch(
            clz = MapRoomNode.class,
            method = SpirePatch.CLASS
    )
    public static class HighlightedField {
        public static SpireField<Boolean> isHighlighted = new SpireField<>(() -> false);
    }

    @SpirePatch(
            clz = MapRoomNode.class,
            method = "render"
    )
    public static class RenderHighlight {
        public static void Postfix(MapRoomNode __instance, SpriteBatch sb) {
            if (HighlightedField.isHighlighted.get(__instance)) {
                float offsetX = (float)ReflectionHacks.getPrivateStatic(MapRoomNode.class, "OFFSET_X");
                float offsetY = (float)ReflectionHacks.getPrivateStatic(MapRoomNode.class, "OFFSET_Y");
                float spacingX = (float)ReflectionHacks.getPrivateStatic(MapRoomNode.class, "SPACING_X");
                float scale = (float)ReflectionHacks.getPrivate(__instance, MapRoomNode.class, "scale");
                float angle = (float)ReflectionHacks.getPrivate(__instance, MapRoomNode.class, "angle");
                sb.setColor(Color.valueOf("#26004d"));
                sb.draw(ImageMaster.MAP_CIRCLE_5,
                        __instance.x * spacingX + offsetX - 96.0f + __instance.offsetX,
                        __instance.y * Settings.MAP_DST_Y + offsetY + DungeonMapScreen.offsetY - 96.0f + __instance.offsetY,
                        96.0f, 96.0f, 192.0f, 192.0f, (scale * 0.95f + 0.2f) * Settings.scale,
                        (scale * 0.95f + 0.2f) * Settings.scale, angle, 0, 0, 192, 192,
                        false, false);
            }
        }
    }
}
