package xyz.terrific.mod.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapState;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import xyz.terrific.mod.helper.math.Matrix4x4;
import xyz.terrific.mod.helper.math.Vector3D;

import java.awt.*;


/**
 * I used JexClient's Render2DHelper, modified some functions and removed some functions
 * Credit: JexClient / @DustinRepo
 */
public enum Render2DHelper {
    INSTANCE;
    private final static Identifier MAP_BACKGROUND = new Identifier("textures/map/map_background_checkerboard.png");

    public FontHelper getFontRenderer() {
        return FontHelper.INSTANCE;
    }

    public void setup2DRender(boolean disableDepth) {
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        if (disableDepth)
            RenderSystem.disableDepthTest();
    }

    public void end2DRender() {
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();
    }

    public double getScaleFactor() {
        return Wrapper.INSTANCE.getWindow().getScaleFactor();
    }

    public int getScaledWidth() {
        return Wrapper.INSTANCE.getWindow().getScaledWidth();
    }

    public int getScaledHeight() {
        return Wrapper.INSTANCE.getWindow().getScaledHeight();
    }

    public void drawTexture(MatrixStack matrices, float x, float y, float u, float v, float width, float height, int textureWidth, int textureHeight) {
        drawTexture(matrices, x, y, width, height, u, v, width, height, textureWidth, textureHeight);
    }

    private void drawTexture(MatrixStack matrices, float x, float y, float width, float height, float u, float v, float regionWidth, float regionHeight, int textureWidth, int textureHeight) {
        drawTexture(matrices, x, x + width, y, y + height, 0, regionWidth, regionHeight, u, v, textureWidth, textureHeight);
    }

    private void drawTexture(MatrixStack matrices, float x0, float y0, float x1, float y1, int z, float regionWidth, float regionHeight, float u, float v, int textureWidth, int textureHeight) {
        drawTexturedQuad(matrices.peek().getPositionMatrix(), x0, y0, x1, y1, z, (u + 0.0F) / (float)textureWidth, (u + (float)regionWidth) / (float)textureWidth, (v + 0.0F) / (float)textureHeight, (v + (float)regionHeight) / (float)textureHeight);
    }

    public void drawTexturedQuad(Matrix4f matrices, float x0, float x1, float y0, float y1, float z, float u0, float u1, float v0, float v1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferBuilder.vertex(matrices, (float)x0, (float)y1, (float)z).texture(u0, v1).next();
        bufferBuilder.vertex(matrices, (float)x1, (float)y1, (float)z).texture(u1, v1).next();
        bufferBuilder.vertex(matrices, (float)x1, (float)y0, (float)z).texture(u1, v0).next();
        bufferBuilder.vertex(matrices, (float)x0, (float)y0, (float)z).texture(u0, v0).next();
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
    }

    public void drawTexturedQuadNoDraw(Matrix4f matrices, float x0, float x1, float y0, float y1, float z, float u0, float u1, float v0, float v1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.vertex(matrices, (float)x0, (float)y1, (float)z).texture(u0, v1).next();
        bufferBuilder.vertex(matrices, (float)x1, (float)y1, (float)z).texture(u1, v1).next();
        bufferBuilder.vertex(matrices, (float)x1, (float)y0, (float)z).texture(u1, v0).next();
        bufferBuilder.vertex(matrices, (float)x0, (float)y0, (float)z).texture(u0, v0).next();
    }

