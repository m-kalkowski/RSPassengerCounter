javac -cp jar_files/javacpp.jar:jar_files/librealsense-platform.jar:jar_files/javacv.jar:jar_files/opencv.jar RealSenseFrameGrabber.java 

javac -cp jar_files/javacpp.jar:jar_files/librealsense-platform.jar:jar_files/javacv.jar:jar_files/opencv.jar Main.java
java -jar jar_files/javacpp.jar Main
java -cp jar_files/javacpp.jar:jar_files/librealsense-platform.jar:jar_files/javacv.jar:jar_files/opencv.jar:. Main
