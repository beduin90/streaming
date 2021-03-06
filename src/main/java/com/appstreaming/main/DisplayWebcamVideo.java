/*******************************************************************************
 * Copyright (c) 2008, 2010 Xuggle Inc.  All rights reserved.
 *
 * This file is part of Xuggle-Xuggler-Main.
 *
 * Xuggle-Xuggler-Main is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Xuggle-Xuggler-Main is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Xuggle-Xuggler-Main.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package com.appstreaming.main;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IContainerFormat;
import com.xuggle.xuggler.IError;
import com.xuggle.xuggler.IMetaData;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.IVideoResampler;
import com.xuggle.xuggler.Utils;
import com.xuggle.xuggler.demos.VideoImage;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Takes a FFMPEG device driver name (ex: "video4linux2"), and a device name (ex: /dev/video0), and displays video
 * from that device.  For example, a web camera.
 * <p>
 * For example, to play the default camera on these operating systems:
 * <ul>
 * <li>Microsoft Windows:<pre>java -cp %XUGGLE_HOME%\share\java\jars\xuggle-xuggler.jar com.xuggle.xuggler.demos.DisplayWebcamVideo vfwcap 0</pre></li>
 * <li>Linux:<pre>java -cp $XUGGLE_HOME/share/java/jars/xuggle-xuggler.jar com.xuggle.xuggler.demos.DisplayWebcamVideo video4linux2 /dev/video0</pre></li>
 * </ul>
 * </p>
 * @author aclarke
 *
 */
public class DisplayWebcamVideo
{
    public static JSlider slider, slider2;
    public ChangeEvent e;
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    /**
     *
     * contrast , kolor , brightness YUV
     protected static int convertYUVtoRGB(int y, int u, int v) {
     // based on http://en.wikipedia.org/wiki/Yuv#Y.27UV444_to_RGB888_conversion
     int c = y - 16;
     int d = u - 128;
     int e = v - 128;
     int r = clamp8bit((298 * c + 409 * e + 128) >> 8);
     int g = clamp8bit((298 * c - 100 * d - 208 * e + 128) >> 8);
     int b = clamp8bit((298 * c + 516 * d + 128) >> 8);

     return (r << 16) | (g << 8) | b;
     }

     protected BufferedImage convertYUYV422toRGB(int width, int height, ByteBuffer buffer) {
     byte[] input = new byte[width * height * 2];
     buffer.get(input);

     int[] output = new int[width * height];
     int i = 0;
     int j = 0;
     for (int y = 0; y < height; y++) {
     for (int x = 0; x < width; x += 2) {
     int y0 = input[i++] & 0xFF;
     int u = input[i++] & 0xFF;
     int y1 = input[i++] & 0xFF;
     int v = input[i++] & 0xFF;
     output[j++] = convertYUVtoRGB(y0, u, v);
     output[j++] = convertYUVtoRGB(y1, u, v);
     }
     }







     * Takes a FFMPEG webcam driver name, and a device name, opens the
     * webcam, and displays its video in a Swing window.
     * <p>
     * Examples of device formats are:
     * </p>
     * <table border="1">
     * <thead>
     *  <tr>
     *  <td>OS</td>
     *  <td>Driver Name</td>
     *  <td>Sample Device Name</td>
     *  </tr>
     *  </thead>
     *  <tbody>
     *  <tr>
     *  <td>Windows</td>
     *  <td>vfwcap</td>
     *  <td>0</td>
     *  </tr>
     *  <tr>
     *  <td>Linux</td>
     *  <td>video4linux2</td>
     *  <td>/dev/video0</td>
     *  </tr>
     *  </tbody>
     *  </table>
     *
     * <p>
     * Webcam support is very limited; you can't query what devices are
     * available, nor can you query what their capabilities are without
     * actually opening the device.  Sorry, but that's how FFMPEG rolls.
     * </p>
     *
     * @param args Must contain two strings: a FFMPEG driver name and a device name
     *   (which is dependent on the FFMPEG driver).
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args)
    {
        String driverName = "vfwcap";
        String deviceName=  "0";

        // Let's make sure that we can actually convert video pixel formats.
        if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION))
            throw new RuntimeException("you must install the GPL version of Xuggler ");

        // Create a Xuggler container object
        IContainer container = IContainer.make();   // file or data source one or more streams

        // Tell Xuggler about the device format
        IContainerFormat format = IContainerFormat.make();
        if (format.setInputFormat(driverName) < 0)
            throw new IllegalArgumentException("couldn't open webcam device: " + driverName);

        // devices, unlike most files, need to have parameters set in order
        // for Xuggler to know how to configure them, for a webcam, these
        // parameters make sense

        IMetaData params = IMetaData.make();

        params.setValue("framerate", "30/1");
        params.setValue("video_size", "800x600");
        // Open up the container
        int retval = container.open(deviceName, IContainer.Type.READ, format,
                false, true, params, null);
        if (retval < 0)
        {
            // This little trick converts the non friendly integer return value into
            // a slightly more friendly object to get a human-readable error name
            IError error = IError.make(retval);
            throw new IllegalArgumentException("could not open file: " + deviceName + "; Error: " + error.getDescription());
        }

        // query how many streams the call to open found
        int numStreams = container.getNumStreams();

        // and iterate through the streams to find the first video stream
        int videoStreamId = -1;
        IStreamCoder videoCoder = null;
        for(int i = 0; i < numStreams; i++) // stream number
        {
            // Find the stream object
            IStream stream = container.getStream(i);
            // Get the pre-configured decoder that can decode this stream;
            IStreamCoder coder = stream.getStreamCoder();

            if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO)
            {
                videoStreamId = i;
                videoCoder = coder;
                break;
            }
        }
        if (videoStreamId == -1)
            throw new RuntimeException("could not find video stream in container: "+deviceName);

    /*
     * Now we have found the video stream in this file.  Let's open up our decoder so it can
     * do work.
     */
        if (videoCoder.open() < 0)
            throw new RuntimeException("could not open video decoder for container: "+deviceName);