    public void fill(MatrixStack poseStack, float x1, float y1, float x2, float y2, int color) {
        Matrix4f matrix = poseStack.peek().getPositionMatrix();
        float j;
        if (x1 < x2) {
            j = x1;
            x1 = x2;
            x2 = j;
        }

        if (y1 < y2) {
            j = y1;
            y1 = y2;
            y2 = j;
        }

        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float k = (float)(color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void fillNoDraw(MatrixStack poseStack, float x1, float y1, float x2, float y2, int color) {
        Matrix4f matrix = poseStack.peek().getPositionMatrix();
        float j;
        if (x1 < x2) {
            j = x1;
            x1 = x2;
            x2 = j;
        }

        if (y1 < y2) {
            j = y1;
            y1 = y2;
            y2 = j;
        }

        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float k = (float)(color & 255) / 255.0F;
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, k, f).next();
    }

    public void drawFace(MatrixStack poseStack, float x, float y, int renderScale, Identifier id) {
        try {
            bindTexture(id);
            drawTexture(poseStack, x, y, 8 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 64 * renderScale, 64 * renderScale);
            drawTexture(poseStack, x, y, 8 * renderScale, 8 * renderScale, 40 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 64 * renderScale, 64 * renderScale);
        }catch (Exception e){}
    }

    public void fillAndBorder(MatrixStack poseStack, float left, float top, float right, float bottom, int bcolor, int icolor, float f) {
        fill(poseStack, left + f, top + f, right - f, bottom - f, icolor);
        fill(poseStack, left, top, left + f, bottom, bcolor);
        fill(poseStack, left + f, top, right, top + f, bcolor);
        fill(poseStack, left + f, bottom - f, right, bottom, bcolor);
        fill(poseStack, right - f, top + f, right, bottom - f, bcolor);
    }

    public void drawGradientRect(double x, double y, double x2, double y2, int col1, int col2) {
        float f = (float) (col1 >> 24 & 0xFF) / 255F;
        float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
        float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
        float f3 = (float) (col1 & 0xFF) / 255F;

        float f4 = (float) (col2 >> 24 & 0xFF) / 255F;
        float f5 = (float) (col2 >> 16 & 0xFF) / 255F;
        float f6 = (float) (col2 >> 8 & 0xFF) / 255F;
        float f7 = (float) (col2 & 0xFF) / 255F;

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(x2, y, 0).color(f1, f2, f3, f).next();
        bufferBuilder.vertex(x, y, 0).color(f1, f2, f3, f).next();

        bufferBuilder.vertex(x, y2, 0).color(f5, f6, f7, f4).next();
        bufferBuilder.vertex(x2, y2, 0).color(f5, f6, f7, f4).next();

        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void drawRect(double x, double y, double x2, double y2, Color col) {
        float alpha = (float) 255 - col.getAlpha();
        float red = (float) col.getRed();
        float green = (float) col.getGreen();
        float blue = (float) col.getBlue();

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(x2, y, 0).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(x, y, 0).color(red, green, blue, alpha).next();

        bufferBuilder.vertex(x, y2, 0).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(x2, y2, 0).color(red, green, blue, alpha).next();

        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void gradientFill(MatrixStack poseStack, float x, float y, float x2, float y2, Color col1, Color col2) {
        Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
        float alpha = (float) 255 - col1.getAlpha();
        float red = (float) col1.getRed();
        float green = (float) col1.getGreen();
        float blue = (float) col1.getBlue();

        float alpha1 = (float) 255 - col2.getAlpha();
        float red1 = (float) col2.getRed();
        float green1 = (float) col2.getGreen();
        float blue1 = (float) col2.getBlue();

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(matrix4f, x2, y, 0).color(red, blue, green, alpha).next();
        bufferBuilder.vertex(matrix4f, x, y, 0).color(red, blue, green, alpha).next();

        bufferBuilder.vertex(matrix4f, x, y2, 0).color(red1, blue1, green1, alpha1).next();
        bufferBuilder.vertex(matrix4f, x2, y2, 0).color(red1, blue1, green1, alpha1).next();

        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void gradientSidewaysFill(MatrixStack poseStack, float x, float y, float x2, float y2, Color col1, Color col2) {
        Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
        float alpha = (float) 255 - col1.getAlpha();
        float red = (float) col1.getRed();
        float green = (float) col1.getGreen();
        float blue = (float) col1.getBlue();

        float alpha1 = (float) 255 - col2.getAlpha();
        float red1 = (float) col2.getRed();
        float green1 = (float) col2.getGreen();
        float blue1 = (float) col2.getBlue();

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(matrix4f, x2, y, 0).color(red, blue, green, alpha).next();
        bufferBuilder.vertex(matrix4f, x, y, 0).color(red, blue, green, alpha).next();

        bufferBuilder.vertex(matrix4f, x, y2, 0).color(red1, blue1, green1, alpha1).next();
        bufferBuilder.vertex(matrix4f, x2, y2, 0).color(red1, blue1, green1, alpha1).next();

        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    //credits to 0x150 for this
    public void fillRound(MatrixStack matrixStack, float x, float y, float x2, float y2, Color col1, double rad, double samples) {
        Matrix4f matrix = matrixStack.peek().getPositionMatrix();
        float j;
        if (x < x2) {
            j = x;
            x = x2;
            x2 = j;
        }
        if (y < y2) {
            j = y;
            y = y2;
            y2 = j;
        }
        float alpha = (float) 255 - col1.getAlpha();
        float red = (float) col1.getRed();
        float green = (float) col1.getGreen();
        float blue = (float) col1.getBlue();

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
        double toX1 = x2 - rad;
        double toY1 = y2 - rad;
        double fromX1 = x + rad;
        double fromY1 = y + rad;
        double[][] map = new double[][]{new double[]{toX1, toY1}, new double[]{toX1, fromY1}, new double[]{fromX1, fromY1}, new double[]{fromX1, toY1}};
        for (int i = 0; i < 4; i++) {
            double[] current = map[i];
            for (double r = i * 90d; r < (360 / 4d + i * 90d); r += (90 / samples)) {
                float rad1 = (float) Math.toRadians(r);
                float sin = (float) (Math.sin(rad1) * rad);
                float cos = (float) (Math.cos(rad1) * rad);
                bufferBuilder.vertex(matrix, (float) current[0] + sin, (float) current[1] + cos, 0.0F).color(red, green, blue, alpha).next();
            }
        }
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void outlineAndFill(MatrixStack poseStack, float x, float y, float x2, float y2, Color bcolor, Color col1) {
        Matrix4f matrix = poseStack.peek().getPositionMatrix();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        float alpha = (float) 255 - col1.getAlpha();
        float red = (float) col1.getRed();
        float green = (float) col1.getGreen();
        float blue = (float) col1.getBlue();

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.vertex(matrix, x, y2, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x2, y2, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x2, y, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x, y, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        bufferBuilder.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        alpha = (float) 255 - bcolor.getAlpha();
        red = (float) bcolor.getRed();
        green = (float) bcolor.getGreen();
        blue = (float) bcolor.getBlue();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.vertex(matrix, x, y, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x, y2, 0.0F).color(red, green, blue, alpha).next();

        bufferBuilder.vertex(matrix, x, y2, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x2, y2, 0.0F).color(red, green, blue, alpha).next();

        bufferBuilder.vertex(matrix, x2, y2, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x2, y, 0.0F).color(red, green, blue, alpha).next();

        bufferBuilder.vertex(matrix, x2, y, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.vertex(matrix, x, y, 0.0F).color(red, green, blue, alpha).next();
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void drawMap(MatrixStack poseStack, int x, int y, ItemStack stack) {
        MapState mapState = FilledMapItem.getOrCreateMapState(stack, Wrapper.INSTANCE.getWorld());
        if (mapState != null) {
            Render2DHelper.INSTANCE.bindTexture(MAP_BACKGROUND);
            DrawableHelper.drawTexture(poseStack, x, y, 0, 0, 150, 150, 150, 150);

            poseStack.push();
            poseStack.translate(x + 11, y + 11, 1000);
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
            Wrapper.INSTANCE.getMinecraft().gameRenderer.getMapRenderer().draw(poseStack, immediate, FilledMapItem.getMapId(stack), mapState, false, 15728880);
            immediate.draw();
            poseStack.pop();
        }
    }

    public void drawCheckmark(MatrixStack poseStack, float x, float y, int color) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        Matrix4f matrix = poseStack.peek().getPositionMatrix();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        float f = (float)(color >> 24 & 255) / 255.0F;
        float g = (float)(color >> 16 & 255) / 255.0F;
        float h = (float)(color >> 8 & 255) / 255.0F;
        float k = (float)(color & 255) / 255.0F;
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.vertex(matrix, x, y + 5, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, x + 3, y + 8, 0.0F).color(g, h, k, f).next();

        bufferBuilder.vertex(matrix, x + 3, y + 8, 0.0F).color(g, h, k, f).next();
        bufferBuilder.vertex(matrix, x + 9, y - 1, 0.0F).color(g, h, k, f).next();

        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    public void drawFullCircle(int cx, int cy, double r, int c, MatrixStack poseStack) {
        float f = (c >> 24 & 0xFF) / 255.0F;
        float f1 = (c >> 16 & 0xFF) / 255.0F;
        float f2 = (c >> 8 & 0xFF) / 255.0F;
        float f3 = (c & 0xFF) / 255.0F;
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);
        for (int i = 0; i <= 360; i++) {
            double x = Math.sin(i * 3.141592653589793D / 180.0D) * r;
            double y = Math.cos(i * 3.141592653589793D / 180.0D) * r;
            bufferBuilder.vertex(cx + x, cy + y, -64).color(f1, f2, f3, f).next();
        }
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        RenderSystem.defaultBlendFunc();
    }

    public void drawArc(float cx, float cy, double r, int c, int startpoint, double arc, int linewidth, MatrixStack poseStack) {
        float f = (c >> 24 & 0xFF) / 255.0F;
        float f1 = (c >> 16 & 0xFF) / 255.0F;
        float f2 = (c >> 8 & 0xFF) / 255.0F;
        float f3 = (c & 0xFF) / 255.0F;
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.lineWidth(linewidth);

        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();

        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);//TRIANGLE_STRIP is fucked too I guess

        for (int i = (int) startpoint; i <= arc; i += 1) {
            double x = Math.sin(i * 3.141592653589793D / 180.0D) * r;
            double y = Math.cos(i * 3.141592653589793D / 180.0D) * r;
            bufferBuilder.vertex(cx + x, cy + y, 0).color(f1, f2, f3, f).next();
        }
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
        RenderSystem.defaultBlendFunc();
    }

    public void drawHLine(MatrixStack poseStack, float x, float y, float x2, Color color) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }

        fill(poseStack, x, x2, y + 1, x2 + 1, color.getRGB());
    }

    public void drawThinHLine(MatrixStack poseStack, float x, float y, float endX, Color color) {
        Matrix4f matrix4f = poseStack.peek().getPositionMatrix();
        Render2DHelper.INSTANCE.setup2DRender(false);
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        bufferBuilder.begin(DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
        bufferBuilder.vertex(matrix4f, x, y, 0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();
        bufferBuilder.vertex(matrix4f, endX, y, 0).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).next();
        bufferBuilder.clear();
        BufferRenderer.drawWithShader(bufferBuilder.end());
        Render2DHelper.INSTANCE.end2DRender();
    }

    public void drawVLine(MatrixStack poseStack, float par1, float par2, float par3, int par4) {
        if (par3 < par2) {
            float var5 = par2;
            par2 = par3;
            par3 = var5;
        }

        fill(poseStack, par1, par2 + 1, par1 + 1, par3, par4);
    }

    public Color hex2Rgb(String colorStr) {
        try {
            return new Color(Integer.valueOf(colorStr.substring(2, 4), 16), Integer.valueOf(colorStr.substring(4, 6), 16), Integer.valueOf(colorStr.substring(6, 8), 16));
        } catch (Exception e) {
            return Color.WHITE;
        }
    }

    public boolean isOnScreen(Vec3d pos) {
        if (pos.getZ() > -1 && pos.getZ() < 1) {
            return true;
        }
        return false;
    }

    /*
     * public void drawItem(ItemStack stack, float xPosition, float yPosition) {
     *     drawItem(stack, xPosition, yPosition, 1);
     * }

     * public void drawItem(ItemStack stack, float xPosition, float yPosition, float scale) {
     *     String amountText = stack.getCount() != 1 ? stack.getCount() + "" : "";
     *     IItemRenderer iItemRenderer = (IItemRenderer) Wrapper.INSTANCE.getMinecraft().getItemRenderer();
     *     iItemRenderer.renderItemIntoGUI(stack, xPosition, yPosition);
     *     renderGuiItemOverlay(Wrapper.INSTANCE.getMinecraft().textRenderer, stack, xPosition - 0.5f, yPosition + 1, scale, amountText);
     * }
     */

    public void renderGuiItemOverlay(TextRenderer renderer, ItemStack stack, float x, float y, float scale, @Nullable String countLabel) {
        if (!stack.isEmpty()) {
            MatrixStack poseStack = new MatrixStack();
            if (stack.getCount() != 1 || countLabel != null) {
                String string = countLabel == null ? String.valueOf(stack.getCount()) : countLabel;
                poseStack.translate(0.0D, 0.0D, (double)(Wrapper.INSTANCE.getMinecraft().getItemRenderer().zOffset + 200.0F));
                VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
                renderer.draw(string, (float)(x + 19 - 2 - renderer.getWidth(string)), (float)(y + 6 + 3), 16777215, true, poseStack.peek().getPositionMatrix(), immediate, false, 0, 15728880);
                immediate.draw();
            }

            if (stack.isItemBarVisible()) {
                RenderSystem.disableDepthTest();
                RenderSystem.disableTexture();
                RenderSystem.disableBlend();
                int i = stack.getItemBarStep();
                int j = stack.getItemBarColor();
                this.fill(poseStack, x + 2, y + 13, x + 2 + 13, y + 13 + 2, 0xff000000);
                this.fill(poseStack, x + 2, y + 13, x + 2 + i, y + 13 + 1, new Color(j >> 16 & 255, j >> 8 & 255, j & 255, 255).getRGB());
                RenderSystem.enableBlend();
                RenderSystem.enableTexture();
                RenderSystem.enableDepthTest();
            }

            ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
            float f = clientPlayerEntity == null ? 0.0F : clientPlayerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), MinecraftClient.getInstance().getTickDelta());
            if (f > 0.0F) {
                RenderSystem.disableDepthTest();
                RenderSystem.disableTexture();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                Tessellator tessellator2 = Tessellator.getInstance();
                BufferBuilder bufferBuilder2 = tessellator2.getBuffer();
                this.renderGuiQuad(bufferBuilder2, x, y + MathHelper.floor(16.0F * (1.0F - f)), 16, MathHelper.ceil(16.0F * f), 255, 255, 255, 127);
                RenderSystem.enableTexture();
                RenderSystem.enableDepthTest();
            }

        }
    }

    public void draw3DCape(MatrixStack poseStack, float x, float y, Identifier identifier, float yaw, float pitch) {
        poseStack.push();
        poseStack.translate(x + 16, y + 30, 64);
        poseStack.multiply(new Quaternion(new Vec3f(0, 1, 0), yaw, true));
        poseStack.multiply(new Quaternion(new Vec3f(1, 0, 0), pitch, true));
        //
        bindTexture(identifier);
        //front of cape
        DrawableHelper.drawTexture(poseStack, -16, -30, 2.5f, 4, 32, 60, 198, 124);
        //back of cape
        poseStack.multiply(new Quaternion(new Vec3f(0.0F, 1.0F, 0.0F), 180, true));
        DrawableHelper.drawTexture(poseStack, -16, -30, 34.5f, 4, 32, 60, 198, 124);
        poseStack.multiply(new Quaternion(new Vec3f(0.0F, 1.0F, 0.0F), -180, true));
        //
        poseStack.pop();
    }

    private void renderGuiQuad(BufferBuilder buffer, float x, float y, float width, float height, int red, int green, int blue, int alpha) {
        buffer.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex((double) (x + 0), (double) (y + 0), 0.0D).color(red, green, blue, alpha).next();
        buffer.vertex((double) (x + 0), (double) (y + height), 0.0D).color(red, green, blue, alpha).next();
        buffer.vertex((double) (x + width), (double) (y + height), 0.0D).color(red, green, blue, alpha).next();
        buffer.vertex((double) (x + width), (double) (y + 0), 0.0D).color(red, green, blue, alpha).next();
        Tessellator.getInstance().draw();
    }

    public Formatting getPercentFormatting(float percent) {
        if (percent <= 15)
            return Formatting.DARK_RED;
        else if (percent <= 25)
            return Formatting.RED;
        else if (percent <= 50)
            return Formatting.GOLD;
        else if (percent <= 75)
            return Formatting.YELLOW;
        return Formatting.GREEN;
    }

    /*
     * public Vec3d to2D(Vec3d worldPos, MatrixStack poseStack) {
     *    Vec3d bound = Render3DHelper.INSTANCE.getRenderPosition(worldPos, poseStack);
     *    Vec3d twoD = to2D(bound.x, bound.y, bound.z);
     *    return new Vec3d(twoD.x, twoD.y, twoD.z);
     * }
     */

    private Vec3d to2D(double x, double y, double z) {
        int displayHeight = Wrapper.INSTANCE.getWindow().getHeight();
        Vector3D screenCoords = new Vector3D();
        int[] viewport = new int[4];
        GL11.glGetIntegerv(GL11.GL_VIEWPORT, viewport);
        Matrix4x4 matrix4x4Proj = Matrix4x4.copyFromColumnMajor(RenderSystem.getProjectionMatrix());//no more joml :)
        Matrix4x4 matrix4x4Model = Matrix4x4.copyFromColumnMajor(RenderSystem.getModelViewMatrix());//but I do the math myself now :( (heck math)
        matrix4x4Proj.mul(matrix4x4Model).project((float) x, (float) y, (float) z, viewport, screenCoords);

        return new Vec3d(screenCoords.x / Render2DHelper.INSTANCE.getScaleFactor(), (displayHeight - screenCoords.y) / Render2DHelper.INSTANCE.getScaleFactor(), screenCoords.z);
    }

    public void bindTexture(Identifier identifier) {
        RenderSystem.setShaderTexture(0, identifier);
    }

    public void shaderColor(int hex) {
        float alpha = (hex >> 24 & 0xFF) / 255.0F;
        float red = (hex >> 16 & 0xFF) / 255.0F;
        float green = (hex >> 8 & 0xFF) / 255.0F;
        float blue = (hex & 0xFF) / 255.0F;
        RenderSystem.setShaderColor(red, green, blue, alpha);
    }
}
