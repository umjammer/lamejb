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

package net.sf.lamejb.impl.std;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.lamejb.LamejbException;
import net.sf.lamejb.jna.std.Lame;
import net.sf.lamejb.std.StreamEncoder;


/**
 * Implementation of the StreamEncoder interface
 */
public abstract class StreamEncoderImpl extends EncoderImpl implements StreamEncoder {

    protected BufferedInputStream sourceStream;
    protected byte[] pPCMBuffer;


    public StreamEncoderImpl(String sourceFile) {
        this(createFileInputStream(sourceFile));
    }

    public StreamEncoderImpl(InputStream input) {
        super();

        if (!(input instanceof BufferedInputStream))
            input = new BufferedInputStream(input);

        this.sourceStream = (BufferedInputStream) input;

        parseHeader();
    }

    public static FileInputStream createFileInputStream(String sourceFile) {
        try {
            return new FileInputStream(sourceFile);
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }
    }

    public abstract void parseHeader();

    public InputStream getSourceInputStream() {
        return sourceStream;
    }

    public void close() {
        if (closed) return;

        super.close();

        closeSourceStream();
    }

    public void closeSourceStream() {
        if (sourceStream == null) return;

        closeStream(sourceStream);
        this.sourceStream = null;
    }

    public void encode(String mp3File) {
        // http://stuff.mit.edu/afs/sipb/user/gamache/src/lame-3.91/Dll/BladeMP3EncDLL.c        
        // http://www.koders.com/cpp/fid8B71B2E32247F195DA00237E702C5031468A55C1.aspx?s=lame_encode_buffer        

        if (closed) throw new LamejbException("Encoding Task is closed, create a new one");

        FileOutputStream mp3Stream;

        try {
            mp3Stream = new FileOutputStream(mp3File);
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }

        try {
            encode(mp3Stream);
        } catch (Exception ex) {
            throw new LamejbException(ex);
        } finally {
            closeStream(mp3Stream);
        }

        writeVbrTag(mp3File);

        close();
    }

    public void encode(OutputStream mp3Stream) {
        // http://stuff.mit.edu/afs/sipb/user/gamache/src/lame-3.91/Dll/BladeMP3EncDLL.c        
        // http://www.koders.com/cpp/fid8B71B2E32247F195DA00237E702C5031468A55C1.aspx?s=lame_encode_buffer        

        initEncodingTask();

        try {
            if (!(mp3Stream instanceof BufferedOutputStream))
                mp3Stream = new BufferedOutputStream(mp3Stream);

            int numChannels = Lame.INSTANCE.lame_get_num_channels(flags);

            int mSamplesPerFrame = calcSamplesPerFrame(numChannels);
            int mOutBufferSize = calcOuputBufferSize(numChannels);

            // Allocate buffers
            //short[] pPCMBuffer = new short[mSamplesPerFrame];  
            byte[] pPCMBuffer = new byte[mSamplesPerFrame * 2]; // 2 bytes per short
            byte[] pMP3Buffer = new byte[mOutBufferSize];

            // Convert All PCM samples
            int read;
            while ((read = sourceStream.read(pPCMBuffer, 0, pPCMBuffer.length)) > 0) {
                // Encode samples                
                int nOutputBytes = encodeBuffer(flags, pPCMBuffer, read, pMP3Buffer);

                // write nOutputBytes bytes that are returned in the pMP3Buffer to disk
                mp3Stream.write(pMP3Buffer, 0, nOutputBytes);
            }

            int nOutputBytes = encodeFlushNoGap(flags, pMP3Buffer);
            // Are there any bytes pending? If so, write them to disk               
            if (nOutputBytes != 0)
                mp3Stream.write(pMP3Buffer, 0, nOutputBytes);

            mp3Stream.flush();
        } catch (IOException ex) {
            closeStream(mp3Stream);
            close();
            throw new LamejbException(ex);
        } finally {
            closeSourceStream();
        }
    }

    public int initEncoding() {
        initEncodingTask();

        int numChannels = Lame.INSTANCE.lame_get_num_channels(flags);

        int mSamplesPerFrame = calcSamplesPerFrame(numChannels);
        int mOutBufferSize = calcOuputBufferSize(numChannels);

        this.pPCMBuffer = new byte[mSamplesPerFrame * 2]; // 2 bytes per short
        // To allocate: byte[] pMP3Buffer = new byte[mOutBufferSize]; 

        return mOutBufferSize;
    }

    public int encodeBuffer(byte[] pMP3Buffer) {
        if (sourceStream == null) return 0;

        if (!encodingTaskInProcess) throw new LamejbException("There is no encoding task in process");

        try {
            int read = sourceStream.read(pPCMBuffer, 0, pPCMBuffer.length);
            if (read > 0)
                return encodeBuffer(flags, pPCMBuffer, read, pMP3Buffer);
            else {
                closeSourceStream();  // reached the end
                return encodeFlush(pMP3Buffer);
            }
        } catch (IOException ex) {
            close();
            throw new LamejbException(ex);
        }
    }

    public static void closeStream(InputStream stream) {
        try {
            if (stream != null) stream.close();
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }
    }

    public static void closeStream(OutputStream stream) {
        try {
            if (stream != null) stream.close();
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }
    }

}
