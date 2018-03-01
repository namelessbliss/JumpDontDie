package com.jumpdontdie;

import com.badlogic.gdx.InputAdapter;

/**
 * Created by Administrador on 01/03/2018.
 */

public class Procesador extends InputAdapter {
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Has tocado  en la posición "+screenX+" "+screenY);
        System.out.println("Has usado el dedo "+pointer+" y el botón "+button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
