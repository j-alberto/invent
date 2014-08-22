package org.jar.invent.util;

import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {

	/**
	 * Saves an image in the host's file system
	 * @param image Image to save
	 * @param path Where the image will be saved
	 * @return
	 * @throws IOException 
	 */
	public boolean saveImage(BufferedImage image, Path path) throws IOException{
		
		if(Files.notExists(path.getParent())){
			Files.createDirectories(path.getParent());
		}
		if(Files.notExists(path)){
			Files.createFile(path);
		}
		
		ImageIO.write(image, "png", path.toFile());
		
		return true;
	}
	/**
	 * Saves an Multipart file image in the host's file system
	 * @param image Image to save
	 * @param path Where the image will be saved
	 * @return
	 * @throws IOException 
	 */
	public boolean saveImage(MultipartFile image, Path path) throws IOException{
		
		if(Files.notExists(path.getParent())){
			Files.createDirectories(path.getParent());
		}
		if(Files.notExists(path)){
			Files.createFile(path);
		}
		
		File f = path.toFile().getAbsoluteFile();
		image.transferTo(f);
        
        return true;
	}	
	/**
	 * Creates a reduced version of an image. If maxHeight is greater than image's size, original image is returned.
	 * @param originalImage image to reduce
	 * @param maxHeight The reference target height of the snapshot
	 * @return reduced image
	 */
    public BufferedImage createSnapshot(BufferedImage originalImage, int maxHeight){
    	
    	if(maxHeight >= originalImage.getHeight()) return originalImage;
    	
    	double scale = maxHeight/Double.valueOf(originalImage.getHeight());

		BufferedImage scaledImage = null;
	    if(originalImage != null) {
	    	scaledImage = new BufferedImage((int)(originalImage.getWidth() * scale), maxHeight, ColorSpace.TYPE_RGB);
	        AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
	        scaledImage.createGraphics().drawRenderedImage(originalImage, transform);
	    }
	    return scaledImage;

    }

    /**
     * Creates a reduced version of an image. If maxHeight is greater than image's size, original image is returned.
     * @param imageFile
     * @param maxHeight
     * @return reduced image
     * @throws IOException if specified File cannot be read
     */
    public BufferedImage createSnapshot(File imageFile, int maxHeight) throws IOException{
    	BufferedImage img = ImageIO.read(imageFile);
	    return createSnapshot(img, maxHeight);

    }


}
