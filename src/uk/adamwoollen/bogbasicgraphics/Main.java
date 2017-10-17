package uk.adamwoollen.bogbasicgraphics;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
				error = "Args are not RGB values: setting default values.\nArguments must be numerical values between 0 and 255";
				pp = new PixelPainter(255, 255, 255);
			}
		}
		
		//If no arguments given, a GUI option to display 4 colours displays.
		else {
			Object[] colourOptions = {"White", "Red", "Green", "Blue"};
			int chosenColour = JOptionPane.showOptionDialog(null, 
					"Which colour would you like to display?", 
					"Colour Options",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					colourOptions,
					colourOptions[0]);
			
			switch(chosenColour) {
				case 0: 
					pp = new PixelPainter(255, 255, 255);
					break;
				
				case 1:
					pp = new PixelPainter(255, 0, 0);
					break;
				
				case 2:
					pp = new PixelPainter(0, 255, 0);
					break;
				
				case 3:
					pp = new PixelPainter(0, 0, 255);
					break;
			}
		}
		mainFrame.add(pp);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.println(error);
	}

}
