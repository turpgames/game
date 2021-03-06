package com.turpgames.game.view;

import com.turpgames.framework.v0.IResourceManager;
import com.turpgames.framework.v0.client.ConnectionManager;
import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.framework.v0.util.ShapeDrawer;
import com.turpgames.game.components.TurpLogo;
import com.turpgames.game.updates.GameUpdateManager;
import com.turpgames.game.utils.R;

public class SplashScreen extends Screen {
	private IResourceManager resourceManager;

	@Override
	public void init() {
		ConnectionManager.init();
		super.init();
		registerDrawable(new TurpLogo(), Game.LAYER_BACKGROUND);
		resourceManager = Game.getResourceManager();
	}

	@Override
	public void draw() {
		super.draw();
		float progress = (Game.getVirtualWidth() - 50f) * resourceManager.getProgress();
		ShapeDrawer.drawRect((Game.getVirtualWidth() - progress) / 2.0f, 100f, progress, 20f, R.colors.yellow, true, false);
	}

	@Override
	public void update() {
		if (!resourceManager.isLoading()) {
			GameUpdateManager.runUpdates();
			ScreenManager.instance.switchTo(R.screens.auth, false);
		}
	}
}
