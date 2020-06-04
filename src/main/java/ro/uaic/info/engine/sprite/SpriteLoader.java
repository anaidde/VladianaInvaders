package ro.uaic.info.engine.sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {
    public static final String DEFAULT_SPRITE_SHEET_PATH = "/textures/SpriteSheet1.png";

    private static SpriteLoader instance;

    public static SpriteLoader getInstance() {
        if(SpriteLoader.instance == null)
            SpriteLoader.instance = new SpriteLoader();
        return instance;
    }

    private SpriteLoader(){
        try {
            this.spriteImage = ImageIO.read(SpriteLoader.class.getResource(DEFAULT_SPRITE_SHEET_PATH));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private BufferedImage spriteImage;

    private static final int DECAL_SPRITE_WIDTH = 75;
    private static final int DECAL_SPRITE_HEIGHT = 75;

    public SpriteLoader loadSpriteImage(BufferedImage spriteImage) {
        this.spriteImage = spriteImage;
        return this;
    }

    public static BufferedImage rotateYSprite(BufferedImage bufferedImage, double angle){
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage rotatedBufferedImage = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D graphic = rotatedBufferedImage.createGraphics();
        graphic.rotate(Math.toRadians(angle), (double)width/2, (double)height/2);
        graphic.drawImage(bufferedImage, null, 0, 0);
        graphic.dispose();
        return rotatedBufferedImage;
    }

    public BufferedImage crop(int x, int y){
        return this.spriteImage.getSubimage(
            x * DECAL_SPRITE_WIDTH,
            y * DECAL_SPRITE_HEIGHT,
            DECAL_SPRITE_WIDTH,
            DECAL_SPRITE_HEIGHT
        );
    }

    public BufferedImage getAsset(AssetList assetType){
        switch (assetType){
            case PH_SHIP_1: return this.crop(0, 0);
            case PH_SHIP_2: return this.crop(1, 0);
            case PH_SHIP_3: return this.crop(2, 0);
            case PH_PROJECTILE_1: return this.crop(0, 1);
            case PH_PROJECTILE_2: return this.crop(1, 1);
            case PH_POWER_UP_1: return this.crop(2, 2);
            default : return this.crop(9, 9);
        }
    }
}
