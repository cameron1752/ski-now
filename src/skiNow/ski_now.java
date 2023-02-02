package skiNow;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import cautious_invention.CautiousInvention;

public class ski_now {
	final static Logger logger = LogManager.getLogger(ski_now.class);
	private final static String imageLink = "C:/Users/Cammy/eclipse-workspace/SkiNow/xlarge.jpg";
	private final static String saveImageLink = "C:/Users/Cammy/eclipse-workspace/SkiNow/saved.jpg";
	private final static String finishImageLink = "C:/Users/Cammy/eclipse-workspace/SkiNow/final.jpg";
	private final static int dimension = 10;
	private final static int dif = 40;
	

// 	blue square kind of 
//	private static int lRed = 27;
//	private static int lGreen = 95;
//	private static int lBlue = 144;
	
//	black diamond
//	private static int lRed = 0;
//	private static int lGreen = 0;
//	private static int lBlue = 0;
//	
//	grey for black diamonds
//	private static int lRed1 = 67;
//	private static int lGreen1 = 67;
//	private static int lBlue1 = 67;

// 	green circle
//	private static int lRed = 22;
//	private static int lGreen = 139;
//	private static int lBlue = 60;

	// 	red
//	private static int lRed = 232;
//	private static int lGreen = 33;
//	private static int lBlue = 37;
	private static int colors[][] = new int[dimension][3];
	
	
	
	
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		PropertyConfigurator.configure("log4j.info");
//		 TODO Auto-generated method stub
		// tree green
		colors[0][0] = 14;
		colors[0][1] = 87;
		colors[0][2] = 42;
		
		// tree green 2
		colors[1][0] = 58;
		colors[1][1] = 116;
		colors[1][2] = 76;
		
		// tree green 3
		colors[2][0] = 111;
		colors[2][1] = 161;
		colors[2][2] = 136;
		
		// white
		colors[3][0] = 255;
		colors[3][1] = 255;
		colors[3][2] = 255;
		
		// sky blue
		colors[4][0] = 166;
		colors[4][1] = 201;
		colors[4][2] = 229;
		
		// grey in trees
		colors[5][0] = 156;
		colors[5][1] = 167;
		colors[5][2] = 159;
		
		// lighter grey
		colors[6][0] = 190;
		colors[6][1] = 196;
		colors[6][2] = 192;
		
		// idk a third grey
		colors[7][0] = 200;
		colors[7][1] = 200;
		colors[7][2] = 200;
		
		// gross fkn yellow
		colors[8][0] = 254;
		colors[8][1] = 182;
		colors[8][2] = 18;
		
		// another yeller
		colors[9][0] = 213;
		colors[9][1] = 203;
		colors[9][2] = 109;
		
		getColorArray(imageLink, saveImageLink, true);
		
