package com.dcronqvist.engine.math;

public class SurfaceMath {

    /**
     * Calculates the normal of a surface defined by points v1, v2 and v3 and stores
     * it in dest. v1, v2 and v3 are not modified
     */
    public static Vector3f normal(Vector3f v1, Vector3f v2, Vector3f v3) {
        Vector3f vec = new Vector3f(((v2.y - v1.y) * (v3.z - v1.z)) - ((v2.z - v1.z) * (v3.y - v1.y)),
                ((v2.z - v1.z) * (v3.x - v1.x)) - ((v2.x - v1.x) * (v3.z - v1.z)),
                ((v2.x - v1.x) * (v3.y - v1.y)) - ((v2.y - v1.y) * (v3.x - v1.x)));
        return vec;
    }

    /**
     * Calculates the surface tangent for the three supplied vertices and UV
     * coordinates.
     * 
     * @param v1  XYZ of first vertex
     * @param uv1 UV of first vertex
     * @param v2  XYZ of second vertex
     * @param uv2 UV of second vertex
     * @param v3  XYZ of third vertex
     * @param uv3 UV of third vertex
     */
    public static Vector3f tangent(Vector3f v1, Vector2f uv1, Vector3f v2, Vector2f uv2, Vector3f v3, Vector2f uv3) {
        float f = 1.0f / ((uv2.x - uv1.x) * (uv3.y - uv1.y) - (uv3.x - uv1.x) * (uv2.y - uv1.y));

        Vector3f vec = new Vector3f(f * ((uv3.y - uv1.y) * (v2.x - v1.x) - (uv2.y - uv1.y) * (v3.x - v1.x)),
                f * ((uv3.y - uv1.y) * (v2.y - v1.y) - (uv2.y - uv1.y) * (v3.y - v1.y)),
                f * ((uv3.y - uv1.y) * (v2.z - v1.z) - (uv2.y - uv1.y) * (v3.z - v1.z)));
        return vec;
    }

    /**
     * Calculates the surface binormal for the three supplied vertices and UV
     * coordinates.
     * 
     * @param v1  XYZ of first vertex
     * @param uv1 UV of first vertex
     * @param v2  XYZ of second vertex
     * @param uv2 UV of second vertex
     * @param v3  XYZ of third vertex
     * @param uv3 UV of third vertex
     */
    public static Vector3f binormal(Vector3f v1, Vector2f uv1, Vector3f v2, Vector2f uv2, Vector3f v3, Vector2f uv3) {
        float f = 1.0f / ((uv2.x - uv1.x) * (uv3.y - uv1.y) - (uv3.x - uv1.x) * (uv2.y - uv1.y));

        Vector3f vec = new Vector3f(f * ((uv2.x - uv1.x) * (v3.x - v1.x) - (uv3.x - uv1.x) * (v2.x - v1.x)),
                f * ((uv2.x - uv1.x) * (v3.y - v1.y) - (uv3.x - uv1.x) * (v2.y - v1.y)),
                f * ((uv2.x - uv1.x) * (v3.z - v1.z) - (uv3.x - uv1.x) * (v2.z - v1.z)));
        return vec;
    }

}
