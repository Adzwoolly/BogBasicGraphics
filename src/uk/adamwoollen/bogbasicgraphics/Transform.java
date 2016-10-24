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
	
	/**
	 * Rotates an object the specified number of degrees
	 * @param obj
	 * @param angle
	 */
	public static void rotateAboutX(ThreeDObject obj, int angle) {

		double[][] vertices = obj.getVertices();

		double angleRadians = Math.toRadians(angle); 
		
		double cos = Math.cos(angleRadians);
		double sin = Math.sin(angleRadians);
		
		System.out.println("cos(" + angle + ") is " + cos);
		System.out.println("sin(" + angle + ") is " + sin);

		// A matrix used to rotate about the x axis
		double[][] rX = { { 1, 0, 0 }, { 0, cos, -sin }, { 0, sin, cos } };
		
		double[] newVertex = new double[3];

		for (int i = 0; i < vertices.length; i++) {// for each coordinate
			//System.out.println("Original Co-ordinate " + i + " is " + vertices[i][0] + ", " + vertices[i][1] + ", " + vertices[i][2]);
			for (int j = 0; j < 3; j++) {// for each dimension
				double top = (rX[j][0] * vertices[i][0]);
				double middle = (rX[j][1] * vertices[i][1]);
				double bottom = (rX[j][2] * vertices[i][2]);
				newVertex[j] = top + middle + bottom;
			}
			vertices[i] = newVertex.clone();
			//System.out.println("Shifted  Co-ordinate " + i + " is " + vertices[i][0] + ", " + vertices[i][1] + ", " + vertices[i][2]);
			
			/*if(i > 0){
				System.out.println("Shifted  Co-ordinate " + (i - 1) + " is " + vertices[i - 1][0] + ", " + vertices[i - 1][1] + ", " + vertices[i - 1][2]);
			}*/
			
		}
	}
	
	public static void rotateAboutY(ThreeDObject obj, int angle) {

		double[][] vertices = obj.getVertices();

		double angleRadians = Math.toRadians(angle); 
		
		double cos = Math.cos(angleRadians);
		double sin = Math.sin(angleRadians);
		
		System.out.println("cos(" + angle + ") is " + cos);
		System.out.println("sin(" + angle + ") is " + sin);

		// A matrix used to rotate about the x axis
		double[][] rY = { { cos, 0, sin }, { 0, 1, 0 }, { -sin, 0, cos } };
		
		/*
		 * 1	0	0
		 * 0	cos	-sin
		 * 0	sin	cos
		 */
		
		double[] newVertex = new double[3];

		for (int i = 0; i < vertices.length; i++) {// for each coordinate
			//System.out.println("Original Co-ordinate " + i + " is " + vertices[i][0] + ", " + vertices[i][1] + ", " + vertices[i][2]);
			for (int j = 0; j < 3; j++) {// for each dimension
				double top = (rY[j][0] * vertices[i][0]);
				double middle = (rY[j][1] * vertices[i][1]);
				double bottom = (rY[j][2] * vertices[i][2]);
				newVertex[j] = top + middle + bottom;
			}
			vertices[i] = newVertex.clone();
			//System.out.println("Shifted  Co-ordinate " + i + " is " + vertices[i][0] + ", " + vertices[i][1] + ", " + vertices[i][2]);
			
			/*if(i > 0){
				System.out.println("Shifted  Co-ordinate " + (i - 1) + " is " + vertices[i - 1][0] + ", " + vertices[i - 1][1] + ", " + vertices[i - 1][2]);
			}*/
			
		}
	}
	
}
