package com.jumpdontdie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class CreditsScreen extends BaseScreen {

    private Stage stage;

    private Skin skin;

    private Label credits;

    private TextButton back;

    public CreditsScreen(final MyGdxGame game) {
        super(game);


        stage = new Stage(new FitViewport(640, 360));

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        back = new TextButton("Back", skin);

        credits = new Label("Jump Don't Die v1.0.2\n" +
                "Copyright (C) 2015-2016 Dani Rodriguez\n" +
                "This game is GNU GPL. Get the code at github.com/danirod/JumpDontDie\n\n" +

                "Music: \"Long Time Coming\" Kevin MacLeod (incompetech.com)\n" +
                "Licensed under Creative Commons: By Attribution 3.0", skin);

        back.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.setScreen(game.menuScreen);
            }
        });

        credits.setPosition(20, 340 - credits.getHeight());
        back.setSize(200, 80);
        back.setPosition(40, 50);

        // Do not forget to add actors to the stage or we wouldn't see anything.
        stage.addActor(back);
        stage.addActor(credits);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {

        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        // Dispose assets.
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}