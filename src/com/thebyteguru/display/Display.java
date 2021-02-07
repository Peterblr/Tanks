package com.thebyteguru.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

//создаем окно движка
public abstract class Display {
    private static boolean created = false; //способ следить создалось окно или нет
    private static JFrame window; //создаем рамку
    private static Canvas content; //создаем лист, на котором будем рисовать

    //для создания изображение (имейдж)
    private static BufferedImage buffer;
    //создаем массив, в который запишем всю информацию, которая составляет наш имейдж
    private static int[] bufferDuta;
    //создаем обьект типа график
    private static Graphics bufferGraphics;
    //создаем цвет, чтобы очищать наш имейдж
    private static int clearColor;
    //temp
    private static float delta = 0;

    //temp end


    //метод создает наше окно
    public static void create(int width, int height, String title, int new_clearColor) { //ширина, высота и имя нашего окна
        //если окно создано, то выходим из функции
        if (created)
            return;
        //создаем наше окно
        window = new JFrame(title);//добавляем titke в конструктор JFrame
        //делаем так, что бы при нажатии на "крестик" окно закрывалось
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //создаем наш лист
        content = new Canvas();
        //создаем размер нашего листа
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        //добавляем фон в нашем окне
        //через класс Color добавляем цвет черный

        //запрещаем игроку менять наше окно
        window.setResizable(false);

        //добавляем в наше неизменяемое окно наш лист
        window.getContentPane().add(content);//возвращает только внутреннюю часть окна, не перекрывая кнопки
        window.pack();//изменит размер окна под размер контента
        //делаем так, что бы окно появлялось по центру
        window.setLocationRelativeTo(null);//если нет компонента (у нас null) окно будет по середине
        window.setVisible(true);//делаем, что бы окно было видимым

        //создаем имейдж
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        //вытаскиваем информацию об цвете, чтобы стереть все на нашем имейдже
        bufferDuta = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        //получаем оъект типа graphics, что бы рисовать фигуры
        bufferGraphics = buffer.getGraphics();
        //сохраняем цвет

        clearColor = new_clearColor;

        created = true;

    }

    //дополнительный метод, что бы очищать имейдж на тот цвет, который был передан в конструктор
    public static void clear() {
        //создаем массив с одинаковыми значениями
        Arrays.fill(bufferDuta, clearColor);
    }

    //дополнительная временная функция, что бы добавить все что мы хотим в наше окно
    public static void render() {
        //выбираем цвет
        bufferGraphics.setColor(new Color(0xff0000ff));
        //рисуем круг
        bufferGraphics.fillOval(350 +(int) (Math.sin(delta) * 200), 250, 100, 100);
        //добавляем движение для круга
        delta += 0.08f;
    }
    //дополнительная функция, которая меняет баффер, на который мы смотрим на данный момент, на то что мы создали
    //на новую сцену
    public static void swapBuffers(){
        //новый временный объект графики
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);//рисуем имейдж на координатах с заданной шириной и высотой (в нашес случае не меняем)

    }


}
