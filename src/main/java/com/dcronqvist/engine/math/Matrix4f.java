/*
 * Feel free to do whatever you want with this code, all I've done is
 * pull together common knowledge into one easy package. Use it as a
 * base for your own work, copy/paste bits or integrate it into your
 * existing project, it's all good. Just add a thanks to me somewhere.
 */
package com.dcronqvist.engine.math;

import java.nio.FloatBuffer;

/**
 * Matrix4f
 * 
 * Contains the definition of a 4x4 Matrix of floats, and associated functions
 * to transform it. The matrix is column-major to match OpenGL's interpretation,
 * and it looks like this:
 * 
 * m00 m10 m20 m30 m01 m11 m21 m31 m02 m12 m22 m32 m03 m13 m23 m33
 * 
 * @author Richard Greenlees
 */

public class Matrix4f {

        public float m00;
        public float m01;
        public float m02;
        public float m03;
        public float m10;
        public float m11;
        public float m12;
        public float m13;
        public float m20;
        public float m21;
        public float m22;
        public float m23;
        public float m30;
        public float m31;
        public float m32;
        public float m33;

        public Matrix4f() {
                super();
        }

        public Matrix4f(float diagonal) {
                super();
                this.m00 = diagonal;
                this.m11 = diagonal;
                this.m22 = diagonal;
                this.m33 = diagonal;
        }

        /** Clones this matrix from the supplied matrix */
        public Matrix4f(Matrix4f mat) {
                this.m00 = mat.m00;
                this.m01 = mat.m01;
                this.m02 = mat.m02;
                this.m03 = mat.m03;
                this.m10 = mat.m10;
                this.m11 = mat.m11;
                this.m12 = mat.m12;
                this.m13 = mat.m13;
                this.m20 = mat.m20;
                this.m21 = mat.m21;
                this.m22 = mat.m22;
                this.m23 = mat.m23;
                this.m30 = mat.m30;
                this.m31 = mat.m31;
                this.m32 = mat.m32;
                this.m33 = mat.m33;
        }

        /** Create a new 4x4 matrix using the supplied float values */
        public Matrix4f(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13,
                        float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
                this.m00 = m00;
                this.m01 = m01;
                this.m02 = m02;
                this.m03 = m03;
                this.m10 = m10;
                this.m11 = m11;
                this.m12 = m12;
                this.m13 = m13;
                this.m20 = m20;
                this.m21 = m21;
                this.m22 = m22;
                this.m23 = m23;
                this.m30 = m30;
                this.m31 = m31;
                this.m32 = m32;
                this.m33 = m33;
        }

        /**
         * A way to initialize a Matrix4f using a {@link FloatBuffer}.
         * 
         * @param buffer The {@link FloatBuffer} in which 16 column-major float values
         *               are.
         */
        public Matrix4f(FloatBuffer buffer) {
                m00 = buffer.get(0);
                m01 = buffer.get(1);
                m02 = buffer.get(2);
                m03 = buffer.get(3);
                m10 = buffer.get(4);
                m11 = buffer.get(5);
                m12 = buffer.get(6);
                m13 = buffer.get(7);
                m20 = buffer.get(8);
                m21 = buffer.get(9);
                m22 = buffer.get(10);
                m23 = buffer.get(11);
                m30 = buffer.get(12);
                m31 = buffer.get(13);
                m32 = buffer.get(14);
                m33 = buffer.get(15);
        }

        /**
         * Creates a {@link Matrix4f} using the supplied {@link float} array. Must be in
         * column-major order.
         * 
         * @param m The {@link float} array.
         */
        public Matrix4f(float m[]) {
                this.m00 = m[0];
                this.m02 = m[2];
                this.m01 = m[1];
                this.m03 = m[3];
                this.m10 = m[4];
                this.m11 = m[5];
                this.m12 = m[6];
                this.m13 = m[7];
                this.m20 = m[8];
                this.m21 = m[9];
                this.m22 = m[10];
                this.m23 = m[11];
                this.m30 = m[12];
                this.m31 = m[13];
                this.m32 = m[14];
                this.m33 = m[15];
        }

        /**
         * Returns an identity 4x4 matrix.
         * 
         * @return The identity matrix.
         */
        public static Matrix4f identity() {
                return new Matrix4f(1.0f);
        }

