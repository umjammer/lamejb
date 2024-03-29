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

package net.sf.lamejb.std;

import java.io.InputStream;
import java.io.OutputStream;

import net.sf.lamejb.jna.std.LameGlobalFlags;


/**
 * This interface may be used to encode the specified source file or
 * stream to MP3 with only one call or progressively by chunks.
 *
 * <p>LAME flags must be set up before starting an encoding task.
 * If the source file or stream is a WAV the header is used to
 * set up the LAME encoder with the number of channels and sample rate.
 * If PCM the encoder uses the default values of LAME.
 * Call the {@link #getLameConfig()} method to config any other encoding
 * parameters (or to overwrite the defaults).</p>
 *
 * <p>If the source audio is stereo the PCM data must be interleaved.</p>
 *
 * @see LameEncoderFactory#createStreamEncoder(String)
 * @see LameEncoderFactory#createStreamEncoder(InputStream)
 */
public interface StreamEncoder extends Encoder {

    /**
     * Returns the input stream used to encode.
     *
     * @return the input stream.
     */
    InputStream getSourceInputStream();

    /**
     * Encodes the input source to MP3 saving to the specified file.
     *
     * <p>If VBR Info tag is set (see
     * {@link net.sf.lamejb.jna.std.Lame#lame_set_bWriteVbrTag(LameGlobalFlags, int)})
     * is automatically added.</p>
     *
     * <p>The encoder is closed.</p>
     *
     * @param mp3File the MP3 file path.
     */
    void encode(String mp3File);

    /**
     * Encodes the input source to MP3 saving to the specified stream.
     *
     * <p>The encoder is not closed but no new encoding can be done.
     * The encoder must be closed explicitly (see {@link Encoder#close()}).</p>
     *
     * <p>VBR Info tag is not added, you can use the
     * method {@link Encoder#writeVbrTag(String)}</p>
     */
    void encode(OutputStream mp3Stream);

    /**
     * Starts a progressive encoding process.
     *
     * <p>The required size of the target MP3 byte buffer is returned</p>
     *
     * @return pcmBufferSize the required MP3 buffer size.
     */
    int initEncoding();

    /**
     * Encodes a chunk of PCM data read from the input source.
     *
     * <p>MP3 buffer must have the length provided by the method
     * {@link #initEncoding()}</p>
     *
     * @param mp3Buffer the MP3 buffer to fill with the encoded MP3 audio.
     * @return the number of bytes used to fill the MP3 buffer. If 0 the encoding process is finished.
     */
    int encodeBuffer(byte[] mp3Buffer);
}