        IVideoResampler resampler = null;
        if (videoCoder.getPixelType() != IPixelFormat.Type.RGB24)
        {
            // if this stream is not in BGR24, we're going to need to
            // convert it.  The VideoResampler does that for us.

            IStream stream=videoCoder.getStream();
            resampler = IVideoResampler.make(videoCoder.getWidth(), videoCoder.getHeight(), IPixelFormat.Type.BGR24,
                    videoCoder.getWidth(), videoCoder.getHeight(), videoCoder.getPixelType());
            if (resampler == null)
                throw new RuntimeException("could not create color space resampler for: " + deviceName);
        }
    /*
     * And once we have that, we draw a window on screen
     */
        new DisplayWebcamVideo().openJavaWindow();

    /*
     * Now, we start walking through the container looking at each packet.
     */
        IPacket packet = IPacket.make();
        while(container.readNextPacket(packet) >= 0)
        {
      /*
       * Now we have a packet, let's see if it belongs to our video stream
       */
            if (packet.getStreamIndex() == videoStreamId)
            {
        /*
         * We allocate a new picture to get the data out of Xuggler
         */
                IVideoPicture picture = IVideoPicture.make(videoCoder.getPixelType(),
                        videoCoder.getWidth(), videoCoder.getHeight());

                int offset = 0;
                while(offset < packet.getSize())
                {
          /*
           * Now, we decode the video, checking for any errors.
           *
           */
                    int bytesDecoded = videoCoder.decodeVideo(picture, packet, offset);
                    if (bytesDecoded < 0)
                        throw new RuntimeException("got error decoding video in: " + deviceName);
                    offset += bytesDecoded;

          /*
           * Some decoders will consume data in a packet, but will not be able to construct
           * a full video picture yet.  Therefore you should always check if you
           * got a complete picture from the decoder
           */
                    if (picture.isComplete())
                    {
                        IVideoPicture newPic = picture;
            /*
             * If the resampler is not null, that means we didn't get the video in BGR24 format and
             * need to convert it into BGR24 format.
             */
                        if (resampler != null)
                        {
                            // we must resample

                            newPic = IVideoPicture.make(resampler.getOutputPixelFormat(), picture.getWidth(), picture.getHeight());
                            if (resampler.resample(newPic, picture) < 0)
                                throw new RuntimeException("could not resample video from: " + deviceName);
                        }
                        if (newPic.getPixelType() != IPixelFormat.Type.BGR24)
                            throw new RuntimeException("could not decode video as BGR 24 bit data in: " + deviceName);

                        // Convert the BGR24 to an Java buffered image
                       System.out.println(slider.getValue());
                        System.out.println(newPic.getHeight());
                        System.out.println("Pixel type: " + videoCoder.getPixelType().toString());
                        BufferedImage javaImage = Utils.videoPictureToImage(newPic);

                        // and display it on the Java Swing window
                        updateJavaWindow(javaImage);
                    }
                }
            }
            else
            {
        /*
         * This packet isn't part of our video stream, so we just silently drop it.
         */
                do {} while(false);
            }

        }
    /*
     * Technically since we're exiting anyway, these will be cleaned up by
     * the garbage collector... but because we're nice people and want
     * to be invited places for Christmas, we're going to show how to clean up.
     */
        if (videoCoder != null)
        {
            videoCoder.close();
            videoCoder = null;
        }
        if (container !=null)
        {
            container.close();
            container = null;
        }
        closeJavaWindow();

    }

    /**
     * The window we'll draw the video on.
     *
     */
    private static VideoImage mScreen = null;

    private static void updateJavaWindow(BufferedImage javaImage)
    {
        mScreen.setImage(javaImage);
    }

    /**
     * Opens a Swing window on screen.
     */

    private  void openJavaWindow()
    {
        mScreen = new VideoImage();
        prepareGUI();
        showSliderDemo();
    }

    private  void prepareGUI(){
        mainFrame = new JFrame("Controls:");
        mainFrame.setSize(400 ,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private void showSliderDemo(){
        headerLabel.setText("Controls your video properties:  ");
        slider = new JSlider(JSlider.HORIZONTAL,0,800,10);
        slider2 = new JSlider(JSlider.HORIZONTAL,0,800,10);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                statusLabel.setText("Value Contrast : " + ((JSlider)e.getSource()).getValue());
            }
        });


        slider2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                statusLabel.setText("Value Brightness: " + ((JSlider)e.getSource()).getValue());
            }
        });
        controlPanel.add(slider);
        controlPanel.add(slider2);
        mainFrame.setVisible(true);
    }



    /**
     * Forces the swing thread to terminate; I'm sure there is a right
     * way to do this in swing, but this works too.
     */
    private static void closeJavaWindow()
    {
        System.exit(0);
    }

}