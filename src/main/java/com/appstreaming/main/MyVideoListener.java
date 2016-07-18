package com.appstreaming.main;


    import com.xuggle.mediatool.MediaToolAdapter;
    import com.xuggle.mediatool.event.IAddStreamEvent;
    import com.xuggle.xuggler.ICodec;
    import com.xuggle.xuggler.IStreamCoder;
    import com.xuggle.xuggler.Configuration;
    import com.xuggle.xuggler.ICodec;
    import com.xuggle.xuggler.IContainer;
    import com.xuggle.xuggler.IContainerFormat;
    import com.xuggle.xuggler.IPacket;
    import com.xuggle.xuggler.IPixelFormat;
    import com.xuggle.xuggler.IRational;
    import com.xuggle.xuggler.IStream;
    import com.xuggle.xuggler.IStreamCoder;
    import com.xuggle.xuggler.IVideoPicture;
    import com.xuggle.xuggler.video.ConverterFactory;
    import com.xuggle.xuggler.video.IConverter;
    import java.awt.AWTException;
    import java.awt.Rectangle;
    import java.awt.Robot;
    import java.awt.image.BufferedImage;
    import java.io.IOException;
    import java.io.InputStream;
    import java.util.Properties;

    public class MyVideoListener extends MediaToolAdapter {
        private Integer width;
        private Integer height;

        public MyVideoListener(Integer aWidth, Integer aHeight) {
            this.width = aWidth;
            this.height = aHeight;
        }

        @Override
        public void onAddStream(IAddStreamEvent event) {
            int streamIndex = event.getStreamIndex();
            IStreamCoder streamCoder = event.getSource().getContainer().getStream(streamIndex).getStreamCoder();
            if (streamCoder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
            } else if (streamCoder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
                streamCoder.setWidth(width);
                streamCoder.setHeight(height);
            }
            super.onAddStream(event);
        }

    }



