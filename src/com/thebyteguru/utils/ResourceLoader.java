package com.thebyteguru.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {
    //добавляем путь к папке с рисунками
    public static final String PATH = "res/";
    //добавляем функцию, которая загружает изображения
    public static BufferedImage loadImage (String fileName){
        BufferedImage image = null;
        //загружаем картинки с помощью класса ImageIO
        //ловим исключение
        try {
            image = ImageIO.read(new File(PATH + fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
}
