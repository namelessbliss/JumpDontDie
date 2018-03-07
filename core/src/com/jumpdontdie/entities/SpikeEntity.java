package com.jumpdontdie.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.jumpdontdie.Constants.PIXEL_IN_METERS;

/**
 * Created by Administrador on 05/03/2018.
 */

public class SpikeEntity extends Actor{

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;

    /**
     *
     * @param world
     * @param texture
     * @param x posicion horizontal del centro del pincho(en metros)
     * @param y posicion vertical de la base  de los pinchos(en metros)
     */
    public SpikeEntity(World world, Texture texture, float x, float y) {
        this.world = world;
        this.texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(x,y + 0.5f);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);
        box.set(vertices);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("spike");
        box.dispose();

        setPosition((x - 0.5f) * PIXEL_IN_METERS, PIXEL_IN_METERS);
        setSize(PIXEL_IN_METERS,PIXEL_IN_METERS);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(),getY(),getWidth(),getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
