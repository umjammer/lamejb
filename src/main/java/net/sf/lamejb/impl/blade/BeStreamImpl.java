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

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import net.sf.lamejb.LamejbException;
import net.sf.lamejb.blade.BeStream;
import net.sf.lamejb.jna.blade.BladeMP3Enc;


public class BeStreamImpl implements BeStream {

    protected NativeLong hbeStream;
    protected int nSamples;
    protected int outputBufferSize;

    /**
     * Creates a new instance of BeStreamImpl
     */
    public BeStreamImpl(NativeLong hbeStream, int nSamples, int outputBufferSize) {
        this.hbeStream = hbeStream;
        this.nSamples = nSamples;
        this.outputBufferSize = outputBufferSize;
    }

    private void checkStreamOpen() {
        if (hbeStream.longValue() == 0)
            throw new LamejbException("Stream is closed");
    }

    @Override
    public NativeLong getHandle() {
        return hbeStream;
    }

    @Override
    public int getNSamples() {
        return nSamples;
    }

    @Override
    public int getOutputBufferSize() {
        return outputBufferSize;
    }

    @Override
    public boolean isOpen() {
        return hbeStream.longValue() != 0;
    }

    @Override
    public void close() {
        checkStreamOpen();
        NativeLong err = BladeMP3Enc.INSTANCE.beCloseStream(hbeStream);
        BladeUtil.checkError(err);
        this.hbeStream.setValue(0L);
    }

    @Override
    public int deinitStream(byte[] pOutput) {
        checkStreamOpen();
        IntByReference pdwOutput = new IntByReference();
        NativeLong err = BladeMP3Enc.INSTANCE.beDeinitStream(hbeStream, pOutput, pdwOutput);
        BladeUtil.checkError(err);
        return pdwOutput.getValue();
    }

    @Override
    public int encodeChunk(int nSamples, short[] pSamples, byte[] pOutput) {
        checkStreamOpen();
        IntByReference pdwOutput = new IntByReference();
        NativeLong err =
                BladeMP3Enc.INSTANCE.beEncodeChunk(hbeStream, nSamples, pSamples, pOutput, pdwOutput);
        BladeUtil.checkError(err);
        return pdwOutput.getValue();
    }

    @Override
    public int encodeChunk(int nSamples, byte[] pSamples, byte[] pOutput) {
        checkStreamOpen();
        IntByReference pdwOutput = new IntByReference();
        NativeLong err = BladeMP3Enc.INSTANCE.beEncodeChunk(hbeStream, nSamples, pSamples, pOutput, pdwOutput);
        BladeUtil.checkError(err);
        return pdwOutput.getValue();
    }

    @Override
    public int encodeChunkFloatS16NI(NativeLong hbeStream, int nSamples, float[] buffer_l, float[] buffer_r, byte[] pOutput) {
        checkStreamOpen();
        IntByReference pdwOutput = new IntByReference();
        NativeLong err = BladeMP3Enc.INSTANCE.beEncodeChunkFloatS16NI(hbeStream, nSamples, buffer_l, buffer_r, pOutput, pdwOutput);
        BladeUtil.checkError(err);
        return pdwOutput.getValue();
    }

    @Override
    public int flushNoGap(NativeLong hbeStream, byte[] pOutput) {
        checkStreamOpen();
        IntByReference pdwOutput = new IntByReference();
        NativeLong err = BladeMP3Enc.INSTANCE.beFlushNoGap(hbeStream, pOutput, pdwOutput);
        BladeUtil.checkError(err);
        return pdwOutput.getValue();
    }
}
