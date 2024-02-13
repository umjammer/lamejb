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

package net.sf.lamejb.impl.blade;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import net.sf.lamejb.LamejbException;
import net.sf.lamejb.blade.BeStream;
import net.sf.lamejb.blade.BladeEnc;
import net.sf.lamejb.jna.blade.BE_CONFIG;
import net.sf.lamejb.jna.blade.BE_VERSION;
import net.sf.lamejb.jna.blade.BladeMP3Enc;


public class BladeEncImpl implements BladeEnc {

    @Override
    public void encode(String wavFile, String mp3File, BE_CONFIG beConfig) {

        FileInputStream wavStream = null;
        FileOutputStream mp3Stream = null;
        try {
            wavStream = new FileInputStream(wavFile);
            mp3Stream = new FileOutputStream(mp3File);

            encode(wavStream, mp3Stream, beConfig, true);
        } catch (IOException ex) {
            throw new LamejbException(ex);
        } finally {
            try {
                if (wavStream != null) wavStream.close();
                if (mp3Stream != null) mp3Stream.close();
            } catch (IOException ex) {
                throw new LamejbException(ex);
            }
        }

        if (beConfig.format.lhv1.bWriteVBRHeader) {
            BladeMP3Enc.INSTANCE.beWriteVBRHeader(mp3File);
        }
    }

    @Override
    public void encode(InputStream inputStream, OutputStream mp3Stream,
                       BE_CONFIG beConfig, boolean isWav) {

        if (!(inputStream instanceof BufferedInputStream))
            inputStream = new BufferedInputStream(inputStream);

        if (!(mp3Stream instanceof BufferedOutputStream))
            mp3Stream = new BufferedOutputStream(mp3Stream);

        BeStream beStream = initStream(beConfig);

        int dwSamples = beStream.getNSamples();
        int dwMP3Buffer = beStream.getOutputBufferSize();

        // Allocate buffers   
        //short[] pWAVBuffer = new short[dwSamples];  
        byte[] pWAVBuffer = new byte[dwSamples * 2]; // each sample is a short      
        byte[] pMP3Buffer = new byte[dwMP3Buffer];

        try {
            if (isWav) inputStream.skip(44); // Skipping the WAV header        

            int dwWrite;
            // Convert All PCM samples
            int read = 0;
            while ((read = inputStream.read(pWAVBuffer, 0, pWAVBuffer.length)) > 0) {
                // read is "bytes", each sample is 2 bytes
                int readSamples = read / 2;
                // Encode samples
                dwWrite = beStream.encodeChunk(readSamples, pWAVBuffer, pMP3Buffer);
                // write dwWrite bytes that are returned in the pMP3Buffer to disk
                mp3Stream.write(pMP3Buffer, 0, dwWrite);
            }

            // Definite the beStream
            dwWrite = beStream.deinitStream(pMP3Buffer);

            // Are there any bytes returned from the DeInit call?
            // If so, write them to disk              
            if (dwWrite != 0)
                mp3Stream.write(pMP3Buffer, 0, dwWrite);

            mp3Stream.flush();  // IMPORTANT because BufferedInputStream is lost and not closed
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }

        beStream.close();
    }

    @Override
    public BeStream initStream(BE_CONFIG beConfig) {
        IntByReference pDwSamples = new IntByReference();
        IntByReference pDwMP3Buffer = new IntByReference();
        NativeLongByReference pHbeStream = new NativeLongByReference();

        NativeLong err =
                BladeMP3Enc.INSTANCE.beInitStream(beConfig, pDwSamples, pDwMP3Buffer, pHbeStream);
        BladeUtil.checkError(err);

        int nSamples = pDwSamples.getValue();
        int outputBufferSize = pDwMP3Buffer.getValue();
        NativeLong hbeStream = pHbeStream.getValue();
        return new BeStreamImpl(hbeStream, nSamples, outputBufferSize);
    }

    @Override
    public BE_VERSION getVersion() {
        BE_VERSION version = new BE_VERSION();
        BladeMP3Enc.INSTANCE.beVersion(version);
        return version;
    }
}
