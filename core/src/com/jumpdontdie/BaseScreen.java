package com.jumpdontdie;

import com.badlogic.gdx.Screen;

/**
 * Created by Administrador on 01/03/2018.
 */

public abstract class BaseScreen implements Screen {

    protected MyGdxGame game;

    public BaseScreen(MyGdxGame game){
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
