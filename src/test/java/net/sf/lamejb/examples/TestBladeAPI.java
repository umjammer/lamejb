/*
 * Lamejb Java based API for LAME MP3 encoder
 *
 * Copyright (c) 2008 Luigi Bitonti
 *
 * Based on LAMEOnJ by Jose Maria Arranz
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 */

package net.sf.lamejb.examples;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import net.sf.lamejb.blade.BladeEnc;
import net.sf.lamejb.impl.blade.BladeEncImpl;
import net.sf.lamejb.jna.blade.BE_CONFIG;
import net.sf.lamejb.jna.blade.BE_CONFIG_Format_LHV1;
import net.sf.lamejb.jna.blade.BE_VERSION;
import net.sf.lamejb.jna.blade.BladeMP3Enc;


public class TestBladeAPI {

    public void testPlainAPI(String wavFile, String mp3File) throws Exception {
        // Based in the LAME example : Dll/Example.cpp

        new File(mp3File).delete();

        BE_VERSION version = new BE_VERSION();
        BladeMP3Enc.INSTANCE.beVersion(version);
        System.out.println("LAME Version: " + version.byMajorVersion + "." + version.byMinorVersion);
        System.out.println("Home page: " + version.getZHomepageAsString());

        BE_CONFIG beConfig = new BE_CONFIG();
        beConfig.format.setType(BE_CONFIG_Format_LHV1.class);

        beConfig.dwConfig = BladeMP3Enc.BE_CONFIG_LAME;
        beConfig.format.lhv1.dwStructVersion = 1;
        beConfig.format.lhv1.dwStructSize = beConfig.size();
        beConfig.format.lhv1.dwSampleRate = 44100;
        beConfig.format.lhv1.dwReSampleRate = 0; // NO RESAMPLE       
        beConfig.format.lhv1.nMode = BladeMP3Enc.BE_MP3_MODE_JSTEREO;
        beConfig.format.lhv1.dwBitrate = 128;
        //beConfig.getFormat().getLHV1().setNPreset(LAME_QUALITY_PRESET.LQP_R3MIX);      
        beConfig.format.lhv1.dwMpegVersion = BladeMP3Enc.MPEG1;
        beConfig.format.lhv1.dwPsyModel = 0; //  USE DEFAULT PSYCHOACOUSTIC MODEL     
        beConfig.format.lhv1.dwEmphasis = 0; // NO EMPHASIS TURNED ON
        beConfig.format.lhv1.bOriginal = true;
        beConfig.format.lhv1.bWriteVBRHeader = true;  // Write INFO tag      
        beConfig.format.lhv1.bNoRes = true;

        IntByReference pDwSamples = new IntByReference();
        IntByReference pDwMP3Buffer = new IntByReference();
        NativeLongByReference pHbeStream = new NativeLongByReference();

        NativeLong err =
                BladeMP3Enc.INSTANCE.beInitStream(beConfig, pDwSamples, pDwMP3Buffer, pHbeStream);
        checkError(err);

        int dwSamples = pDwSamples.getValue();
        int dwMP3Buffer = pDwMP3Buffer.getValue();
        NativeLong hbeStream = pHbeStream.getValue();

        // Allocate buffers
        //short[] pWAVBuffer = new short[dwSamples];  
        byte[] pWAVBuffer = new byte[dwSamples * 2];  // 2 bytes per short     
        byte[] pMP3Buffer = new byte[dwMP3Buffer];


        // WAV file supposed 44100 Hz, Stereo, 16 bits
        BufferedInputStream wavStream = new BufferedInputStream(new FileInputStream(wavFile));
        BufferedOutputStream mp3Stream = new BufferedOutputStream(new FileOutputStream(mp3File));

        wavStream.skip(44); // Skipping the WAV header        

        IntByReference pDwWrite = new IntByReference(0);
        int dwWrite;
        // Convert All PCM samples
        int read = 0;
        while ((read = wavStream.read(pWAVBuffer, 0, pWAVBuffer.length)) > 0) {
            // read is "bytes", each sample is 2 bytes
            int readSamples = read / 2;
            // Encode samples
            err = BladeMP3Enc.INSTANCE.beEncodeChunk(hbeStream, readSamples, pWAVBuffer, pMP3Buffer, pDwWrite);
            checkError(err);

            // write dwWrite bytes that are returned in tehe pMP3Buffer to disk
            dwWrite = pDwWrite.getValue();
            mp3Stream.write(pMP3Buffer, 0, dwWrite);
        }

        // Deinit the stream
        err = BladeMP3Enc.INSTANCE.beDeinitStream(hbeStream, pMP3Buffer, pDwWrite);
        checkError(err);

        // Are there any bytes returned from the DeInit call?
        // If so, write them to disk
        dwWrite = pDwWrite.getValue();
        if (dwWrite != 0)
            mp3Stream.write(pMP3Buffer, 0, dwWrite);

        // close the MP3 Stream
        BladeMP3Enc.INSTANCE.beCloseStream(hbeStream);

        wavStream.close();
        mp3Stream.close();

        if (beConfig.format.lhv1.bWriteVBRHeader)
            BladeMP3Enc.INSTANCE.beWriteVBRHeader(mp3File);

        // Test.checkFiles(mp3File,mp3RefFile,false);        
    }


    public void testOOPAPI(String wavFile, String mp3File) throws Exception {
        // Based in the LAME example : Dll/Example.cpp           

        new File(mp3File).delete();

        BladeEnc blade = new BladeEncImpl();

        BE_VERSION version = blade.getVersion();
        System.out.println("LAME Version: " + version.byMajorVersion + "." + version.byMinorVersion);
        System.out.println("Home page: " + version.getZHomepageAsString());

        BE_CONFIG beConfig = new BE_CONFIG();
        beConfig.format.setType(BE_CONFIG_Format_LHV1.class);

        beConfig.dwConfig = BladeMP3Enc.BE_CONFIG_LAME;
        beConfig.format.lhv1.dwStructVersion = 1;
        beConfig.format.lhv1.dwStructSize = beConfig.size();
        beConfig.format.lhv1.dwSampleRate = 44100;
        beConfig.format.lhv1.dwReSampleRate = 0; // NO RESAMPLE       
        beConfig.format.lhv1.nMode = BladeMP3Enc.BE_MP3_MODE_JSTEREO;
        beConfig.format.lhv1.dwBitrate = 128;
        //beConfig.getFormat().getLHV1().setNPreset(LAME_QUALITY_PRESET.LQP_R3MIX);      
        beConfig.format.lhv1.dwMpegVersion = BladeMP3Enc.MPEG1;
        beConfig.format.lhv1.dwPsyModel = 0; //  USE DEFAULT PSYCHOACOUSTIC MODEL     
        beConfig.format.lhv1.dwEmphasis = 0; // NO EMPHASIS TURNED ON
        beConfig.format.lhv1.bOriginal = true;
        beConfig.format.lhv1.bWriteVBRHeader = true;  // Write INFO tag      
        beConfig.format.lhv1.bNoRes = true;

        blade.encode(wavFile, mp3File, beConfig);

        // Test.checkFiles(mp3File,mp3RefFile,false);        
    }


    public static void checkError(NativeLong err) {
        if (err.intValue() != BladeMP3Enc.BE_ERR_SUCCESSFUL)
            throw new RuntimeException("ERROR " + err);
    }

}
