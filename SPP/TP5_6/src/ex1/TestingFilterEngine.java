package ex1;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.junit.Test;

public class TestingFilterEngine {
	IImageFilteringEngine im = new MyImageFilterEngine();

	public boolean sameImg(IImageFilteringEngine i, BufferedImage initImage){
		System.out.println("taille init : "+ initImage.getHeight() +" "+ initImage.getWidth() +"\n taille fin :"+i.getImg().getHeight() +" "+i.getImg().getWidth());
		if (i.getImg().getWidth() != initImage.getWidth() || i.getImg().getHeight() != initImage.getHeight()){
			System.out.println("pas la même taille");
		}
		for (int x = 0; x < initImage.getWidth(); x++) {
			for (int y = 0; y < initImage.getHeight(); y++) {
				if (initImage.getRGB(x,y)!=i.getImg().getRGB(x,y)){
					return false;
				}
			} // EndFor y
		} // EndFor x
		return true; 
	}

	@Test
	public void testBlackGreyFilter() throws Exception {
		im.loadImage("./TEST_IMAGES/BlackRec.png");
		im.applyFilter(new GrayLevelFilter());
		// reading image in
		BufferedImage inImg  = ImageIO.read(new File("./TEST_IMAGES/BlackRec.png"));
		
		assertTrue("pas les même images", sameImg(im,inImg) ==true);
	}

	@Test
	public void testWhiteGreyFilter() throws Exception{
		im.loadImage("./TEST_IMAGES/WhiteRec.png");
		im.applyFilter(new GrayLevelFilter());
		// reading image in
		BufferedImage inImg  = ImageIO.read(new File("./TEST_IMAGES/WhiteRec.png"));
		
		assertTrue("pas les même images", sameImg(im,inImg) ==true);
	}

	@Test
	public void testColorGreyFilterCircles() throws Exception{
		im.loadImage("./TEST_IMAGES/FourCircles.png");
		im.applyFilter(new GrayLevelFilter());
		// reading image in
		BufferedImage inImg  = ImageIO.read(new File("./TEST_IMAGES/FourCircles_Gray.png"));
		
		assertTrue("pas les même images", sameImg(im,inImg) ==true);
	}
	
	@Test
	public void testColorContourFilterCircles() throws Exception{
		im.loadImage("./TEST_IMAGES/FourCircles_Gray.png");
		im.applyFilter(new BasicContourExtractorFilter());
		// reading image in
		BufferedImage inImg  = ImageIO.read(new File("./TEST_IMAGES/FourCircles_Gray_Contour.png"));
		
		assertTrue("pas les même images", sameImg(im,inImg) ==true);
	}

	@Test
	public void testBlackContourFilter() throws Exception{
		im.loadImage("./TEST_IMAGES/BlackRec.png");
		im.applyFilter(new BasicContourExtractorFilter());
		//reading supposed result image
		BufferedImage inImg  = ImageIO.read(new File("./TEST_IMAGES/WhiteRec.png"));
		
		assertTrue("pas les même images", sameImg(im,inImg) ==true);
	}

	@Test 
	public void testWhiteContourFilter() throws Exception{
		im.loadImage("./TEST_IMAGES/WhiteRec.png");
		im.applyFilter(new BasicContourExtractorFilter());
		// reading image fin
		BufferedImage inImg  = ImageIO.read(new File("./TEST_IMAGES/WhiteRecContour.png"));
		
		assertTrue("pas les même images", sameImg(im,inImg) ==true);
	}

	@Test
	public void testColorContourFilter(){
		fail("Not yet implemented");
	}


}
