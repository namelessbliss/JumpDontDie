package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrador on 02/03/2018.
 */

public class Box2DScreen extends BaseScreen {
    public Box2DScreen(MyGdxGame game) {
        super(game);
    }

    private World world;

    private Box2DDebugRenderer renderer;

    private OrthographicCamera camera;

    private Body player1Body, sueloBody, pinchoBody;

    private Fixture player1Fixture, sueloFixture, pinchoFixture;

    private boolean debeSaltar, playerSaltando, playerVivo = true;

    @Override
    public void show() {
        //declarar nuevo mundo
        world = new World(new Vector2(0, -10),true);
        renderer = new Box2DDebugRenderer();
        //crear camara
        //tamaño en metros
        camera = new OrthographicCamera(19  ,9);
        camera.translate(0,1);

        //establecer contactor para colisiones
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("floor"))
                        || (fixtureA.getUserData().equals("floor") && fixtureB.getUserData().equals("player"))){
                        if (Gdx.input.isTouched()){
                            debeSaltar = true;
                        }
                        playerSaltando = false;
                }

                if ((fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("spike"))
                        || (fixtureA.getUserData().equals("spike") && fixtureB.getUserData().equals("player"))){
                    playerVivo = false;
                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if (fixtureA == player1Fixture && fixtureB == sueloFixture){
                    playerSaltando = true;
                }
                if (fixtureA == sueloFixture && fixtureB == player1Fixture){
                    playerSaltando = true;
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        //crear bodies
        player1Body = world.createBody(createPlayerBodyDef());
        sueloBody = world.createBody(createSueloBodyDef());
        pinchoBody = world.createBody(createPinchoBodyDef(6f));

        PolygonShape player1Shape = new PolygonShape();
        player1Shape.setAsBox(0.5f,0.5f);
        player1Fixture = player1Body.createFixture(player1Shape,3);
        player1Shape.dispose();

        PolygonShape sueloShape = new PolygonShape();
        sueloShape.setAsBox(500,1);
        sueloFixture = sueloBody.createFixture(sueloShape, 1);
        sueloShape.dispose();

        pinchoFixture = createPincoFixture(pinchoBody);

        //declarar fixtures a userdata
        player1Fixture.setUserData("player");
        sueloFixture.setUserData("floor");
        pinchoFixture.setUserData("spike");
    }

    private BodyDef createPinchoBodyDef(float x) {
        BodyDef def = new BodyDef();
        def.position.set(x,0.5f);
        return def;
    }

    private BodyDef createSueloBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,-1);
        return def;
    }

    private BodyDef createPlayerBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0,0.5f);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private Fixture createPincoFixture(Body pinchoBody){
        //hacer triangulo
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f,-0.5f);
        vertices[1] = new Vector2(0.5f,-0.5f);
        vertices[2] = new Vector2(0,0.5f);
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);
        Fixture fix = pinchoBody.createFixture(shape,1);
        shape.dispose();
        return fix;
    }

    @Override
    public void dispose() {
        player1Body.destroyFixture(player1Fixture);
        sueloBody.destroyFixture(sueloFixture);
        pinchoBody.destroyFixture(pinchoFixture);
        world.destroyBody(player1Body);
        world.destroyBody(sueloBody);
        world.destroyBody(pinchoBody);
        world.dispose();
        renderer.dispose();
        camera.translate(0,1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //saltar al tocar pantalla
        if (debeSaltar){
            debeSaltar = false;
            saltar();
        }
        if (Gdx.input.justTouched() && !playerSaltando){
            debeSaltar = true;
        }
        if (playerVivo) {
            float velocidadY = player1Body.getLinearVelocity().y;
            player1Body.setLinearVelocity(8, velocidadY);
        }
        //establecer mundo
        world.step(delta, 6,2);
        //dibujar mundo
        camera.update();
        renderer.render(world,camera.combined);
    }

    //Crear metodo para salto
    private void saltar(){
        Vector2 position = player1Body.getPosition();
        player1Body.applyLinearImpulse(0,20,position.x,position.y,true);
    }
}