		getColorArray(saveImageLink, finishImageLink, false);
		
	}
	
	public static int[][] getColorArray(String in, String out, boolean removeColor) {
		try {
			BufferedImage myImage = ImageIO.read(new File(in));
			
			int pix[][] = new int[myImage.getWidth()][myImage.getHeight()];
			
			System.out.println(myImage.getWidth());
			System.out.println(myImage.getHeight());
			
			for (int x = 0; x < myImage.getWidth(); x++) {
				for (int y = 0; y < myImage.getHeight(); y++) {
					// save color value
					int color = myImage.getRGB(x, y);
					// write color to array
			        pix[x][y] = color;
				}
			}
			
			if (removeColor) {
				removeColor(pix, myImage.getWidth(), myImage.getHeight(), myImage);
			} else {
				addColor(pix, myImage.getWidth(), myImage.getHeight(), myImage);
			}
			
			
		    // save changed file
		    File outputfile = new File(out);
		    ImageIO.write(myImage, "jpg", outputfile);
		    return pix;
		    
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pix[][] = new int[1][2];
		return pix;
	}
	
	public static void removeColor(int pix[][], int length, int height, BufferedImage myImage) {
		int count = 0;
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < height; y++) {

				int red = getRed(pix[x][y]);
				int green = getGreen(pix[x][y]);
				int blue = getBlue(pix[x][y]);

				
				if (x == 936 && y == 530) {
					System.out.println(getColor(pix[x][y]));
				}
				
				for (int i = 0; i < dimension; i++) {
					
					if (isEqual(red, colors[i][0]) && isEqual(green, colors[i][1]) && isEqual(blue, colors[i][2])){
						
						myImage.setRGB(x, y, Color.white.getRGB());

						count++;
					}
				}
			}
		}
		
		System.out.println(count + " of " + length * height +  " pixels changed");
		System.out.println(length + " x " +  height);
	}
	
	public static void addColor(int pix[][], int length, int height, BufferedImage myImage) {
		int runs[][] = new int[length][height];
		
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < height; y++) {
				
				if ((x + 5 < length) && (x - 5 > 0)) {
					if ((y + 5 < height) && (y - 5 > 0)) {
						
						// check to see if surrounding pixels are also colored?
						int red = getRed(pix[x][y]);
						int green = getGreen(pix[x][y]);
						int blue = getBlue(pix[x][y]);
						
						if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
							// x y, x + 1 y, x - 1 y, x y + 1, x y - 1, x + 1 y + 1, x - 1 y - 1, x - 1 y + 1, x + 1 y -1
							red = getRed(pix[x + 1][y]);
							green = getGreen(pix[x + 1][y]);
							blue = getBlue(pix[x + 1][y]);
							
							if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
								runs[x][y] = 1;
								myImage.setRGB(x, y, Color.red.getRGB());
							} else {
								red = getRed(pix[x - 1][y]);
								green = getGreen(pix[x - 1][y]);
								blue = getBlue(pix[x - 1][y]);
								
								if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
									runs[x][y] = 1;
									myImage.setRGB(x, y, Color.red.getRGB());
								} else {
									red = getRed(pix[x][y - 1]);
									green = getGreen(pix[x][y - 1]);
									blue = getBlue(pix[x][y - 1]);
									
									if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
										runs[x][y] = 1;
										myImage.setRGB(x, y, Color.red.getRGB());
									} else {
										red = getRed(pix[x][y + 1]);
										green = getGreen(pix[x][y + 1]);
										blue = getBlue(pix[x][y + 1]);
										
										if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
											runs[x][y] = 1;
											myImage.setRGB(x, y, Color.red.getRGB());
										} else {
											red = getRed(pix[x + 1][y + 1]);
											green = getGreen(pix[x + 1][y + 1]);
											blue = getBlue(pix[x + 1][y + 1]);
											
											if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
												runs[x][y] = 1;
												myImage.setRGB(x, y, Color.red.getRGB());
											} else {
												red = getRed(pix[x - 1][y - 1]);
												green = getGreen(pix[x - 1][y - 1]);
												blue = getBlue(pix[x - 1][y - 1]);
												
												if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
													runs[x][y] = 1;
													myImage.setRGB(x, y, Color.red.getRGB());
												} else {
													red = getRed(pix[x - 1][y + 1]);
													green = getGreen(pix[x - 1][y + 1]);
													blue = getBlue(pix[x - 1][y + 1]);
													
													if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
														runs[x][y] = 1;
														myImage.setRGB(x, y, Color.red.getRGB());
													} else {
														red = getRed(pix[x + 1][y - 1]);
														green = getGreen(pix[x + 1][y - 1]);
														blue = getBlue(pix[x + 1][y - 1]);
														
														if (!isEqual(red, colors[3][0]) && !isEqual(green, colors[3][1]) && !isEqual(blue, colors[3][2])){
															runs[x][y] = 1;
															myImage.setRGB(x, y, Color.red.getRGB());
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		String temp = "";
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < length; x++) {
				temp = temp + runs[x][y];
			}
			logger.info(temp);
			temp = "";
		}
		
		
		
		
	}
	
	public static String getColor(int color) {
        int red =   (color & 0x00ff0000) >> 16;
        int green = (color & 0x0000ff00) >> 8;
        int blue =   color & 0x000000ff;
        
        return "RGB: [" + red + ", " + green + ", " + blue + "] ";
	}
	
	public static int getRed(int color) {
		return (color & 0x00ff0000) >> 16;
	}
	
	public static int getGreen(int color) {
		return (color & 0x0000ff00) >> 8;
	}
	
	public static int getBlue(int color) {
		return color & 0x000000ff;
	}
	
	public static boolean isEqual(int color, int base) {
	
		if (color == base) {
			return true;
		} else if (Math.abs(color - base) <= dif) {
			return true;
		}
		return false;
	}
	
	

}
