package com.thebyteguru.game;

import com.thebyteguru.IO.Input;
import graphics.Sprite;
import graphics.SpriteSheet;
import graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
    //сохраняем значение изображения на 16 пикселей
    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_PER_HEADING = 1;


    //создаем енам, что бы поворачивать картинку танка в нужную сторону
    private enum Heading {
        NORTH(0 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0 * SPRITE_SCALE, 1 * SPRITE_SCALE, 1 * SPRITE_SCALE);
        //здесь будут храниться координаты спрайта, который нам нужен
        private final int x;
        private final int y;
        private final int h;
        private final int w;
        //создаем конструктор

        Heading(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }

        //создаем метод, с помощью которго будем вырезать наши изображения
        protected BufferedImage texture(TextureAtlas atlas) {
            return atlas.cut(x, y, w, h);
        }
    }

    //здесь будет храниться, в какую сторону смор=трит наш танк
    private Heading heading;
    //создаем карту, где по ключу будем выбирать в какую сторону смотрит танк
    private Map<Heading, Sprite> spriteMap;

    private float scale;

    private float speed;

    public Player(float x, float y, float scale, float speed, TextureAtlas atlas) {
        super(EntityType.Player, x, y);
        heading = Heading.NORTH;
        spriteMap = new HashMap<Heading, Sprite>();
        this.scale = scale;
        this.speed = speed;
        //ложим значения в карту
        for (Heading h : Heading.values()) {
            SpriteSheet sheet = new SpriteSheet(h.texture(atlas), SPRITES_PER_HEADING, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(h, sprite);
        }
    }

    @Override
    public void update(Input input) {
        //вводим переменные, что бы на давать танку выезжать за пределы окна
        float newX = x;
        float newY = y;
        if (input.getKey(KeyEvent.VK_UP)) {
            newY -= speed;
            heading = Heading.NORTH;

        } else if (input.getKey(KeyEvent.VK_RIGHT)) {
            newX += speed;
            heading = Heading.EAST;

        } else if (input.getKey(KeyEvent.VK_DOWN)) {
            newY += speed;
            heading = Heading.SOUTH;

        } else if (input.getKey(KeyEvent.VK_LEFT)) {
            newX -= speed;
            heading = Heading.WEST;
        }
        //не даем танку выехать за пределы экрана
        if (newX < 0) {
            newX = 0;
        } else if (newX >= Game.WIDTH - SPRITE_SCALE * scale) {
            newX = Game.WIDTH - SPRITE_SCALE * scale;
        }
        if (newY < 0) {
            newY = 0;
        } else if (newY >= Game.HEIGHT - SPRITE_SCALE * scale) {
            newY = Game.HEIGHT - SPRITE_SCALE * scale;
        }

        x = newX;
        y = newY;


    }

    @Override
    public void render(Graphics2D g) {
        spriteMap.get(heading).render(g, x, y);

    }
}
