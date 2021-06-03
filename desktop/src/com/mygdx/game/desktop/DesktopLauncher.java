package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.mygdx.game.MyGame;

import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg){
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Invasor";
		config.useGL30 = false;
		config.width = 800;
		config.height = 500;
		new LwjglApplication(new MyGame(), config);
	}
}
