package com.thebyteguru.game;

import com.thebyteguru.IO.Input;
import com.thebyteguru.display.Display;
import com.thebyteguru.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

//класс для структуры игры
public class Game implements Runnable {
    //имплементируем функции//

    //параметры для создания окна
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Tanks";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 3;

    //сколько раз в секунду нужно делать расчеты физики (60 раз в секунду)
    public static final float UPDATE_RATE = 60.0F;
    //сколько времени должно проходить между каждым апдейтом
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    //создаем переменну, что бы позволить нашему фрейду (процессу) приостановиться, что б остальные процессы сработали
    public static final long IDLE_TIME = 1; //хранит одну миллисек

    //проверяем игру на бег
    private boolean running;
    private Thread gameThread;
    private Graphics2D graphics;

    //создаем обьект
    private Input input;


    //temp
    float x = 350;
    float y = 250;
    float delta = 0;
    float radius = 50;
    float speed = 3;

    //temp end


    //структура игры
    public Game() {
        //наша игра еще не бежит
        running = false;
        //создаем окно нашей игры
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);

        graphics = Display.getGraphics();

        input = new Input();
        Display.addInputListener(input);

    }

    //способ запускать игру
    public synchronized void start() {//synchronized - позволяет запустить только один процесс
        //проверяем, если игра уже запущена, то мы больше ничего не делаем
        if (running)
            return;
        //говорим, что наша игра начинает бежать
        running = true;
        gameThread = new Thread(this);
        gameThread.start();

    }

    //функция для того, что бы остановить нашу игру
    public synchronized void stop() {//synchronized - позволяет запустить только один процесс
        //проверяем, если игра не бежит, то мы ничего не останавливаем
        if (!running)
            return;
        running = false;
        //если какая-то функция приводит к exception, то нужно его поймать
        try {
            gameThread.join();//ждем, пока закончит работу
        } catch (InterruptedException e) {
            e.printStackTrace();//ловим exception и распечатываем его
        }
        //после того как остановили, вызываем функции очистки
        cleanUp();

    }

    //считает физику, позиции, движения
    private void update() {
        //передаем кнопке ASCI код
        if (input.getKey(KeyEvent.VK_UP))
            y -= speed;
        if (input.getKey(KeyEvent.VK_DOWN))
            y += speed;
        if (input.getKey(KeyEvent.VK_LEFT))
            x -= speed;
        if (input.getKey(KeyEvent.VK_RIGHT))
            x += speed;
    }

    //после того как посчитали физику, рисуем при ее помощи объекты (это само ядро, где бесконечный луп)
    private void render() {
        //очищаем экран черным цветом
        Display.clear();
        graphics.setColor(Color.white);
        graphics.fillOval((int) (x + (Math.sin(delta) * 200)),(int) y, (int) radius * 2, (int) radius * 2);
        //говорим, что мы закончили рисовать и хотим показать
        Display.swapBuffers();

    }


    //каждый класс, который имплемитирует Runnable, обязан содержать в себе метод run
    //главное ядро нашей игры
    public void run() {
        int fps = 0;
        int upd = 0;
        int updLoop = 0;
        long count = 0;



        float delta = 0;
        //создаем переменную для хранения времени
        long lastTime = Time.get();
        //создаем луп
        while (running) {
            //создаем переменную для текущего времени
            long now = Time.get();
            //нужно посчитать, сколько времени прошло, когда последний раз выполнялся код внутри цикла
            long elapsedTime = now - lastTime;
            //для правильной работы цикла обновляем значение прошлого времени
            lastTime = now;
            //считаем время, которое прошло в нашей игре
            count += elapsedTime;
            //если никаких изменений не было, то перерисовывать экран не нужно
            boolean render = false;
            //количество апдейтов, которое нам нужно сделать
            delta += (elapsedTime / UPDATE_INTERVAL);
            //если прошло достаточно времени с прошлого апдейта, нужно сделать новый
            while (delta > 1) {
                update();
                upd++;
                delta--;
                //если render = true, то цикл повторяется уже не первый раз. Записываем это в updLoop
                if (render) {
                    updLoop++;
                } else {
                    render = true;
                }
            }
            //если были изменения, то перерисовываем экран
            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDLE_TIME);//даем подышать программе, если ничего не изменялось
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //если прошла одна секунда, как мы включили нашу игру, то высвечиваем данные
            if (count >= Time.SECOND){
                Display.setTitle(TITLE + " || fps:" + fps + " | upd:" + upd + " | updLoop:" + updLoop);
                upd = 0;
                updLoop = 0;
                fps = 0;
                count = 0;

            }

        }

    }

    //любые ресурсы которые мы будет создавать и их нужно закрывать создаем в этой функции
    private void cleanUp() {
        Display.distroy();//уничтожить наше окно, после того как мы закрыли игру

    }

}
