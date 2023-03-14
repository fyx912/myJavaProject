package com.ding;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * OpenCV的Java库要配置到JDK的库路径中
 * @author ding
 * @create 11 22:57
 * @description
 */
public class ImageService {
    static {
        //在使用OpenCV前必须加载Core.NATIVE_LIBRARY_NAME类,否则会报错
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        compareHist_2();
        compareHist_1();
    }

    /**
     * OpenCV-4.0.0 直方图比较
     *
     * @return: void
     * @date: 2020年1月14日20:15:39
     */
    public static void compareHist_1() {
        Mat src = Imgcodecs.imread("C:\\Users\\tintin\\Downloads\\1676127488030.png");

        Mat hsv = new Mat();

        //图片转HSV
        Imgproc.cvtColor(src, hsv,Imgproc.COLOR_BGR2HSV);

        Mat hist = new Mat();
        //直方图计算
        Imgproc.calcHist(Stream.of(hsv).collect(Collectors.toList()),new MatOfInt(0),new Mat(),hist,new MatOfInt(255) ,new MatOfFloat(0,256));
        //图片归一化
        Core.normalize(hist, hist, 1, hist.rows() , Core.NORM_MINMAX, -1, new Mat() );
        //直方图比较
        double a = Imgproc.compareHist(hist,hist,Imgproc.CV_COMP_CORREL);
        System.out.println("越接近1越相识度越高\n比较结果："+a);
    }

    /**
     * OpenCV-4.0.0 直方图比较
     *
     * @return: void
     * @date: 2020年1月14日20:15:39
     */
    public static void compareHist_2() {
        Mat src_1 = Imgcodecs.imread("C:\\Users\\tintin\\Downloads\\1676127488030.png");// 图片 1
        Mat src_2 = Imgcodecs.imread("C:\\Users\\tintin\\Downloads\\1676127477581.png");// 图片 2

        Mat hvs_1 = new Mat();
        Mat hvs_2 = new Mat();
        //图片转HSV
        Imgproc.cvtColor(src_1, hvs_1,Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(src_2, hvs_2,Imgproc.COLOR_BGR2HSV);

        Mat hist_1 = new Mat();
        Mat hist_2 = new Mat();

        //直方图计算
        Imgproc.calcHist(Stream.of(hvs_1).collect(Collectors.toList()),new MatOfInt(0),new Mat(),hist_1,new MatOfInt(255) ,new MatOfFloat(0,256));
        Imgproc.calcHist(Stream.of(hvs_2).collect(Collectors.toList()),new MatOfInt(0),new Mat(),hist_2,new MatOfInt(255) ,new MatOfFloat(0,256));

        //图片归一化
        Core.normalize(hist_1, hist_1, 1, hist_1.rows() , Core.NORM_MINMAX, -1, new Mat() );
        Core.normalize(hist_2, hist_2, 1, hist_2.rows() , Core.NORM_MINMAX, -1, new Mat() );

        //直方图比较
        double a = Imgproc.compareHist(hist_1,hist_1,Imgproc.CV_COMP_CORREL);
        double b = Imgproc.compareHist(hist_1,hist_2, Imgproc.CV_COMP_CORREL);
        System.out.println("越接近1越相识度越高");
        System.out.println("同一张图片\t比较结果(相识度)："+a);
        System.out.println("不同图片\t比较结果(相识度)："+b);
    }
}
