import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class mainer {

	public static void main(String[] args) throws IOException {

		File image = new File(args[0]);
		BufferedImage bufimg = ImageIO.read(image);
		BufferedImage modimg = ImageIO.read(image);

		String message = args[1];

		int numbytes = message.length();

		// store the message's bytes in an int array

		char[] bytes = new char[numbytes];
		for (int i = 0; i < numbytes; i++) {
			bytes[i] = message.charAt(i);
		}

		System.out.println("Before the change:");
		displayImage(bufimg);
		int count = 0;
		while (count < numbytes) {
			for (int x = 0; x < bufimg.getWidth() && count < numbytes; x++) {
				for (int y = 0; y < bufimg.getHeight() && count < numbytes; y++) {
					int clr = modimg.getRGB(x, y);
					int red = (clr & 0x00ff0000) >> 16;
					int green = (clr & 0x0000ff00) >> 8;
					int blue = clr & 0x000000ff;
					
					//Modify red color
					red = red + bytes[y];
					
					//Re-pack the RGB int/pixel
					Color myColor = new Color(red, green, blue);
					int rgb = myColor.getRGB();
					modimg.setRGB(x, y, rgb);
					count++;
				}
			}
		}
		System.out.println("After the change:");
		displayImage(modimg);

		System.out.println("Colored " + count + " pixels");
	}

	public static void displayImage(BufferedImage img) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
	}
}
