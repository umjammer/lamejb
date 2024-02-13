/*
 * Lamejb Java based API for LAME MP3 encoder
 *
 * Copyright (c) 2008 Luigi Bitonti
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

package net.sf.lamejb;


import java.io.InputStream;
import java.io.OutputStream;


/**
 * Generic lamejb codec interface
 *
 * @author luigi
 */
public interface LamejbCodec {

    /**
     * Encode PCM/WAV input file into mp3 output file
     *
     * @param inputFile  the PCM/WAV filename
     * @param outputFile the mp3 filename
     * @param config     configuration parameters for the encoder
     */
    public void encodeFile(String inputFile, String outputFile, LamejbConfig config);


    /**
     * Encode PCM/WAV input stream into mp3 output stream
     *
     * @param inputStream  the PCM/WAV stream
     * @param outputStream the mp3 stream
     * @param config       configuration parameters for the encoder
     */
    public OutputStream encodeStream(InputStream inputStream, OutputStream outputStream, LamejbConfig config);


    //public void decodeFile(File inputFile, File outputFile);
    //public void decodeStream(InputStream inputStream, OutputStream outputStream);

}
