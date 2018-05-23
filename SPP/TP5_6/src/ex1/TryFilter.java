package ex1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import part2.MultiThreadedImageFilteringEngine;

public class TryFilter {
	public static void createImage(int a, String name, int size) throws IOException{
		// creating new image
		BufferedImage outImg = new BufferedImage(size,
				size,
				BufferedImage.TYPE_INT_RGB);
		// generating new image from original
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				// extracting red, green and blue components from rgb integer
				int blue   = (a      ) & 0x000000FF;
				// computing new color from extracted components
				int newRgb = ( ( (blue << 8) | blue) << 8 ) |blue ;
				outImg.setRGB(x,y,newRgb);
			} // EndFor y
		} // EndFor x
		// writing out new image
		File f = new File("TEST_IMAGES/"+name+".png");
		ImageIO.write(outImg, "png", f);
	}

	public static void main(String[] args) throws Exception {
/*
		IImageFilteringEngine im = new MyImageFilterEngine();
		im.loadImage("./TEST_IMAGES/15226222451_75d515f540_o.jpg");
		im.applyFilter(new GrayLevelFilter());
		//im.writeOutPngImage("./TEST_IMAGES/testGrey.png");
		//im.loadImage("./TEST_IMAGES/testGrey.png");
		
		im.applyFilter(new BasicContourExtractorFilter());
		im.writeOutPngImage("./TEST_IMAGES/resultContour.png");
		System.out.println(System.currentTimeMillis()-debut);*/
		/*createImage(0,"BlackRec",256);
		createImage(255,"WhiteRecContour", 254);
		createImage(255,"WhiteRecContour", 254);
		im.loadImage("./TEST_IMAGES/BlackRec.png");
		im.applyFilter(new BasicContourExtractorFilter());*/
		//im.writeOutPngImage("./TEST_IMAGES/testset.png");*/
		/*im.loadImage("./TEST_IMAGES/WhiteRec.png");
		im.applyFilter(new BasicContourExtractorFilter());
		im.writeOutPngImage("./TEST_IMAGES/testset.png");*/
		
		/*long debut = System.currentTimeMillis();
	
			IImageFilteringEngine im = new MyImageFilterEngine();
			im.loadImage("./TEST_IMAGES/15226222451_75d515f540_o.jpg");
			im.applyFilter(new GrayLevelFilter());
			im.applyFilter(new BasicContourExtractorFilter());
			im.writeOutPngImage("./TEST_IMAGES/resultContour.png");
			System.out.println("temps exec unithread  "+im.getImg().getHeight()+" "+im.getImg().getWidth()+" :" +(System.currentTimeMillis()-debut));
			//Random random = new Random();
			//int nb;
			//nb = random.nextInt(10)+1;
*/
			long debut = System.currentTimeMillis();
			IImageFilteringEngine mult= new MultiThreadedImageFilteringEngine(2);
			mult.loadImage("./TEST_IMAGES/15226222451_75d515f540_o.jpg");
			mult.applyFilter(new GrayLevelFilter());
			mult.applyFilter(new BasicContourExtractorFilter());
			mult.writeOutPngImage("./TEST_IMAGES/resultContourbis.png");
			System.out.println("temps exec multithread num thread "+mult.getImg().getHeight()+" "+mult.getImg().getWidth()+" :"+ (System.currentTimeMillis()-debut));
			
		
	}

}
