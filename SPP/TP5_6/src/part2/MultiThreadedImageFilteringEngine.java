package part2;

import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.io.File;

import javax.imageio.ImageIO;

import ex1.IFilter;
import ex1.IImageFilteringEngine;

public class MultiThreadedImageFilteringEngine extends Thread implements IImageFilteringEngine {
	static BufferedImage buffIn; 
	static BufferedImage buffOut;
	private int numberThread; 
	static CyclicBarrier barrier;
	static CyclicBarrier barrier2;
	static ThreadWorker [] threadWorking;

	public MultiThreadedImageFilteringEngine (int p){
		this.numberThread=p;
		
		//initialisation des barrières
		barrier = new CyclicBarrier(p+1);
		barrier2 = new CyclicBarrier(p+1);
		threadWorking = new ThreadWorker[p];

		//création des threads workers
		for (int i=0; i<p ; i++) {
			threadWorking[i] = new ThreadWorker();
			threadWorking[i].start();
		}
	}

	@Override
	public void loadImage(String inputImage) throws Exception {
		buffIn  = ImageIO.read(new File(inputImage));
		buffOut = new BufferedImage(buffIn.getWidth(),
				buffIn.getHeight(),
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

	@Override
	public BufferedImage getImg() {
		return buffOut;
	}

	public BufferedImage getImgIn(){
		return buffIn;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		int size= someFilter.getMargin();
		buffOut = new BufferedImage(buffIn.getWidth()-2*someFilter.getMargin(),
				buffIn.getHeight()-2*someFilter.getMargin(),
				BufferedImage.TYPE_INT_RGB);
	for(int i=0; i<threadWorking.length; i++){
		threadWorking[i].setFilter(someFilter);
		threadWorking[i].setDebut(size);
		size +=((buffOut.getHeight()- 2*someFilter.getMargin())/this.numberThread);
		threadWorking[i].setFin(size);
	}
	try {
		barrier.await();
	} catch (InterruptedException | BrokenBarrierException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		barrier2.await();
	} catch (InterruptedException | BrokenBarrierException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
