package com.turpgames.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.turpgames.framework.v0.impl.desktop.DesktopEnvironmentProvider;
import com.turpgames.framework.v0.impl.libgdx.GdxGame;
import com.turpgames.framework.v0.util.Game;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Game";

		float w = 10f;
		float h = 16f;
		float x = 50f;

		cfg.width = (int) (x * w);
		cfg.height = (int) (x * h);

		Game.setEnvironmentProvider(new DesktopEnvironmentProvider());

		new LwjglApplication(new GdxGame(), cfg);
	}
}
