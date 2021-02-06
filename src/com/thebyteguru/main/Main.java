package com.thebyteguru.main;

import com.thebyteguru.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        //создаем наше окно
        Display.create(800, 600, "Tanks");
        //функция будет вызываться каждый интервал времени, для обновления экрана
        //Важно!!! Импортировать библиотеку javax.swing.Timer
        Timer t =new Timer(1000/60, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Display.render();
            }
        });
        //повторяем таймер
        t.setRepeats(true);
        t.start();

    }
}
