package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.imageio.stream.*;

import static java.lang.Double.NaN;

/**
 * Image writer class combines accumulation of pixel color matrix and
 * finally producing a non-optimized jpeg image from this matrix.
 * The class although is responsible of holding image related parameters
 * of View Plane - pixel matrix size and resolution
 * @author Dan
 */
public class ImageWriter {
    private double _imageWidth, _imageHeight;
    private int _nX, _nY;

    private double _focusWidth, _focusHeight;
    private int _focusX, _focusY;

    private final String PROJECT_PATH = System.getProperty("user.dir");

    private BufferedImage _image;

    private String _imageName;

    // ***************** Constructors ********************** //
    /**
     * Image Writer constructor accepting image name and View Plane parameters,
     * @param imageName the name of jpeg file
     * @param width View Plane width in size units
     * @param height View Plane height in size units
     * @param nX amount of pixels by Width
     * @param nY amount of pixels by height
     */
    public ImageWriter(String imageName, double width, double height, int nX, int nY) {
        _imageName = imageName;
        _imageWidth = width;
        _imageHeight = height;
        _nX = nX;
        _nY = nY;

        _image = new BufferedImage(_nX, _nY, BufferedImage.TYPE_INT_RGB);
    }

    public ImageWriter(double _imageWidth, double _imageHeight, int _nX, int _nY, double _focusWidth, double _focusHeight, int _focusX, int _focusY, String _imageName) {
        this._imageWidth = _imageWidth;
        this._imageHeight = _imageHeight;
        this._nX = _nX;
        this._nY = _nY;
        this._focusWidth = _focusWidth;
        this._focusHeight = _focusHeight;
        this._focusX = _focusX;
        this._focusY = _focusY;
        this._image = new BufferedImage(_nX, _nY, BufferedImage.TYPE_INT_RGB);
        this._imageName = _imageName;
        //ystem.out.println("NaN == NaN = " + (N == NAN));
    }

    // ***************** Getters/Setters ********************** //
    /**
     * View Plane width getter
     * @return the width
     */
    public double getWidth()  { return _imageWidth;  }
    /**
     * View Plane height getter
     * @return the height
     */
    public double getHeight() { return _imageHeight; }

    /**
     * View Plane Y axis resolution
     * @return the amount of vertical pixels
     */
    public int getNy() { return _nY; }
    /**
     * View Plane X axis resolution
     * @return the amount of horizontal pixels
     */
    public int getNx() { return _nX; }

    // ***************** Operations ******************** //

    /**
     * Function writeToImage produces unoptimized jpeg file of
     * the image according to pixel color matrix in the directory
     * of the project
     */
    public void writeToImage(){
        File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
        try {
            javax.imageio.ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(1f);
            jpgWriter.setOutput(new FileImageOutputStream(ouFile));
            jpgWriter.write(null,new IIOImage(_image, null, null), jpgWriteParam);
            //ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return _image;
    }

    public void writeToImage(BufferedImage image){
        File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
        try{
        javax.imageio.ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
        jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpgWriteParam.setCompressionQuality(1f);
        jpgWriter.setOutput(new FileImageOutputStream(ouFile));
        jpgWriter.write(null,new IIOImage(image, null, null), jpgWriteParam);
        //ImageIO.write(_image, "jpg", ouFile);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    /**
     * The function writePixel writes a color of a specific pixel
     * into pixel color matrix
     * @param xIndex X axis index of the pixel
     * @param yIndex Y axis index of the pixel
     * @param color final color of the pixel
     */
    public void writePixel(int xIndex, int yIndex, Color color){

        /*if(isInFocus(xIndex, yIndex))*/
            _image.setRGB(xIndex, yIndex, color.getRGB());
        //else
          //  _image.setRGB(xIndex, yIndex, );
    }

    public String getImageName() {
        return _imageName;
    }
/*
    private boolean isInFocus(int xIndex, int yIndex) {

    }
*/
}
