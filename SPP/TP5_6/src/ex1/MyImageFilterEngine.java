package ex1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public class MyImageFilterEngine implements IImageFilteringEngine {
	private BufferedImage buffIn; 
	private BufferedImage buffOut;
	private int margin; 

	@Override
	public void loadImage(String inputImage) throws Exception {
		buffIn  = ImageIO.read(new File(inputImage));
		buffOut = new BufferedImage(buffIn.getWidth()-2*margin,
				buffIn.getHeight()-2*margin,
				BufferedImage.TYPE_INT_RGB);
	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {
		// writing out new image
		File f = new File(outFile);
		ImageIO.write(buffOut, "png", f);
	

	}

	@Override
	public void setImg(BufferedImage newImg) {
		buffIn=newImg;

	}

	public int getMargin(){
		return margin;
	}
	@Override
	public BufferedImage getImg() {
		return buffOut;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		margin=someFilter.getMargin();
		buffOut = new BufferedImage(buffIn.getWidth()-2*margin,
				buffIn.getHeight()-2*margin,
				BufferedImage.TYPE_INT_RGB);
		for (int x = someFilter.getMargin(); x <buffIn.getWidth()-someFilter.getMargin(); x++) {
			for (int y = someFilter.getMargin(); y < buffOut.getHeight()-someFilter.getMargin(); y++) {
				someFilter.applyFilterAtPoint(x, y, buffIn,buffOut);
			}

		}

	}
}
