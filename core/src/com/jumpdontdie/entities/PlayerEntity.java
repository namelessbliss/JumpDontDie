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
import com.jumpdontdie.Constants;

import static com.jumpdontdie.Constants.IMPULSE_JUMP;
import static com.jumpdontdie.Constants.PIXEL_IN_METERS;
import static com.jumpdontdie.Constants.PLAYER_SPEED;

/**
 * Created by Administrador on 05/03/2018.
 */

public class PlayerEntity extends Actor {

    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;

    private boolean alive = true;
    private boolean jumping = false;

    public boolean isMustJump() {
        return mustJump;
    }

    public void setMustJump(boolean mustJump) {
        this.mustJump = mustJump;
    }

    private boolean mustJump = false;

    public PlayerEntity(World world, Texture texture, Vector2 position){
        this.world = world;
        this.texture = texture;
//crear body
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
//crear ficture
        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f,0.5f);
        fixture = body.createFixture(box,3);
        fixture.setUserData("player");
        box.dispose();
//establecer tamaño a actor a 1metro
        setSize(PIXEL_IN_METERS,PIXEL_IN_METERS);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * PIXEL_IN_METERS,
                (body.getPosition().y -0.5f)* PIXEL_IN_METERS);
        batch.draw(texture, getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta) {
        //Iniciar un salto si hemos tocado la pantalla
        if (mustJump){
            mustJump = false;
            jump();
        }
        //Avanzar el jugador si esta vivo
        if (alive) {
            float speedY = body.getLinearVelocity().y;
            body.setLinearVelocity(PLAYER_SPEED, speedY);
        }

        if (jumping){
            body.applyForceToCenter(0,-Constants.IMPULSE_JUMP * 1.15f, true);
        }

    }

    public void jump() {
        if (!jumping && alive) {
            jumping = true;
            Vector2 position = body.getPosition();
            body.applyLinearImpulse(0,IMPULSE_JUMP,position.x,position.y,true);
        }
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
