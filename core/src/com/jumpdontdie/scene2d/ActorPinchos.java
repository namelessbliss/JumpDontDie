package com.jumpdontdie.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Administrador on 01/03/2018.
 */

public class ActorPinchos extends Actor {

    private TextureRegion pinchos;

    public ActorPinchos(TextureRegion pinchos){
        this.pinchos = pinchos;
        //establecer tamaño
        setSize(pinchos.getRegionWidth(),pinchos.getRegionHeight());

    }

    @Override
    public void act(float delta) {
        //avanza hacia la izq 250 px por seg
        //delta cuenta los miliseg que pasan
        setX(getX() - 250 * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(pinchos,getX(),getY());
    }
}
