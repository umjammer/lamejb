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

import java.io.InputStream;
import java.io.OutputStream;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import net.sf.lamejb.jna.blade.BE_CONFIG;
import net.sf.lamejb.jna.blade.BE_VERSION;


public interface BladeEnc {

    /**
     * Returns data about the LAME version used.
     *
     * @return the version object containing the LAME info.
     */
    BE_VERSION getVersion();

    /**
     * Creates (initializes) a new LAME encoding stream.
     * <br />
     * The returned {@link BeStream} is created and initialized
     * with the data returned by the call
     * {@link net.sf.lamejb.jna.blade.BladeMP3Enc#beInitStream(BE_CONFIG, IntByReference, IntByReference, NativeLongByReference)}.
     *
     * @param beConfig the encoding configuration object used to create the stream.
     * @return a new opened encoding stream.
     */
    BeStream initStream(BE_CONFIG beConfig);

    /**
     * Encodes the input WAV file and writes the MP3 output to the specified file.
     * <br />
     * The file must be a WAV file.
     * <p>If VBR tag info is specified is automatically added.</p>
     *
     * @param wavFile  the input path of WAV file to be encoded.
     * @param mp3File  the output path of the MP3 file to be encoded.
     * @param beConfig the encoding configuration info used.
     * @see #encode(InputStream, OutputStream, BE_CONFIG, boolean)
     */
    void encode(String wavFile, String mp3File, BE_CONFIG beConfig);

    /**
     * Encodes the input stream and writes the MP3 output to the specified output stream.
     * <br />
     * <p>If the <code>isWav</code> parameter is set to true the stream (is supposed)
     * starts with a WAV header, else is considered as a LPCM stream.
     * <br />
     * <p>Streams are NOT closed and VBR info can not be added.</p>
     *
     * @param input     the input stream to be encoded.
     * @param mp3Output the output stream to write the MP3 encoded output.
     * @param beConfig  the encoding configuration info used.
     * @param isWav     if the input stream has a WAV header.
     */
    void encode(InputStream input, OutputStream mp3Output, BE_CONFIG beConfig, boolean isWav);
}
