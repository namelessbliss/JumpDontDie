package com.jumpdontdie;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class MyGdxGame extends ApplicationAdapter {


	@Override
	public void create() {
		Procesador p = new Procesador();
		Gdx.input.setInputProcessor(p);
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		//limpiar la los colores en pantalla pantalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Colorear pantalla
		Gdx.gl.glClearColor(0.3f,0.3f,0.3f,1);

		/*//Metodo de entrada
		if (Gdx.input.justTouched()) {
			System.out.println("Estas tocando la pantalla");

		}*/


	}
}
