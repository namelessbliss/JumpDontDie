package com.jumpdontdie;


import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    @Override
    public void create() {
        setScreen(new Box2DScreen(this));
    }
}
