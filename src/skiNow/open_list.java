package skiNow;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class open_list {
	private final static String imageLink = "C:/Users/Cammy/eclipse-workspace/SkiNow/xlarge.jpg";
	private final static String saveImageLink = "C:/Users/Cammy/eclipse-workspace/SkiNow/saved.jpg";
	private final static String finishImageLink = "C:/Users/Cammy/eclipse-workspace/SkiNow/finalMontage.jpg";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedImage myImage;
		try {
			myImage = ImageIO.read(new File(imageLink));
			
			System.out.println(myImage.getWidth());
			System.out.println(myImage.getHeight());
			
			mountain mtn = new mountain();
			int runs[][] = mtn.getRunLine();
			
			for (int x = 0; x < 274; x++) {
				myImage.setRGB(runs[x][0] - 2, runs[x][1] - 10, Color.red.getRGB());
				myImage.setRGB(runs[x][0] - 1, runs[x][1] - 10, Color.red.getRGB());
				myImage.setRGB(runs[x][0], runs[x][1] - 10, Color.red.getRGB());
				myImage.setRGB(runs[x][0] + 1, runs[x][1] - 10, Color.red.getRGB());
				myImage.setRGB(runs[x][0] + 2, runs[x][1] - 10, Color.red.getRGB());
			}
			
			File outputfile = new File(finishImageLink);
			ImageIO.write(myImage, "jpg", outputfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		

		
		

	}

}
