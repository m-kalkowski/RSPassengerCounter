import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;

import org.bytedeco.javacpp.RealSense;
import org.bytedeco.javacpp.RealSense.context;
import org.bytedeco.javacpp.RealSense.device;

import org.opencv.core.Core;                                                                                                                                                                                                              
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class TestConnection {

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        // OpenCV Testing
        System.out.println("Welcome to OpenCV " + Core.VERSION);

        VideoCapture camera = new VideoCapture(0);

        try{
            Thread.sleep(1000); //Needed for camera initialization
        }catch(InterruptedException e) {
            System.out.println("Main interrupted");
        }

        Mat frame = new Mat();
        camera.read(frame); 

        if(!camera.isOpened()){
            System.out.println("Error");
        }

        camera.read(frame);
        System.out.println("Frame Obtained");

        Highgui.imwrite("camera.jpg",frame);

        // librealsense Testing
        context context = new context();
        System.out.println("Devices found: " + context.get_device_count());

        device device = context.get_device(0);
        System.out.println("Using device 0, an " + device.get_name().getString());
        System.out.println("Using firmware version: " +  device.get_firmware_version().getString()); 
        System.out.println("Usb port id: " + device.get_usb_port_id().getString());

        device.enable_stream(RealSense.depth, 640, 480, RealSense.z16, 60);
        device.start();

        Pointer rawDepthImageData = new Pointer((Pointer) null);
        rawDepthImageData = device.get_frame_data(RealSense.depth);
        
        Mat rawDepthImage = new Mat(640, 480, CvType.CV_16U);
        cvSetData(rawDepthImage, rawDepthImageData, 640 * 1 * CvType.CV_16U / 8);

        Highgui.imwrite("depth.jpg", rawDepthImageData );
    }
}
