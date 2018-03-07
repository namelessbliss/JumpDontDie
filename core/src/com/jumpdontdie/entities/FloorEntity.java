package com.jumpdontdie.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

public class FloorEntity extends Actor {

    private Texture floor, overfloor;
    private World world;
    private Body body, leftBody;
    private Fixture fixture, leftFixture;

    /**
     *
     * @param world
     * @param floor
     * @param overfloor
     * @param x dónde esta el borde izquierdo del suelo(en metros)
     * @param width anchura del suelo en metros
     * @param y dónde esta el borde superior del  suelo(en metros)
     */

    public FloorEntity(World world, Texture floor, Texture overfloor, float x, float width, float y){
        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        //establecer posicion del suelo
        BodyDef def = new BodyDef();
        def.position.set(x + width/2, y - 0.5f);
        body = world.createBody(def);

        //establecer forma
        PolygonShape box = new PolygonShape();
        box.setAsBox(width/2, 0.5f);
        fixture = body.createFixture(box,1);
        fixture.setUserData("floor");
        box.dispose();
        //establecer lado izq del suelo como un pincho más
        BodyDef leftDef = new BodyDef();
        leftDef.position.set(x, y - 0.55f);
        leftBody = world.createBody(leftDef);
        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.02f, 0.45f);
        leftFixture = leftBody.createFixture(leftBox,1);
        leftFixture.setUserData("spike");
        box.dispose();

        setSize(width * PIXEL_IN_METERS, PIXEL_IN_METERS);
        setPosition(x * PIXEL_IN_METERS, (y - 1) * PIXEL_IN_METERS);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(floor, getX(),getY(),getWidth(),getHeight());
        batch.draw(overfloor, getX(),getY(),getWidth(),getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
