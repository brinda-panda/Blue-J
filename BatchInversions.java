import edu.duke.*;
import java.io.*;
/**
 * Write a description of class BatchInversions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BatchInversions
{
    public ImageResource makeInversion(ImageResource inImage)
    {
        ImageResource outImage=new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel:outImage.pixels())
        {
            Pixel inPixel=inImage.getPixel(pixel.getX(),pixel.getY());
            int red_pix=255-inPixel.getRed();
            int blue_pix=255-inPixel.getBlue();
            int green_pix=255-inPixel.getGreen();
            pixel.setRed(red_pix);
            pixel.setGreen(green_pix);
            pixel.setBlue(blue_pix);
        }
        return outImage;
    }
    public void testInversion()
    {
        ImageResource img=new ImageResource();
        ImageResource invert=makeInversion(img);
        invert.draw();
    }
    public void selectAndConvert()
    {
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            ImageResource image=new ImageResource(f);
            ImageResource inverse =makeInversion(image);
            String fname=image.getFileName();
            String newName="gray-"+fname;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }
}
