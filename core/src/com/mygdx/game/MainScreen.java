package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainScreen extends AbstractScreen{

    private Texture titulo;

    private Texture btnJugar;

    private Texture btnSalir;

    private Stage stage;
    public MainScreen(MyGame game) {
        super(game);
    }


    @Override
    public void show() {
        titulo = MyGame.MANAGER.get("fondo2.png", Texture.class);
        btnJugar = MyGame.MANAGER.get("jugar2.png", Texture.class);
        btnSalir = MyGame.MANAGER.get("salir.png", Texture.class);

        stage = new Stage();

        Image imgFondo = new Image(titulo);
        imgFondo.setFillParent(true);
        stage.addActor(imgFondo);

        Image imgJugar = new Image(btnJugar);
        imgJugar.setBounds(stage.getWidth()/2 - btnJugar.getWidth()/1.5f,170,200,40);
        imgJugar.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                game.setScreen(game.GAMEPLAY);
                return true;
            }
        });
        stage.addActor(imgJugar);
        Image imgSalir = new Image(btnSalir);
        imgSalir.setBounds(stage.getWidth()/2 - btnSalir.getWidth()/1.5f,100,200,40);
        imgSalir.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        stage.addActor(imgSalir);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
