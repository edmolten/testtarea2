package cl.usm.lp20162.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cl.usm.lp20162.TestTarea2;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Test Tarea 2";
                config.width = TestTarea2.SCREEN_WIDTH;
                config.height = TestTarea2.SCREEN_HEIGHT;
		new LwjglApplication(new TestTarea2(), config);
	}
}
