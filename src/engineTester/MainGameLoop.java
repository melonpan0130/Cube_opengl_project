package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay(); // display ����
		
		// engine ����
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				// left bottom triangle
				-0.5f, 0.5f, 0f,// v0
				-0.5f, -0.5f, 0f, // v1
				// rignt top triangle
				0.5f, -0.5f, 0f, // v2
				0.5f, 0.5f, 0f, // v3
		};
		
		int[] indices = {
				0, 1, 3, // top left triangle (v0, v1, v3)
				3, 1, 2 // bottom right triangle (v3, v1, v2)
		};
		
		RawModel model = loader.loadVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			// game Logic
			renderer.prepare();
			shader.start();
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
