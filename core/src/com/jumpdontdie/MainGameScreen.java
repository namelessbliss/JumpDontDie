package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jumpdontdie.actors.ActorJugador;

/**
 * Created by Administrador on 01/03/2018.
 */

public class MainGameScreen extends BaseScreen {
    public MainGameScreen(MyGdxGame game) {
        super(game);
        texturaJugador = new Texture("player1.png;");
    }

    private Stage stage;

    private ActorJugador jugador;

    private Texture texturaJugador;


    @Override
    public void show() {
        stage = new Stage();
        jugador = new ActorJugador(texturaJugador);
        stage.addActor(jugador);

        //definir posision jugador
        jugador.setPosition(20,100);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        texturaJugador.dispose();
    }
}
