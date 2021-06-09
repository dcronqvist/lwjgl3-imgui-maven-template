package com.dcronqvist.engine.math;

import static com.dcronqvist.engine.math.TrigMath.coTangent;
import static com.dcronqvist.engine.math.TrigMath.degreesToRadians;

public class CamMath {

    /**
     * Calculates a perspective projection matrix using the supplied parameters and
     * stores the result in dest
     * 
     * @param fovy   Field of view
     * @param aspect Aspect ratio (display width / display height)
     * @param zNear  near clipping plane distance
     * @param zFar   far clipping plane distance
     * @param dest   Matrix4f to store the result
     */
    public static Matrix4f perspective(final float fovy, final float aspect, final float zNear, final float zFar) {
        float y_scale = coTangent(degreesToRadians(fovy / 2.0f));
        float x_scale = y_scale / aspect;
        float frustrum_length = zFar - zNear;

        Matrix4f mat = new Matrix4f();

        mat.m00 = x_scale;
        mat.m11 = y_scale;
        mat.m22 = -((zFar + zNear) / frustrum_length);
        mat.m23 = -1.0f;
        mat.m32 = -((2.0f * zNear * zFar) / frustrum_length);
        return mat;
    }

    /**
     * Calculates a view matrix
     * 
     * @param position The position of the camera
     * @param centre   The point in space to look at
     * @param up       The direction of "up". In most cases it is (x=0, y=1, z=0)
     * @param dest     The matrix to store the results in
     */
    public static Matrix4f lookAt(Vector3f position, Vector3f centre, Vector3f up) {
        Matrix4f mat = new Matrix4f(1.0f);

        Vector3f f = centre.sub(position).normalize();
        Vector3f s = up.cross(f).normalize();
        Vector3f u = f.cross(s);

        mat.m00 = s.x;
        mat.m10 = s.y;
        mat.m20 = s.z;
        mat.m01 = u.x;
        mat.m11 = u.y;
        mat.m21 = u.z;
        mat.m02 = -f.x;
        mat.m12 = -f.y;
        mat.m22 = -f.z;
        mat.m30 = -s.dot(position);
        mat.m31 = -u.dot(position);
        mat.m32 = f.dot(position);

        return mat;
    }

    /**
     * Calculates and returns an orthographic projection matrix using the supplied
     * parameters.
     * 
     * @param left   The left-most coordinate.
     * @param right  The right-most coordinate.
     * @param bottom The bottom coordinate.
     * @param top    The top coordinate.
     * @param zNear  The near plane
     * @param zFar   The far plane
     * @return The projection matrix.
     */
    public static Matrix4f ortho(float left, float right, float bottom, float top, float zNear, float zFar) {
        Matrix4f dest = new Matrix4f();

        dest.m00 = 2.0f / (right - left);
        dest.m11 = 2.0f / (top - bottom);
        dest.m22 = (-2.0f) / (zFar - zNear);
        dest.m30 = -((right + left) / (right - left));
        dest.m31 = -((top + bottom) / (top - bottom));
        dest.m32 = -((zFar + zNear) / (zFar - zNear));
        dest.m33 = 1.0f;
        return dest;
    }
}
