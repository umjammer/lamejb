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

package net.sf.lamejb.jna.std;

import java.util.List;

import com.sun.jna.NativeLong;
import com.sun.jna.Structure;


/**
 * <pre>
 * typedef struct {
 * int header_parsed;   // 1 if header was parsed and following data was computed
 * int stereo;          // number of channels
 * int samplerate;      // sample rate
 * int bitrate;         // bitrate
 * int mode;            // mp3 frame type
 * int mode_ext;        // mp3 frame type
 * int framesize;       // number of samples per mp3 frame
 *
 * // this data is only computed if mpglib detects a Xing VBR header
 * unsigned long nsamp; // number of samples in mp3 file.
 * int totalframes;     // total number of frames in mp3 file
 * // this data is not currently computed by the mpglib routines
 * int framenum;
 * } mp3data_struct;
 * </pre>
 */
public class Mp3data extends Structure {

    /** 1 if header was parsed and following data was computed */
    public int header_parsed;

    /** number of channels */
    public int stereo;
    /** sample rate */
    public int samplerate;
    /** bitrate */
    public int bitrate;
    /** mp3 frame type */
    public int mode;
    /** mp3 frame type */
    public int mode_ext;
    /** number of samples per mp3 frame */
    public int framesize;

    // this data is only computed if mpglib detects a Xing VBR header

    /** number of samples in mp3 file. */
    public NativeLong nsamp;
    /** total number of frames in mp3 file */
    public int totalframes;

    // this data is not currently computed by the mpglib routines

    /** frames decoded counter */
    public int framenum;

    public Mp3data() {}

    @Override
    protected List<String> getFieldOrder() {
        return List.of(
                "header_parsed", "stereo", "samplerate", "bitrate", "mode", "mode_ext", "framesize", "nsamp", "totalframes", "framenum"
        );
    }
}
