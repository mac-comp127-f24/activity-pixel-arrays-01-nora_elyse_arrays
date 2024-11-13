import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Image.PixelFormat;


public class ImageTransform {
    private static float[] pixels;
    private static byte[] pixelsByte;
    public static Image lighten(Image srcImage) {
        pixels = srcImage.toFloatArray(PixelFormat.RGB);
        for(int i = 0; i < pixels.length; i++){
            pixels[i] *= 1.5;
        }
        Image newImage = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, PixelFormat.RGB);
        return newImage;
    }


    public static Image greenShift(Image srcImage) {
        pixels = srcImage.toFloatArray(PixelFormat.RGB);
        for(int i = 1; i < pixels.length; i+=3){
            pixels[i] += 0.25;
        }
        Image newImage = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, PixelFormat.RGB);
        return newImage;

    }

    public static Image invert(Image srcImage) {
        pixelsByte = srcImage.toByteArray(PixelFormat.RGB);
        for(int i =0; i < pixelsByte.length; i++){
            pixelsByte[i] =  (byte)( 25 - pixelsByte[i]);
        }
        Image newImage = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixelsByte, PixelFormat.RGB);
        return newImage;
    }

    public static Image greyScale(Image srcImage) {
        pixels = srcImage.toFloatArray(PixelFormat.RGB);
        for(int i =0; i < pixels.length; i+=3){
            pixels[i+1] = pixels[i];
            pixels[i+2] = pixels[i];
        }
        Image newImage = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, PixelFormat.RGB);
        return newImage;
    }

    public static void main(String[] args) {
        Image srcImage = new Image("dog2.jpeg");
    
        Scanner scan = new Scanner(System.in);
        System.out.println("How would you like to transform your image?");
        System.out.println("1. Lighten");
        System.out.println("2. Green Shift");
        System.out.println("3. Invert");
        System.out.println("4. Greyscale");

        System.out.print("> ");
        int choice = scan.nextInt();

        Image transformed = switch(choice) {
            default -> srcImage; // If no matching choice, display original image
            case 1 -> lighten(srcImage);
            case 2 -> greenShift(srcImage);
            case 3 -> invert(srcImage);
            case 4 -> greyScale(srcImage);
        };

        CanvasWindow canvas = new CanvasWindow("img", 500, 500);
        canvas.add(transformed);
        transformed.setCenter(canvas.getCenter());

        scan.close();
    }
    
}
