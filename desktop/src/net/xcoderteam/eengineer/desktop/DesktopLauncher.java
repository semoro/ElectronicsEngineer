package net.xcoderteam.eengineer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.xcodersteam.eengineer.EEngineerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 500;
        config.width = 600;
        config.resizable = false;
		new LwjglApplication(new EEngineerGame(), config);

	}
}
