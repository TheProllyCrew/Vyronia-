package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera() {}
	
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z-=0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x+=0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x-=0.02f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z +=0.02f;
		} 
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y +=0.02;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -=0.02;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_C)) {
			if(Mouse.isGrabbed()) {
				Mouse.setGrabbed(false);
			} else {
				Mouse.setGrabbed(true);
			}
		}
	}
	public void increaseYaw(float yaw) {
		this.yaw+=yaw;
	}
	public void increasePitch(float pitch) {
		this.pitch += pitch;
	}
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
}
