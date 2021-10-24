package Manager;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Helper {

	public static void compressionImage(File originalImage, File compressedImage, float compressionQuality)
			throws IOException {

		RenderedImage image = ImageIO.read(originalImage);
		ImageWriter jpegImageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
		ImageWriteParam jpegWriteParam = jpegImageWriter.getDefaultWriteParam();
		jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		jpegWriteParam.setCompressionQuality(compressionQuality);

		try (ImageOutputStream output = ImageIO.createImageOutputStream(compressedImage)) {
			jpegImageWriter.setOutput(output);
			IIOImage outputImage = new IIOImage(image, null, null);
			jpegImageWriter.write(null, outputImage, jpegWriteParam);
			jpegImageWriter.dispose();
		}

	}

	public static void jpgToPng() throws IOException {

		File imageFile = new File("C:\\Users\\maksa\\OneDrive\\Masaüstü\\deneme.jpg");
		File convertedImageFile = new File("C:\\Users\\maksa\\OneDrive\\Masaüstü\\deneme2.png");

		BufferedImage image = ImageIO.read(imageFile.toURL());
		ImageIO.write(image, "png", convertedImageFile);

	}

	public JFileChooser showFileChooser() {

		JFileChooser file = new JFileChooser();
		file.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png", "gif", "png");
		file.addChoosableFileFilter(filter);

		return file;
	}

}
