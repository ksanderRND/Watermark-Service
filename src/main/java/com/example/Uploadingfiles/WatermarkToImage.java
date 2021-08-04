package com.example.Uploadingfiles;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class WatermarkToImage {
    public static void addWatermark(String localImagePath) {

        File origFile = new File(localImagePath);
        ImageIcon icon = new ImageIcon(origFile.getPath());

        // create BufferedImage object of same width and height as of original image
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),
                icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

        // create graphics object and add original image to it
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(icon.getImage(), 0, 0, null);

        // set font for the watermark text
        Font font = new Font("Arial", Font.BOLD, 30);
        graphics.setFont(font);
        graphics.setColor(new Color(0,0,0,70));

        //adding a filename as a watermark text
        String name = origFile.getName();
        String str = name.substring(0, name.lastIndexOf("."));
        String watermark = "\u00a9 "+ str;
        //System.out.println("Res Watermark: "+watermark);
        graphics.drawString(watermark, 0, icon.getIconHeight() );

        graphics.dispose();

        File newFile = new File(origFile.getParent() +"/"+str+"WatermarkedImage.jpg");
        try {
            ImageIO.write(bufferedImage, "jpg", newFile);

            InputStream inputStream = new FileInputStream(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newFile.getPath() + " created successfully!");
    }
}
