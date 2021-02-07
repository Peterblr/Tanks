package com.thebyteguru.main;

import com.thebyteguru.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        //создаем наше окно: ширина, высота, название, цвет в hexadecimal (шестнадцатеричный)
        //где 0xff(прозрачность)ff(красный)ff(зеленый)ff(синий)
<<<<<<< HEAD
        Display.create(800, 600, "Tanks", 0xff00bb00, 3);
=======
        Display.create(800, 600, "Tanks", 0xff00bb00 );
>>>>>>> 5aad55debbc3b6bd0fb6d7f15f5a7d2f2df27f4f
        //функция будет вызываться каждый интервал времени, для обновления экрана
        //Важно!!! Импортировать библиотеку javax.swing.Timer
        Timer t =new Timer(1000/60, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Display.clear();//стираем баффер, перед рисунком сцен
                //рисуем
                Display.render();
                Display.swapBuffers();//меняем что видим, на то что создали


            }
        });
        //повторяем таймер
        t.setRepeats(true);
        t.start();

    }
}
