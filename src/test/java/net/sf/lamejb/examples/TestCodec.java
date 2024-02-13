package net.sf.lamejb.examples;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.jna.Platform;
import net.sf.lamejb.BladeCodecFactory;
import net.sf.lamejb.LameCodecFactory;
import net.sf.lamejb.LamejbCodec;
import net.sf.lamejb.LamejbCodecFactory;
import net.sf.lamejb.LamejbConfig;
import net.sf.lamejb.LamejbConfig.MpegMode;


public class TestCodec {

    public void encodeFile(String wavFile, String mp3File) throws Exception {
        LamejbCodecFactory codecFactory;
        if (Platform.isWindows())
            codecFactory = new BladeCodecFactory();
        else
            codecFactory = new LameCodecFactory();

        LamejbCodec codec = codecFactory.createCodec();

        LamejbConfig config = new LamejbConfig(44100, 32, MpegMode.MONO, true);

        codec.encodeFile(wavFile, mp3File, config);
    }


    public void encodeStream(String wavFile, String mp3File) throws Exception {
        LamejbCodecFactory codecFactory;
        if (Platform.isWindows())
            codecFactory = new BladeCodecFactory();
        else
            codecFactory = new LameCodecFactory();

        LamejbCodec codec = codecFactory.createCodec();

        LamejbConfig config = new LamejbConfig(44100, 128, MpegMode.JOINT_STEREO, true);

        InputStream is = new BufferedInputStream(new FileInputStream(wavFile));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(mp3File));

        codec.encodeStream(is, os, config);
    }


}
