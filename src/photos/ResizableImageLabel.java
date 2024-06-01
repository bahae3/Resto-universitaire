package photos;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizableImageLabel extends JLabel {
    private BufferedImage originalImage;

    public ResizableImageLabel(String imagePath, int width, int height) {
        try {
            // Check if the image file exists
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                // i used a default image if the specified image does not exist
                imagePath = "src/photos/no_photo.png";
            }

            originalImage = ImageIO.read(new File(imagePath));
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image if available
        if (originalImage != null) {
            g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
