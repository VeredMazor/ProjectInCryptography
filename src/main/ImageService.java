package main;

import java.util.ArrayList; // import the ArrayList class


public class ImageService {
	private static ArrayList<String> images = new ArrayList<String>();
	public ImageService() {
		images.add("balloons");
		images.add("facebook");
		images.add("icecream");
		images.add("instagram");
		images.add("spiderman");
		images.add("amongus");
		
	}
	
	public static ArrayList<String> getInstance() {
		return images;
	}
}
