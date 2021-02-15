package graphics;

import com.thebyteguru.utils.ResourceLoader;

import java.awt.image.BufferedImage;

public class TextureAtlas {
    BufferedImage image;
    //создаем конструктор, с названием картинки, которую хотим загрузить
    public TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }
    //функция для вырезания из изображения более маленькие картинки
    public BufferedImage cut(int x, int y, int w, int h){
       return image.getSubimage(x, y, w, h);
    }
}
