package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {

	public static final AssetManager MANAGER = new AssetManager();
	public  SpriteBatch SB;
	public final AbstractScreen GAMEOVER, GAMEPLAY, LOADING , MAIN;

	public MyGame() {
		GAMEOVER = new GameOverScreen(this);
		GAMEPLAY = new GamePlayScreen(this);
		LOADING = new LoadingScreen(this);
		MAIN = new MainScreen(this);

	}
	@Override
	public void create() {
		SB = new SpriteBatch();

		MANAGER.load("cargando.png",Texture.class);
		MANAGER.load("fondo2.png",Texture.class);
		MANAGER.load("NavesYExplosion.png",Texture.class);
		MANAGER.load("bala.png",Texture.class);
		MANAGER.load("nave.png",Texture.class);
		MANAGER.load("defensa.png",Texture.class);
		MANAGER.load("barraVida.png",Texture.class);
		MANAGER.load("fondo_espacio.jpg",Texture.class);
		MANAGER.load("gameover.png",Texture.class);
		MANAGER.load("jugar2.png",Texture.class);
		MANAGER.load("salir.png",Texture.class);
		MANAGER.load("fondo2.png",Texture.class);
		MANAGER.load("fondo3.png",Texture.class);
		MANAGER.load("sonidos/explosion.ogg", Sound.class);
		MANAGER.load("sonidos/hit.ogg", Sound.class);
		MANAGER.load("sonidos/shoot.ogg", Sound.class);
		setScreen(LOADING);
	}
	@Override
	public void dispose(){
		super.dispose();
		MANAGER.dispose();
		SB.dispose();
	}
}
