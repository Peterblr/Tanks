package com.thebyteguru.utils;

//класс будет помогать со временем
public class Time {
    //статическое поле, которое хранит в себе кол-во наносекунд
    public static final long SECOND = 1000000000L;
    //создаем метод, который возвращает текущее время
    public static long get(){
        return System.nanoTime();

    }
}
