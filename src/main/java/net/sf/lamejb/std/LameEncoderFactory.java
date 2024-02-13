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

/**
 * The <code>LameEncoderFactory</code> is the starting interface of the Lamejb
 * utilities using an object oriented fashion to work with the LAME C methods using 
 * the LAME standard API.
 *
 */
public interface LameEncoderFactory
{
    /**
     * Creates a new MP3 stream encoder using the specified file as audio source.
     *
     * <p>If the specified file is a WAV file, the header is processed and 
     * extracted data as number of channels, mono/stereo, and sample rate are
     * used to set up the LAME encoder.
     * </p>
     *
     * @param file the path of the audio source.
     * @return a new MP3 stream encoder
     */    
    public StreamEncoder createStreamEncoder(String file);

    /**
     * Creates a new MP3 stream encoder using the specified stream as audio source.
     *
     * <p>If WAV the header is processed as explained in {@link #createStreamEncoder(String)}</p>
     *
     * @param stream the path of the audio source.
     * @return a new MP3 stream encoder
     */ 
    public StreamEncoder createStreamEncoder(InputStream stream);       
    
    /**
     * Creates a new MP3 generic stream encoder.
     *
     * <p>If the specified file is a WAV file, the header is processed and 
     * extracted data as number of channels, mono/stereo, and sample rate are
     * used to set up the LAME encoder.
     * </p>
     *
     * @return a new MP3 stream encoder
     */   
    public GenericEncoder createGenericEncoder();
}
