package com.jumpdontdie.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jumpdontdie.BaseScreen;
import com.jumpdontdie.MyGdxGame;

/**
 * Created by Administrador on 01/03/2018.
 */

public class Scene2DScreen extends BaseScreen {
    public Scene2DScreen(MyGdxGame game) {
        super(game);
        texturaJugador = new Texture("player1.png");
        texturaPinchos = new Texture("pinchos.png");
        regionPinchos = new TextureRegion(texturaPinchos,0,64,128,64);
    }

    private Stage stage;
//Declarar actores
    private ActorJugador jugador;

    private ActorPinchos pinchos;
//Declarar texturas
    private Texture texturaJugador,texturaPinchos;
//Declarar region de textura
    private TextureRegion regionPinchos;

    @Override
    public void show() {
        stage = new Stage();
        //dibujar bordes de obejtos
        stage.setDebugAll(true);

        jugador = new ActorJugador(texturaJugador);
        pinchos = new ActorPinchos(regionPinchos);
        stage.addActor(jugador);
        stage.addActor(pinchos);


        //definir posision de actores
        jugador.setPosition(20,100);
        pinchos.setPosition(500,100);
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

        comprobarColisiones();

        stage.draw();
    }

    private void comprobarColisiones(){
        if (jugador.isAlive() &&
                jugador.getX()+jugador.getWidth()>pinchos.getX()){
            System.out.println("COLISION");
            jugador.setAlive(false);
        }
    }

    @Override
    public void dispose() {
        texturaJugador.dispose();
        texturaPinchos.dispose();
    }
}
