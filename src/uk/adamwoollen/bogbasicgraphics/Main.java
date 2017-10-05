package uk.adamwoollen.bogbasicgraphics;

import java.awt.Component;

import javax.swing.JFrame;

public class Main {
	
	/**
	 * @author Adam Woollen 
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		System.out.println(args.length);
		PixelPainter pp = null;
		Integer[] colours = new Integer[3];
		String error = "";
		if(args.length == 3) {
			boolean rgbValues = true;
			for(int i = 0; i < 3; i++) {
				colours[i] = Integer.parseInt(args[i]);
				if(colours[i] < 0 || colours[i] > 255){
					rgbValues = false;
				}
			}
			if(rgbValues){
				pp = new PixelPainter(colours[0], colours[1], colours[2]);
			}
			else{
				error = "Args are not RGB values: setting default values.\nArguments must be numerical valuesb between 0 and 255";
				pp = new PixelPainter(255, 255, 255);
			}
		}
		else {
			pp = new PixelPainter(255, 255, 255);
			error = "Argument number != 3";
		}
		mainFrame.add(pp);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.println(error);
	}

}
