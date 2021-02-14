package com.thebyteguru.IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;



//карта, которая держит какие-то значения и ключи
public class Input extends JComponent {
    //массив с ASCI значениями клавиш, нажата кнопка или нет
    private boolean[] map;

    //создаем конструктор НА 256 значений
    public Input() {
        map = new boolean[256];
        //пробегаемся по всем ASCI значениям
        for (int i = 0; i <= map.length; i++) {
            //создаем неизменяемую копию i, что бы потом ее использовать без изменений
            final int KEY_CODE = i;
            //возвращает карту со значениями, которым мы можем давать названия
            //когда кнопка нажата только в окне,
            //метод getKeyStroke принимает параметры (кнопка в ASCI формате, дублируется ли нажатие с другими
            //клавишами (Ctrl + R), выполнять при нажатой клавише (false)
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, false),
                    i * 2);
            //добавляем действие к нажатию кнопки
            getActionMap().put(i * 2, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = true;
                }
            });
            //выполняем действие, когда кнопка отпущена
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, true),
                    i * 2 + 1);
            //добавляем действие к нажатию кнопки
            getActionMap().put(i * 2 + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = false;
                }
            });

        }
    }

    //хотим получить кариу, какие кнопки сей час нажаты
    public boolean[] getMap() {
        //делаем копию карты, которая возвращает копию, чего хотим и длину массива
        return Arrays.copyOf(map, map.length);
    }

    //проверяем одну кнопку нажата она или нет
    public boolean getKey(int keyCode) {
        return map[keyCode];
    }
}