        /**
         * Multiplies this {@link Matrix4f} with the supplied {@link Matrix4f}. This
         * matrix will be seen as the "left" one.
         * 
         * @param right The "right" {@link Matrix4f} which you want to multiply this
         *              matrix with.
         * @return The resulting {@link Matrix4f} from the multiplication.
         */
        public Matrix4f mul(Matrix4f right) {
                return new Matrix4f(
                                this.m00 * right.m00 + this.m10 * right.m01 + this.m20 * right.m02
                                                + this.m30 * right.m03,
                                this.m01 * right.m00 + this.m11 * right.m01 + this.m21 * right.m02
                                                + this.m31 * right.m03,
                                this.m02 * right.m00 + this.m12 * right.m01 + this.m22 * right.m02
                                                + this.m32 * right.m03,
                                this.m03 * right.m00 + this.m13 * right.m01 + this.m23 * right.m02
                                                + this.m33 * right.m03,
                                this.m00 * right.m10 + this.m10 * right.m11 + this.m20 * right.m12
                                                + this.m30 * right.m13,
                                this.m01 * right.m10 + this.m11 * right.m11 + this.m21 * right.m12
                                                + this.m31 * right.m13,
                                this.m02 * right.m10 + this.m12 * right.m11 + this.m22 * right.m12
                                                + this.m32 * right.m13,
                                this.m03 * right.m10 + this.m13 * right.m11 + this.m23 * right.m12
                                                + this.m33 * right.m13,
                                this.m00 * right.m20 + this.m10 * right.m21 + this.m20 * right.m22
                                                + this.m30 * right.m23,
                                this.m01 * right.m20 + this.m11 * right.m21 + this.m21 * right.m22
                                                + this.m31 * right.m23,
                                this.m02 * right.m20 + this.m12 * right.m21 + this.m22 * right.m22
                                                + this.m32 * right.m23,
                                this.m03 * right.m20 + this.m13 * right.m21 + this.m23 * right.m22
                                                + this.m33 * right.m23,
                                this.m00 * right.m30 + this.m10 * right.m31 + this.m20 * right.m32
                                                + this.m30 * right.m33,
                                this.m01 * right.m30 + this.m11 * right.m31 + this.m21 * right.m32
                                                + this.m31 * right.m33,
                                this.m02 * right.m30 + this.m12 * right.m31 + this.m22 * right.m32
                                                + this.m32 * right.m33,
                                this.m03 * right.m30 + this.m13 * right.m31 + this.m23 * right.m32
                                                + this.m33 * right.m33);
        }

        /** Returns the determinant of this matrix */
        public float determinant() {
                return (m00 * m11 - m01 * m10) * (m22 * m33 - m23 * m32)
                                - (m00 * m12 - m02 * m10) * (m21 * m33 - m23 * m31)
                                + (m00 * m13 - m03 * m10) * (m21 * m32 - m22 * m31)
                                + (m01 * m12 - m02 * m11) * (m20 * m33 - m23 * m30)
                                - (m01 * m13 - m03 * m11) * (m20 * m32 - m22 * m30)
                                + (m02 * m13 - m03 * m12) * (m20 * m31 - m21 * m30);
        }

        /**
         * Returns this {@link Matrix4f}, but inverted.
         */
        public Matrix4f inverted() {
                float s = determinant();
                if (s == 0.0f) {
                        return this;
                }
                s = 1.0f / s;
                return new Matrix4f(
                                (m11 * (m22 * m33 - m23 * m32) + m12 * (m23 * m31 - m21 * m33)
                                                + m13 * (m21 * m32 - m22 * m31)) * s,
                                (m21 * (m02 * m33 - m03 * m32) + m22 * (m03 * m31 - m01 * m33)
                                                + m23 * (m01 * m32 - m02 * m31)) * s,
                                (m31 * (m02 * m13 - m03 * m12) + m32 * (m03 * m11 - m01 * m13)
                                                + m33 * (m01 * m12 - m02 * m11)) * s,
                                (m01 * (m13 * m22 - m12 * m23) + m02 * (m11 * m23 - m13 * m21)
                                                + m03 * (m12 * m21 - m11 * m22)) * s,
                                (m12 * (m20 * m33 - m23 * m30) + m13 * (m22 * m30 - m20 * m32)
                                                + m10 * (m23 * m32 - m22 * m33)) * s,
                                (m22 * (m00 * m33 - m03 * m30) + m23 * (m02 * m30 - m00 * m32)
                                                + m20 * (m03 * m32 - m02 * m33)) * s,
                                (m32 * (m00 * m13 - m03 * m10) + m33 * (m02 * m10 - m00 * m12)
                                                + m30 * (m03 * m12 - m02 * m13)) * s,
                                (m02 * (m13 * m20 - m10 * m23) + m03 * (m10 * m22 - m12 * m20)
                                                + m00 * (m12 * m23 - m13 * m22)) * s,
                                (m13 * (m20 * m31 - m21 * m30) + m10 * (m21 * m33 - m23 * m31)
                                                + m11 * (m23 * m30 - m20 * m33)) * s,
                                (m23 * (m00 * m31 - m01 * m30) + m20 * (m01 * m33 - m03 * m31)
                                                + m21 * (m03 * m30 - m00 * m33)) * s,
                                (m33 * (m00 * m11 - m01 * m10) + m30 * (m01 * m13 - m03 * m11)
                                                + m31 * (m03 * m10 - m00 * m13)) * s,
                                (m03 * (m11 * m20 - m10 * m21) + m00 * (m13 * m21 - m11 * m23)
                                                + m01 * (m10 * m23 - m13 * m20)) * s,
                                (m10 * (m22 * m31 - m21 * m32) + m11 * (m20 * m32 - m22 * m30)
                                                + m12 * (m21 * m30 - m20 * m31)) * s,
                                (m20 * (m02 * m31 - m01 * m32) + m21 * (m00 * m32 - m02 * m30)
                                                + m22 * (m01 * m30 - m00 * m31)) * s,
                                (m30 * (m02 * m11 - m01 * m12) + m31 * (m00 * m12 - m02 * m10)
                                                + m32 * (m01 * m10 - m00 * m11)) * s,
                                (m00 * (m11 * m22 - m12 * m21) + m01 * (m12 * m20 - m10 * m22)
                                                + m02 * (m10 * m21 - m11 * m20)) * s);
        }

