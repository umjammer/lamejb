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

import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import net.sf.lamejb.LamejbException;
import net.sf.lamejb.jna.std.FileUtil;
import net.sf.lamejb.jna.std.Lame;
import net.sf.lamejb.jna.std.LameGlobalFlags;
import net.sf.lamejb.std.Encoder;
import net.sf.lamejb.std.LameConfig;


/**
 * Implementation of the encoder interface
 */
public abstract class EncoderImpl implements Encoder {

    //protected LameEncoderFactoryImpl std;
    protected LameGlobalFlags flags;
    protected boolean closed = false;
    protected boolean encodingTaskInProcess = false;
    protected LameConfigImpl config = new LameConfigImpl(this);


    public EncoderImpl() {
        //this.std = std;      

        this.flags = Lame.INSTANCE.lame_init();
    }

    public LameConfig getLameConfig() {
        return config;
    }

    public LameGlobalFlags getLameFlags() {
        if (closed)
            throw new LamejbException("Encoder is closed"); // flags is not valid

        return flags;
    }

    public boolean isEncodingTaskInProcess() {
        return encodingTaskInProcess;
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        if (closed) return;

        int rc = Lame.INSTANCE.lame_close(flags);
        LameUtil.checkError(rc);

        closed = true;
    }


    public void initEncodingTask() {
        if (closed) throw new LamejbException("This encoder is closed, create a new one");

        if (encodingTaskInProcess) throw new LamejbException("There is an encoding task in process");

        int rc = Lame.INSTANCE.lame_init_params(flags);
        LameUtil.checkError(rc);

        this.encodingTaskInProcess = true;
    }

    public static int calcSamplesPerFrame(int numChannels) {
        // For MPEG-I, 1152 samples per frame per channel (Lame.lame_get_framesize(flags) returns 1152)
        //int mSamplesPerFrameAndChannel = 1152;      
        int mSamplesPerFrame = 1152 * numChannels;

        return mSamplesPerFrame;
    }

    public static int calcOuputBufferSize(int numChannels) {
        int mSamplesPerFrame = calcSamplesPerFrame(numChannels);

        // Worst case MPEG-I (see lame.h/lame_encode_buffer())
        int mOutBufferSize = mSamplesPerFrame * (320 / 8) / 8 + 4 * 1152 * (320 / 8) / 8 + 512;
        // int mOutBufferSize = (int)(1.25 * ( mSamplesPerFrame / numChannels ) + 7200);
        return mOutBufferSize;
    }

    public static int encodeBuffer(LameGlobalFlags flags, byte[] pPCMBuffer, int size, byte[] pMP3Buffer) {
        // Encode samples

        int numChannels = Lame.INSTANCE.lame_get_num_channels(flags);

        int readSamplesAllChannels = size / 2; // size is "bytes", each sample is 2 bytes             
        int readSamplesPerChannel = readSamplesAllChannels / numChannels;
        int nOutputBytes;
        if (numChannels == 2)
            nOutputBytes = Lame.INSTANCE.lame_encode_buffer_interleaved(
                    flags, pPCMBuffer, readSamplesPerChannel, pMP3Buffer, 0);
        else
            nOutputBytes = Lame.INSTANCE.lame_encode_buffer(
                    flags, pPCMBuffer, pPCMBuffer, readSamplesPerChannel, pMP3Buffer, 0);

        return nOutputBytes;
    }

    public int encodeFlush(byte[] pMP3Buffer) {
        if (!encodingTaskInProcess) throw new LamejbException("There is no encoding task in process");

        int nOutputBytes = encodeFlushNoGap(flags, pMP3Buffer);

        this.encodingTaskInProcess = false;

        return nOutputBytes;
    }

    public static int encodeFlushNoGap(LameGlobalFlags flags, byte[] pMP3Buffer) {
        return Lame.INSTANCE.lame_encode_flush_nogap(flags, pMP3Buffer, 0);
    }

    public void writeVbrTag(String mp3File) {
        if (closed) throw new LamejbException("This encoder is closed, create a new one");

        if (Lame.INSTANCE.lame_get_bWriteVbrTag(flags) > 0) {
            //FileUtil.FILE fpStream = FileUtil.fopen( mp3File, "rb+" );
            Pointer fpStream = FileUtil.INSTANCE.fopen(mp3File, "rb+");

            // TODO the following line causes a crash on win xp (libmp3lame.dll 3.98 compiled
            //      using Makefile.msvc + msvc express 9). The problem seems to be caused by
            //      an fseek(FILE *_File, long _Offset, int _Origin) call
            // It works on linux CentOS 4.6 with lame-3.97-1.el4.rf.rpm from rpmforge repository
            if (!Platform.isWindows())
                Lame.INSTANCE.lame_mp3_tags_fid(flags, fpStream);

            FileUtil.INSTANCE.fclose(fpStream);
        }
    }


}
