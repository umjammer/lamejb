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

package net.sf.lamejb.blade;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import net.sf.lamejb.jna.blade.BE_CONFIG;


public interface BeStream {

    /**
     * Returns the stream handle used by LAME.
     * <p>
     * This is the handle returned in the <code>phbeStream</code> parameter by the call
     * {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beInitStream(BE_CONFIG, IntByReference, IntByReference, NativeLongByReference)}
     * and used to encode.
     * <br />
     *
     * @return the stream handle. If 0 the stream is closed.
     */
    NativeLong getHandle();

    /**
     * Returns the maximum number of samples per encoding call.
     * <p>
     * This is the value returned in the <code>pDwSamples</code> parameter by the call
     * {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beInitStream(BE_CONFIG, IntByReference, IntByReference, NativeLongByReference)}.
     *
     * @return the stream handle.
     * @see #encodeChunk(int, short[], byte[])
     */
    int getNSamples();

    /**
     * Returns the maximum output buffer size per encoding call.
     * <p>
     * This is the value returned in the <code>pDwSamples</code> parameter by the call
     * {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beInitStream(BE_CONFIG, IntByReference, IntByReference, NativeLongByReference)}.
     *
     * @return the maximum output buffer size.
     * @see #encodeChunk(int, short[], byte[])
     */
    int getOutputBufferSize();

    /**
     * Returns true if the encoding stream is open.
     *
     * @return true if open.
     */
    boolean isOpen();

    /**
     * Closes the stream, the instance must be discarded.
     * <br />
     * The method {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beCloseStream(NativeLong)} is called.
     */
    void close();

    /**
     * Flushes to the output buffer the internal encoder data remaining.
     * <br />
     * The method {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beInitStream(BE_CONFIG, IntByReference, IntByReference, NativeLongByReference)}
     * is called.
     *
     * @param pOutput the output buffer receiving the remaining data.
     * @return the number of bytes written to the output buffer.
     */
    int deinitStream(byte[] pOutput);

    /**
     * Encodes a chunk of audio samples, the output data is written to the output buffer.
     * <br />
     * The method {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beEncodeChunk(NativeLong, int, short[], byte[], IntByReference)}
     * is called.
     *
     * @param nSamples the number of samples to be encoded.
     * @param pSamples the samples buffer to be written.
     * @param pOutput  the output buffer receiving the encoded data.
     * @return the number of bytes written to the output buffer.
     */
    int encodeChunk(int nSamples, short[] pSamples, byte[] pOutput);

    /**
     * Encodes a chunk of audio samples, the output data is written to the output buffer.
     * <br />
     * This is a convenience method using a byte[] samples buffer instead a short[] buffer.
     * The byte[] buffer must be 2x the size of the corresponding short[] buffer.
     *
     * @param nSamples the number of samples to be encoded.
     * @param pSamples the samples buffer in bytes (two bytes per sample) to be written.
     * @param pOutput  the output buffer receiving the encoded data.
     * @return the number of bytes written to the output buffer.
     * @see #encodeChunk(int, short[], byte[])
     */
    int encodeChunk(int nSamples, byte[] pSamples, byte[] pOutput);

    /**
     * Undocumented method.
     */
    int encodeChunkFloatS16NI(NativeLong hbeStream, int nSamples, float[] buffer_l, float[] buffer_r, byte[] pOutput);

    /**
     * Undocumented method.
     */
    int flushNoGap(NativeLong hbeStream, byte[] pOutput);
}
