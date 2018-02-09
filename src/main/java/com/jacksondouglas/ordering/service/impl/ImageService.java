package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.service.IImageService;
import com.jacksondouglas.ordering.service.exception.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Service
public class ImageService implements IImageService {

    @Override
    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());

        if (!"png".equals(ext) && !"jpg".equals(ext)) {
            throw  new FileException("Only images PNG and JPG are allowed");
        }

        try {
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream());

            if ("png".equals(ext)) {
                img = pngToJpg(img);
            }

            return img;
        } catch (IOException e) {
            throw new FileException("Error when trying to read the file");
        }
    }

    private BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_BGR);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage img, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Error when trying to read the file");
        }
    }

    @Override
    public BufferedImage cropSquare(BufferedImage sourceImg) {
        int min = (sourceImg.getHeight() < sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
        return Scalr.crop(sourceImg,
                (sourceImg.getWidth() / 2) - (min / 2),
                (sourceImg.getHeight() / 2) - (min / 2),
                min, min);
    }

    @Override
    public BufferedImage resize(BufferedImage sourceImg, int size) {
        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
    }
}
