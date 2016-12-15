package com.graphcoloring.main;

import java.awt.Graphics;

// TODO: Auto-generated Javadoc
/**
 * The Class GameObject.
 */
public abstract class GameObject {

	/** The y. */
	protected int x, y;
	
	/** The id. */
	protected ID id;
	
	/** The vel Y. */
	protected int velX, velY;

	/**
	 * Instantiates a new game object.
	 *
	 * @param x the x
	 * @param y the y
	 * @param id the id
	 */
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * Tick.
	 */
	public abstract void tick();

	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public abstract void render(Graphics g);

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(ID id) {
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public ID getId() {
		return id;
	}

	/**
	 * Sets the vel X.
	 *
	 * @param velX the new vel X
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * Sets the vel Y.
	 *
	 * @param velY the new vel Y
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}

	/**
	 * Gets the vel X.
	 *
	 * @return the vel X
	 */
	public int getVelX() {
		return velX;
	}

	/**
	 * Gets the vel Y.
	 *
	 * @return the vel Y
	 */
	public int getVelY() {
		return velY;
	}
}
