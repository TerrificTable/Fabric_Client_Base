package xyz.terrific.mod.helper;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;


// Credits: JexClient / @DustinRepo
// This is not mine...
public enum FontHelper {
    INSTANCE;

    /**
     * Get width of string
     * @param string The String to get width from
     * @return width of string
     */
    public float getStringWidth(String string) {
        return Wrapper.INSTANCE.getTextRenderer().getWidth(string);
    }

    /**
     * Get String / Font height
     * @return String / Font height
     */
    public float getStringHeight() {
        return Wrapper.INSTANCE.getTextRenderer().fontHeight;
    }

    /**
     * Get String width of `Text` object
     * @param string The String
     * @return The String width
     */
    public float getStringWidth(Text string) {
        return Wrapper.INSTANCE.getTextRenderer().getWidth(string);
    }

    /**
     * draw String with shadow (x,y are top left of string)
     * @param matrixStack the matrix stack to... draw on
     * @param text the text to draw
     * @param x the x position
     * @param y the y position
     * @param color the color
     */
    public void drawWithShadow(MatrixStack matrixStack, String text, float x, float y, int color) {
        Wrapper.INSTANCE.getTextRenderer().draw(matrixStack, fix(text), x + 0.5f, y + 0.5f, 0xff000000);
        Wrapper.INSTANCE.getTextRenderer().draw(matrixStack, text, x, y, color);
    }

    /**
     * draw String with x and y being the middle of the string
     * @param matrixStack the matrix stack to... draw on
     * @param string the text to draw
     * @param x the x position
     * @param y the y position
     * @param color the color
     */
    public void drawCenteredString(MatrixStack matrixStack, String string, float x, float y, int color) {
        float newX = x - getStringWidth(string) / 2;

        Wrapper.INSTANCE.getTextRenderer().draw(matrixStack, fix(string), newX + 0.5f, y + 0.5f, 0xff000000);
        Wrapper.INSTANCE.getTextRenderer().draw(matrixStack, string, newX, y, color);
    }

    /**
     * draw String with shadow
     * @param matrixStack the matrix stack to... draw on
     * @param text the text to draw (Text not String)
     * @param x the x position
     * @param y the y position
     * @param color the color
     */
    public void drawWithShadow(MatrixStack matrixStack, Text text, float x, float y, int color) {
        String s = text.getString();
        drawWithShadow(matrixStack, s, x, y, color);
    }

    /**
     * draw String
     * @param matrixStack the matrix stack to... draw on
     * @param text the text to draw (Text not String)
     * @param x the x position
     * @param y the y position
     * @param color the color
     */
    public void draw(MatrixStack matrixStack, Text text, float x, float y, int color) {
        Wrapper.INSTANCE.getTextRenderer().draw(matrixStack, text, x, y, color);
    }
    /**
     * draw String
     * @param matrixStack the matrix stack to... draw on
     * @param text the text to draw (String not Text)
     * @param x the x position
     * @param y the y position
     * @param color the color
     */
    public void draw(MatrixStack matrixStack, String text, float x, float y, int color) {
        Wrapper.INSTANCE.getTextRenderer().draw(matrixStack, fix(text), x, y, color);
    }

    /**
     * draw centered String with shadow
     * @param matrixStack the matrix stack to... draw on
     * @param string the text to draw (Text not String)
     * @param x the x position
     * @param y the y position
     * @param color the color
     */
    public void drawCenteredStringWithShadow(MatrixStack matrixStack, Text string, float x, float y, int color) {
        float newX = x - (getStringWidth(string) / 2);

        drawWithShadow(matrixStack, string, newX, y, color);
    }

    // absolutely no clue
    public String fix(String s) {
        if (s == null || s.isEmpty())
            return s;
        for (int i = 0; i < 9; i++) {
            if (s.contains("\247" + i))
                s = s.replace("\247" + i, "");
        }
        return s.replace("\247a", "").replace("\247b", "").replace("\247c", "").replace("\247d", "").replace("\247e", "").replace("\247f", "").replace("\247g", "");
    }

}
