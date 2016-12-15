package com.graphcoloring.main;

import java.awt.Graphics;
import java.util.LinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class Handler.
 */
public class Handler {

	/** The object. */
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	/**
	 * Tick.
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/**
	 * Adds the object.
	 *
	 * @param object the object
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	/**
	 * Adds the object first.
	 *
	 * @param object the object
	 */
	public void addObjectFirst(GameObject object) {
		this.object.addFirst(object);
	}

	/**
	 * Removes the object.
	 *
	 * @param object the object
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	/**
	 * Removes the all objects.
	 */
	public void removeAllObjects() {
		while (!this.object.isEmpty()) {
			this.object.removeFirst();
		}
	}
}
