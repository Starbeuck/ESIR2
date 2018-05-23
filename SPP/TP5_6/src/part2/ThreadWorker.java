package part2;

import java.awt.image.BufferedImage;
import java.util.concurrent.BrokenBarrierException;

import ex1.IFilter;


public class ThreadWorker extends Thread {
	int debut; 
	int fin; 
	IFilter filter;

	public ThreadWorker(int deb, int end){
		this.debut=deb;
		this.fin=end;
	}

	public ThreadWorker(){
	
	}

	public void setDebut(int debut) {
		this.debut = debut;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

	public void run(){
		//while permet d'eviter que les thread ne meurent sinon mort à la fin du programme 

		while(true){
			try {
				MultiThreadedImageFilteringEngine.barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
				for (int y =debut; y < fin; y++) {
					for (int x = filter.getMargin(); x <MultiThreadedImageFilteringEngine.buffIn.getWidth()-filter.getMargin(); x++) {
					filter.applyFilterAtPoint(x, y, MultiThreadedImageFilteringEngine.buffIn,MultiThreadedImageFilteringEngine.buffOut);
				}
			}
			try {
				MultiThreadedImageFilteringEngine.barrier2.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}



}
