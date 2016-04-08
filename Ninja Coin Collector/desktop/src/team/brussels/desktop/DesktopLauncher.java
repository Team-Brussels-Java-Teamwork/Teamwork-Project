package team.brussels.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import team.brussels.NinjaCoinCollector;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = NinjaCoinCollector.WIDTH;
		config.height = NinjaCoinCollector.HEIGHT;
		config.title = NinjaCoinCollector.TITLE;

		new LwjglApplication(new NinjaCoinCollector(), config);
	}
}
