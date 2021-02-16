package graphics;

import graphics.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

//хранит в себе ту информацию, которая нам нужна на данный момент
public class Sprite {
    private SpriteSheet sheet;
    private float scale;


    public Sprite(SpriteSheet sheet, float scale){
        this.scale = scale;
        this.sheet = sheet;
    }
    public void render(Graphics2D g, float x, float y){
        //какой имэйдж мы хотим нарисовать
        BufferedImage image = sheet.getSprite(0);
        g.drawImage(image, (int)(x), (int)(y), (int)(image.getWidth() * scale), (int)(image.getHeight() * scale), null);

    }
}
