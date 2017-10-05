package uk.adamwoollen.bogbasicgraphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PixelPainter extends JPanel{

	/**
	 * Contains constructor and a method for drawing the graphics.
	 * @author Adam Woollen
	 */
	private static final long serialVersionUID = 1242870092798244754L;
	
	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	private Integer[] colours;
	
	public PixelPainter(int red, int blue, int green) {
		setPreferredSize(new Dimension(WIDTH * 2, HEIGHT * 2));
		this.colours = new Integer[3];
		this.colours[0] = red;
		this.colours[1] = blue;
		this.colours[2] = green;
		for(int colour : colours) {
			System.out.println(colour);
		}
	}
	

	@Override
	public void paintComponent(Graphics g) {
		//Setup painting stuff
		final BufferedImage image;

		image = (BufferedImage) createImage(WIDTH, HEIGHT);
		WritableRaster raster = image.getRaster();
		
		///////////////////////////////////////////////////////////////////
		//My experimental code that will probably go wrong
		
		/*
		 * Plan of action:
		 * for every face, draw lines between every vertex
		 */
		
		ArrayList<int[]> linePoints = new ArrayList<int[]>();
		
		ThreeDObject pyramid = new ThreeDObject();
		Transform.scale(pyramid, 75);
		Transform.translate(pyramid, 50, 50, 0);
		Transform.rotateAboutX(pyramid, 190);
		Transform.rotateAboutY(pyramid, 30);
		Transform.translate(pyramid, 50, 150, 0);
	
	
	
		int[][] faces = pyramid.getFaces();
		double[][] vertices = pyramid.getVertices();
		
		for(double[] vertex : vertices){
			System.out.println("original point at " + vertex[0] + ", " + vertex[1]);
		}
		
		//For every face
		for(int faceNum = 0; faceNum < faces.length; faceNum++){
			int[] face = faces[faceNum];
			System.out.println("Drawing face " + faceNum);
			//Get every vertex - note that all shapes should really be triangles but, this should handle not triangles, too.
			for(int vertexNum = 0; vertexNum < face.length; vertexNum++){
				//int actualVertexNum = face[vertexNum];
				double[] vertex1 = vertices[face[vertexNum]];
				double[] vertex2 = vertices[face[(vertexNum + 1) % (face.length)]];
				System.out.println("0 - Drawing between points " + vertexNum + " and " + (vertexNum + 1) % (face.length));
				/*
				 * How to draw lines?
				 * Calculate gradient and then start drawing!
				 */
				double xDif = vertex2[0] - vertex1[0];
				double yDif = vertex2[1] - vertex1[1];
				double gradient = yDif / xDif;
				double invGradient = xDif / yDif;
				
				System.out.println("1 - Drawing between points " + vertexNum + " and " + (vertexNum + 1) % (face.length));
				
				//Now we have the gradient, let's start at vertex1 and draw!
				double x = vertex1[0];
				double y = vertex1[1];
				
				//Draw in x direction
				if(vertex1[0] < vertex2[0]){
					for(int xStep = (int) vertex1[0]; xStep < vertex2[0]; xStep++){
						int[] point = {xStep, (int) y};
						
						if(point[0] >= 0 && point[0] < WIDTH && point[1] >= 0 && point[1] < HEIGHT){
							linePoints.add(point);
							System.out.println("(x-run) Added point " + point[0] + ", " + point[1]);
						}
						y += gradient;
					}
				} else{
					for(int xStep = (int) vertex2[0]; vertex2[0] < xStep; xStep--){
						int[] point = {xStep, (int) y};
						if(point[0] >= 0 && point[0] < WIDTH && point[1] >= 0 && point[1] < HEIGHT){
							linePoints.add(point);
							System.out.println("(x-run) Added point " + point[0] + ", " + point[1]);
						}
						y -= gradient;
					}
				}
				
				
				System.out.println("2 - Drawing between points " + vertexNum + " and " + (vertexNum + 1) % (face.length));
				
				//Draw in y direction
				if(vertex1[1] < vertex2[1]){
					for(int yStep = (int) vertex1[1]; yStep < vertex2[1]; yStep++){
						int[] point = {(int) x, yStep};
						if(point[0] >= 0 && point[0] < WIDTH && point[1] >= 0 && point[1] < HEIGHT){
							linePoints.add(point);
							System.out.println("(y-run) Added point " + point[0] + ", " + point[1]);
						}
						x += invGradient;
					}
				} else{
					for(int yStep = (int) vertex1[1]; vertex2[1] < yStep; yStep--){
						int[] point = {(int) x, yStep};
						if(point[0] >= 0 && point[0] < WIDTH && point[1] >= 0 && point[1] < HEIGHT){
							linePoints.add(point);
							System.out.println("(y-run) Added point " + point[0] + ", " + point[1]);
						}
						x -= invGradient;
					}
				}
				
				System.out.println("3 - Drawing between points " + vertexNum + " and " + (vertexNum + 1) % (face.length));
			}
		}
		
		
		
		
		//////////////////////////////////////////////////////////////////
		
		//Do the painting
		//Set colour of pixel
		int[] iArray = { 0, 0, 0, 255 };
		
		

		//Paint coordinate x, y, colour
		raster.setPixel(0, 0, iArray);
		
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j ++){
				raster.setPixel(i, j, iArray);
			}
		}
		//lear screen
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		//Aston green, I think - close enough, anyway
		iArray[0] = colours[0];
		iArray[1] = colours[1];
		iArray[2] = colours[2];
		
		for (int[] point : linePoints) {
			//System.out.println("Drawing point " + point[0] + ", " + point[1]);
			raster.setPixel(point[0], point[1], iArray);
		}
		
		//Draw image (Wow, really?!)
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		
	
		
	}
	
}
