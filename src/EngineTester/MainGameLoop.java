package EngineTester;


import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Models.RawModel;
import Models.TexturedModel;
import Textures.ModelTexture;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		float[] vertices = {
				  -0.5f, 0.5f, 0,
				  -0.5f, -0.5f, 0,
				  0.5f, -0.5f, 0,
				  0.5f, 0.5f, 0f
				};
		int[] indices = {
				0,1,3,
				3,1,2
		};
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		RawModel rock = OBJLoader.loadObjModel("rock", loader);
		RawModel deez = OBJLoader.loadObjModel("tree",loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		ModelTexture rockTex = new ModelTexture(loader.loadTexture("rock"));
		ModelTexture deezTex = new ModelTexture(loader.loadTexture("prolly"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		TexturedModel rockMod = new TexturedModel(rock, rockTex);
		TexturedModel deezMod = new TexturedModel(deez, deezTex);
		
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-1),0,0,0,1);
		Entity ballEnt = new Entity(rockMod, new Vector3f(0,0,-1),0,0,0,1);
		Entity deezEnt = new Entity(deezMod, new Vector3f(1,0,-3),0,0,0,1);
		
		
		
		Camera camera = new Camera();
		
		float mx;
		float my;
		float lmx = Mouse.getX();
		float lmy = Mouse.getY();
		while(!Display.isCloseRequested()) {
			mx = Mouse.getX();
			my = Mouse.getY();
			float dx = mx-lmx;
			float dy = my - lmy;
			if(Mouse.isGrabbed()) {
			camera.increaseYaw(dx*0.2f);
			camera.increasePitch(-dy*0.2f);
			}
			entity.increaseRotation(0, 0.5f, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			//game logic
			
			renderer.render(deezEnt,shader);
			renderer.render(ballEnt, shader);
			
			
			shader.stop();
			DisplayManager.updateDisplay();
			lmx = mx;
			lmy = my;
		}
		
		shader.cleanUp();
		loader.cleanUP();
		DisplayManager.closeDisplay();

	}

}
