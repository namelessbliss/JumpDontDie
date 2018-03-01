package com.jumpdontdie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	public Texture player1, princhos;

	private TextureRegion regionPinchos;
	private SpriteBatch batch;

	private int width;
	private int height;

	private int widthPlayer1, heightPlayer1;
	@Override
	public void create() {
		player1= new Texture("player1.png");
		princhos = new Texture("pinchos.png");
		batch = new SpriteBatch();

		regionPinchos = new TextureRegion(princhos, 0, 64, 128, 64);

		//obtener ancho de pantalla
		width = Gdx.graphics.getWidth();
		System.out.println("ANCHO "+ width);
		//obtener alto de pantalla
		height = Gdx.graphics.getHeight();
		System.out.println("ALTO " +height);

		widthPlayer1 = player1.getWidth();
		heightPlayer1 = player1.getHeight();


	}

	@Override
	public void dispose() {
		//liberar texturas
		player1.dispose();
		batch.dispose();
		princhos.dispose();
	}

	@Override
	public void render() {
		//limpiar la los colores en pantalla pantalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1,0,0.5f,1);
		//mostrar imagenes en pantalla
		batch.begin();
		batch.draw(player1,50,0);
		batch.draw(regionPinchos,250,0);
		batch.end();


	}
}
