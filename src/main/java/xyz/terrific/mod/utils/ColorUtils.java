package xyz.terrific.mod.utils;

import java.awt.*;

/**
 * @author to a part me... to a part others...
 */
public class ColorUtils {

    // TODO
    public static Color fadeEffect(long timeAmount /* in milliseconds */, Timer timer, float fadeAmount, Color lastColor) {
        // "save" old colors
        int lastRed = lastColor.getRed();
        int lastBlue = lastColor.getBlue();
        int lastGreen = lastColor.getGreen();

        // Fade blue to green
        if ((timer.getStarted() + timeAmount) < timer.getCurrentTime()) { // only run if time is not up
            lastRed = (int) (lastRed + (fadeAmount * (timer.getElapsed() - timeAmount)));
            lastBlue = (int) (lastBlue - (fadeAmount * (timer.getElapsed() - timeAmount)));
        }

        return new Color(lastRed, lastGreen, lastBlue);
    }

    public static Color rainbowNormal(float seconds, float saturation, float brightness) {
        float hue = (System.currentTimeMillis() % (int)(seconds * 1000)) / (float)(seconds * 1000);
        return new Color(Color.HSBtoRGB(hue, saturation, brightness));
    }

    public static int rainbowWave(float seconds, float saturation, float brightness, long index) {
        float hue = ((System.currentTimeMillis() + index) % (int)(seconds * 1000)) / (float)(seconds * 1000);
        return Color.HSBtoRGB(hue, saturation, brightness);
    }

    public static Color rainbowEffect(long offset, float fade) {
        float hue = (float) (System.currentTimeMillis() + offset) / 1.0E10f % 1.0f;
        long c = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color color = new Color((int) c);
        return new Color((float) ((color.getRed() / 255.0f * fade > 1.0) ? 1.0 : color.getRed() / 255.0f * fade), (float) ((color.getGreen() / 255.0f * fade > 1.0) ? 1.0 : color.getGreen() / 255.0f * fade), (float) ((color.getBlue() / 255.0f * fade > 1.0) ? 1.0 : color.getBlue() / 255.0f * fade), color.getAlpha() / 255.0f);
    }

}
