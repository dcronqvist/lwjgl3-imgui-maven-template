/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dcronqvist.engine.math;

/**
 *
 * @author RGreenlees
 */
public class Vector2f {

    public float x;
    public float y;

    public static Vector2f zero = new Vector2f(0, 0);

    public Vector2f() {

    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f clone) {
        this.x = clone.x;
        this.y = clone.y;
    }

    /**
     * Returns this {@link Vector2f}'s perpendicular {@link Vector2f}.
     */
    public Vector2f perpendicular() {
        return new Vector2f(this.y, -this.x);
    }

    /**
     * Subtracts vec from this {@link Vector2f}, and returns the result.
     * 
     * @param vec The vector you want to subtract with.
     */
    public Vector2f sub(Vector2f vec) {
        return new Vector2f(this.x - vec.x, this.y - vec.y);
    }

    /** Returns the dot product of this vector and v */
    public float dot(Vector2f v) {
        return ((this.x * v.x) + (this.y * v.y));
    }

    /** Returns the length of this {@link Vector2f} */
    public float length() {
        return (float) Math.sqrt((x * x) + (y * y));
    }

    /**
     * Returns the distance between this Vector and v. Always returns a positive
     * number.
     */
    public float distance(Vector2f v) {
        return (float) Math.sqrt(Math.abs((v.x - x) * (v.x - x) + (v.y - y) * (v.y - y)));
    }

    /**
     * Normalizes this {@link Vector2f} and returns it. Does not modify this vector.
     */
    public Vector2f normalize() {
        float length = (float) Math.sqrt((x * x) + (y * y));
        return new Vector2f(this.x / length, this.y / length);
    }

    /** Adds v to this Vector2f */
    public Vector2f add(Vector2f v) {
        return new Vector2f(this.x + v.x, this.y + v.y);
    }

    public Vector2f scale(float scalar) {
        return new Vector2f(this.x * scalar, this.y * scalar);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2f) {
            Vector2f v = (Vector2f) obj;
            return this.x == v.x && this.y == v.y;
        } else {
            return false;
        }
    }
}
