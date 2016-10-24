package uk.adamwoollen.bogbasicgraphics;

public class Transform {
	
	//I will eventually use a 4D matrix to translate instead
	/**
	 * Translate the given object by the given values
	 * @param obj The 3DObject to be translated
	 * @param x The amount to shift on the x axis
	 * @param y The amount to shift on the y axis
	 * @param z The amount to shift on the z axis
	 */
	public static void translate(ThreeDObject obj, double x, double y, double z){
		
		double[][] vertices = obj.getVertices();
		
		for (int i = 0; i < vertices.length; i++) {// for each coordinate
			vertices[i][0] += x;
			vertices[i][1] += y;
			vertices[i][2] += z;
		}
	}
	
	/**
	 * Scales the given object from the origin
	 * @param obj
	 * @param scale
	 */
	public static void scale(ThreeDObject obj, double scale){
		double[][] vertices = obj.getVertices();
		
		for (int i = 0; i < vertices.length; i++) {// for each coordinate
			vertices[i][0] *= scale;
			vertices[i][1] *= scale;
			vertices[i][2] *= scale;
		}
	}
	
}
