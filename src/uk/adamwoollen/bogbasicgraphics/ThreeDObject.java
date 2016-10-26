package uk.adamwoollen.bogbasicgraphics;

/**
 * Class to contain the vertices for 3D objects.
 * @author Adzwoolly
 *
 */
public class ThreeDObject {
	
	private double[][] vertices = {{0, 0, 0}, {1, 0, 0}, {0, 0, 1}, {1, 0, 1}, {0.5, 1, 0.5}};
	
	private int[][] faces = {{0, 1, 4}, {1, 3, 4}, {0, 2, 4}, {2, 3, 4}, {0, 1, 2}, {1, 2, 3}};
	
	public void setScale(int scale){
		for(int i = 0; i < vertices.length; i++){
			for(int j = 0; j < 3; j++){
				vertices[i][j] = vertices[i][j] * scale;
			}
		}
	}
	
	public double[][] getVertices(){
		return vertices;
	}
	
	public int[][] getFaces(){
		return faces;
	}
	
}
