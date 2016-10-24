package uk.adamwoollen.bogbasicgraphics;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		PixelPainter pp = new PixelPainter();
		mainFrame.add(pp);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

}
