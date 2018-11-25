package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay(); // display ����
		
		// engine ����
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		/*
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
		
		float[] textureCoords = {
				0, 0,		// v0
				0, 1, 	// v1
				1, 1, 	// v2
				1, 0 		// v3
		};*/
		
		 float[] vertices = {            
	                -0.5f,0.5f,0,   
	                -0.5f,-0.5f,0,  
	                0.5f,-0.5f,0,   
	                0.5f,0.5f,0,        
	                 
	                -0.5f,0.5f,1,   
	                -0.5f,-0.5f,1,  
	                0.5f,-0.5f,1,   
	                0.5f,0.5f,1,
	                 
	                0.5f,0.5f,0,    
	                0.5f,-0.5f,0,   
	                0.5f,-0.5f,1,   
	                0.5f,0.5f,1,
	                 
	                -0.5f,0.5f,0,   
	                -0.5f,-0.5f,0,  
	                -0.5f,-0.5f,1,  
	                -0.5f,0.5f,1,
	                 
	                -0.5f,0.5f,1,
	                -0.5f,0.5f,0,
	                0.5f,0.5f,0,
	                0.5f,0.5f,1,
	                 
	                -0.5f,-0.5f,1,
	                -0.5f,-0.5f,0,
	                0.5f,-0.5f,0,
	                0.5f,-0.5f,1
	                 
	        };
	         
	        float[] textureCoords = {
	                 
	                0,0,
	                0,1,
	                1,1,
	                1,0,            
	                0,0,
	                0,1,
	                1,1,
	                1,0,            
	                0,0,
	                0,1,
	                1,1,
	                1,0,
	                0,0,
	                0,1,
	                1,1,
	                1,0,
	                0,0,
	                0,1,
	                1,1,
	                1,0,
	                0,0,
	                0,1,
	                1,1,
	                1,0
	 
	                 
	        };
	         
	        int[] indices = {
	                0,1,3,  
	                3,1,2,  
	                4,5,7,
	                7,5,6,
	                8,9,11,
	                11,9,10,
	                12,13,15,
	                15,13,14,   
	                16,17,19,
	                19,17,18,
	                20,21,23,
	                23,21,22
	 
	        };
		
		RawModel model = loader.loadVAO(vertices, textureCoords, indices);
		
		TexturedModel staticModel = new TexturedModel(model, 
				new ModelTexture(loader.loadTexture("apple")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			// game Logic
			// entity.increasePosition(0, 0, -0.1f);
			entity.increaseRotation(1, 1, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
