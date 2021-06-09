/*
 * Feel free to do whatever you want with this code, all I've done is
 * pull together common knowledge into one easy package. Use it as a
 * base for your own work, copy/paste bits or integrate it into your
 * existing project, it's all good. Just add a thanks to me somewhere.
 */
package com.dcronqvist.engine.math;

/**
 * Vector3f
 *
 * Contains the definition of a Vector comprising 3 floats and associated
 * transformations.
 *
 * @author Richard Greenlees
 */
public class Vector3f {

    public float x;
    public float y;
    public float z;

    public static Vector3f forward = new Vector3f(0, 0, 1);
    public static Vector3f up = new Vector3f(0, 1, 0);
    public static Vector3f zero = new Vector3f(0, 0, 0);

    public Vector3f() {

    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f clone) {
        this.x = clone.x;
        this.y = clone.y;
        this.z = clone.z;
    }

    /**
     * Subtracts the supplied vector from this one
     */
    public Vector3f sub(Vector3f v) {
        return new Vector3f(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    /**
     * Adds v to this {@link Vector3f} and returns the result.
     * 
     * @param v The vector to add to this one.
     */
    public Vector3f add(Vector3f v) {
        return new Vector3f(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * Multiply this Vector3f by another Vector3f and return it.
     */
    public Vector3f mul(Vector3f v) {
        return new Vector3f(this.x * v.x, this.y * v.y, this.z * v.z);
    }

    /**
     * Multiply this Vector3f by the given rotation matrix.
     */
    public Vector3f mul(Matrix4f mat) {
        return new Vector3f(mat.m00 * this.x + mat.m10 * this.y + mat.m20 * this.z,
                mat.m01 * this.x + mat.m11 * this.y + mat.m21 * this.z,
                mat.m02 * this.x + mat.m12 * this.y + mat.m22 * this.z);
    }

    /**
     * Multiply this Vector3f by the given scalar float.
     */
    public Vector3f mul(float scalar) {
        return new Vector3f(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * Returns the length squared of this vector.
     */
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * Returns the length of this vector. Will always return a positive number.
     */
    public float length() {
        return (float) Math.sqrt(Math.abs(lengthSquared()));
    }

    /**
     * Normalizes this vector and returns it.
     */
    public Vector3f normalize() {
        float d = length();
        return this.mul(1.0f / d);
    }

    /**
     * Returns the cross product of this vector and other vec.
     * 
     * @param vec The vector to perform the cross product with.
     */
    public Vector3f cross(Vector3f vec) {
        return new Vector3f(this.y * vec.z - this.z * vec.y, this.z * vec.x - this.x * vec.z,
                this.x * vec.y - this.y * vec.x);
    }

    public float dot(Vector3f vec) {
        return (this.x * vec.x) + (this.y * vec.y) + (this.z * vec.z);
    }

    /**
     * Returns the distance between this Vector and v. Does not modify either. Will
     * always return a positive number.
     */
    public float distance(Vector3f v) {
        return (float) Math.sqrt(Math.abs(
                (v.x - this.x) * (v.x - this.x) + (v.y - this.y) * (v.y - this.y) + (v.z - this.z) * (v.z - this.z)));
    }

    public String toString() {
        return "Vector3f { " + x + ", " + y + ", " + z + " }";
    }
}
