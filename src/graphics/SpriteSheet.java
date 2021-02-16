package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage sheet;
    //будет храниться индивидуальное изображение
    private int spriteCount;
    //размер одного спрайта
    private int scale;
    //количество спрайтов в ширину
    private int spritesInWidth;

    public SpriteSheet(BufferedImage sheet, int spriteCount, int scale){
        this.sheet = sheet;
        this.scale = scale;
        this.spriteCount = spriteCount;

        this.spritesInWidth = sheet.getWidth() / scale;


    }
    //создаем класс для того, что бы можно было выбрать определенный танчик
    public BufferedImage getSprite(int index){
        index = index % spriteCount;
        //пересчитываем координаты из нашего индекса
        int x = index % spritesInWidth * scale;
        int y = index / spritesInWidth * scale;
        return sheet.getSubimage(x,y,scale,scale);

    }
}
