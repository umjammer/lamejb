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

import net.sf.lamejb.LamejbException;
import net.sf.lamejb.jna.std.Lame;
import net.sf.lamejb.std.GenericEncoder;


/**
 * Implementation of the GenericEncoder interface
 */
public class GenericEncoderImpl extends EncoderImpl implements GenericEncoder {

    public GenericEncoderImpl() {
        super();
    }

    @Override
    public void initEncoding(int[] pcmBufferSize, int[] mp3BufferSize) {
        if ((pcmBufferSize.length != 1) || (mp3BufferSize.length != 1))
            throw new LamejbException("Expected a byte array of length = 1");

        initEncodingTask();

        int numChannels = Lame.INSTANCE.lame_get_num_channels(flags);

        int mSamplesPerFrame = calcSamplesPerFrame(numChannels);
        int mOutBufferSize = calcOuputBufferSize(numChannels);

        // To allocate: byte[] pPCMBuffer = new byte[mSamplesPerFrame * 2]; // 2 bytes per short
        pcmBufferSize[0] = mSamplesPerFrame * 2;
        // To allocate: byte[] pMP3Buffer = new byte[mOutBufferSize]; 
        mp3BufferSize[0] = mOutBufferSize;
    }

    @Override
    public int encodeBuffer(byte[] pPCMBuffer, int size, byte[] pMP3Buffer) {
        if (!encodingTaskInProcess) throw new LamejbException("There is no encoding task in process");

        return encodeBuffer(flags, pPCMBuffer, size, pMP3Buffer);
    }

    @Override
    public int encodeFlush(byte[] pMP3Buffer) {
        return super.encodeFlush(pMP3Buffer);
    }
}
