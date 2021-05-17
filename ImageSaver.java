import java.io.*;
import edu.duke.*;
/**
 * Write a description of class ImageSaver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ImageSaver
{
    public void doSave()
    {
        DirectoryResource dr=new DirectoryResource();
        for(File f:dr.selectedFiles())
        {
            ImageResource image=new ImageResource(f);
            String fname=image.getFileName();
            String newName="gray-"+fname;
            image.setFileName(newName);
            image.draw();
            image.save();
        }
    }
}