        /**
         * Returns this {@link Matrix4f} scaled by the specified scalar float.
         * 
         * @param scalar The scalar to scale the matrix with.
         */
        public Matrix4f mul(float scalar) {
                Matrix4f n = new Matrix4f(this);
                n.m00 *= scalar;
                n.m01 *= scalar;
                n.m02 *= scalar;
                n.m03 *= scalar;
                n.m10 *= scalar;
                n.m11 *= scalar;
                n.m12 *= scalar;
                n.m13 *= scalar;
                n.m20 *= scalar;
                n.m21 *= scalar;
                n.m22 *= scalar;
                n.m23 *= scalar;
                n.m30 *= scalar;
                n.m31 *= scalar;
                n.m32 *= scalar;
                n.m33 *= scalar;
                return n;
        }

        /**
         * Returns the transposed version of this Matrix4f.
         */
        public Matrix4f transpose() {
                return new Matrix4f(m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33);
        }

        /**
         * Translates this {@link Matrix4f} to the given {@link Vector3f} position.
         * 
         * @param position The position the matrix will be translated to.
         * @return A new {@link Matrix4f} translated to position.
         */
        public Matrix4f translate(Vector3f position) {
                Matrix4f mat = new Matrix4f(this);
                mat.m30 = position.x;
                mat.m31 = position.y;
                mat.m32 = position.z;
                mat.m33 = 1.0f;
                return mat;
        }

        public String toString() {
                return "Matrix4f { " + this.m00 + ", " + this.m10 + ", " + this.m20 + ", " + this.m30 + ",\n"
                                + "           " + this.m01 + ", " + this.m11 + ", " + this.m21 + ", " + this.m31 + ",\n"
                                + "           " + this.m02 + ", " + this.m12 + ", " + this.m22 + ", " + this.m32 + ",\n"
                                + "           " + this.m03 + ", " + this.m13 + ", " + this.m23 + ", " + this.m33
                                + " }\n";

        }

        /**
         * Stores this {@link Matrix4f} into the specified {@link FloatBuffer} in
         * column-major order.
         * 
         * @param buffer
         */
        public void store(FloatBuffer buffer) {
                buffer.put(this.m00);
                buffer.put(this.m01);
                buffer.put(this.m02);
                buffer.put(this.m03);
                buffer.put(this.m10);
                buffer.put(this.m11);
                buffer.put(this.m12);
                buffer.put(this.m13);
                buffer.put(this.m20);
                buffer.put(this.m21);
                buffer.put(this.m22);
                buffer.put(this.m23);
                buffer.put(this.m30);
                buffer.put(this.m31);
                buffer.put(this.m32);
                buffer.put(this.m33);
        }

        public Matrix4f scale(Vector3f scale) {
                Matrix4f mat = new Matrix4f(this);
                mat.m00 *= scale.x;
                mat.m01 *= scale.x;
                mat.m02 *= scale.x;
                mat.m03 *= scale.x;
                mat.m10 *= scale.y;
                mat.m11 *= scale.y;
                mat.m12 *= scale.y;
                mat.m13 *= scale.y;
                mat.m20 *= scale.z;
                mat.m21 *= scale.z;
                mat.m22 *= scale.z;
                mat.m23 *= scale.z;
                return mat;
        }
}
