package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoadingScreen extends AbstractScreen{
    public LoadingScreen(MyGame game) {
        super(game);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (MyGame.MANAGER.update()){
            game.setScreen(game.MAIN);
        }
        int with, heith;
        with = Gdx.graphics.getWidth();
        heith = Gdx.graphics.getHeight();

        if (MyGame.MANAGER.isLoaded("cargando.png")) {
            game.SB.begin();
            game.SB.draw(MyGame.MANAGER.<Texture>get("cargando.png", Texture.class), 0, 0, with, heith);
            game.SB.end();
        }


    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
