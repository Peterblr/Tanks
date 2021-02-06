package com.thebyteguru.display;

import javax.swing.*;
import java.awt.*;
//создаем окно движка
public abstract class Display {
    private static boolean created = false; //способ следить создалось окно или нет
    private static JFrame window; //создаем рамку
    private static Canvas content; //создаем лист, на котором будем рисовать

    //метод создает наше окно
    public static void create (int width, int height, String title){ //ширина, высота и имя нашего окна
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

        //запрещаем игроку менять наше окно
        window.setResizable(false);

        //добавляем в наше неизменяемое окно наш лист
        window.getContentPane().add(content);//возвращает только внутреннюю часть окна, не перекрывая кнопки
        window.pack();//изменит размер окна под размер контента
        //делаем так, что бы окно появлялось по центру
        window.setLocationRelativeTo(null);//если нет компонента (у нас null) окно будет по середине
        window.setVisible(true);//делаем, что бы окно было видимым


    }

}
