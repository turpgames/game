package com.turpgames.game.view;

import com.turpgames.framework.v0.impl.Screen;
import com.turpgames.framework.v0.impl.ScreenManager;
import com.turpgames.framework.v0.util.Game;
import com.turpgames.game.components.GameLogo;
import com.turpgames.game.components.Toolbar;
import com.turpgames.game.utils.R;

public class GameScreen extends Screen {
	@Override
	public void init() {
		super.init();

		registerDrawable(new GameLogo("Game Screen"), Game.LAYER_SCREEN);

		registerDrawable(Toolbar.getInstance(), Game.LAYER_INFO);
	}

	@Override
	protected boolean onBeforeActivate() {
		Toolbar.getInstance()
				.setListener(
						new com.turpgames.framework.v0.component.Toolbar.IToolbarListener() {
							@Override
							public void onToolbarBack() {
								onBack();
							}
						});

		return super.onBeforeActivate();
	}

	@Override
	protected void onAfterActivate() {
		Toolbar.getInstance().enable();
		super.onAfterActivate();
	}

	@Override
	protected boolean onBeforeDeactivate() {
		Toolbar.getInstance().disable();
		return super.onBeforeDeactivate();
	}

	protected boolean onBack() {
		ScreenManager.instance.switchTo(R.screens.menu, true);
		return true;
	}
}
