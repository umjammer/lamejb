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

package net.sf.lamejb.jna.blade;


import com.sun.jna.NativeLong;
import com.sun.jna.Structure;


public class BE_CONFIG_Format_LHV1 extends Structure {

    // STRUCTURE INFORMATION
    public int dwStructVersion;
    public int dwStructSize;

    // BASIC ENCODER SETTINGS
    public int dwSampleRate;        // SAMPLERATE OF INPUT FILE
    public int dwReSampleRate;        // DOWNSAMPLERATE, 0=ENCODER DECIDES
    public NativeLong nMode;            // BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
    public int dwBitrate;        // CBR bitrate, VBR min bitrate
    public int dwMaxBitrate;        // CBR ignored, VBR Max bitrate
    public NativeLong nPreset;        // Quality preset, use one of the settings of the LAME_QUALITY_PRESET enum
    public int dwMpegVersion;        // FUTURE USE, MPEG-1 OR MPEG-2
    public int dwPsyModel;        // FUTURE USE, SET TO 0
    public int dwEmphasis;        // FUTURE USE, SET TO 0

    // BIT STREAM SETTINGS
    public boolean bPrivate;        // Set Private Bit (TRUE/FALSE)
    public boolean bCRC;            // Insert CRC (TRUE/FALSE)
    public boolean bCopyright;        // Set Copyright Bit (TRUE/FALSE)
    public boolean bOriginal;        // Set Original Bit (TRUE/FALSE)

    // VBR STUFF
    public boolean bWriteVBRHeader;    // WRITE XING VBR HEADER (TRUE/FALSE)
    public boolean bEnableVBR;        // USE VBR ENCODING (TRUE/FALSE)
    public int nVBRQuality;        // VBR QUALITY 0..9
    public int dwVbrAbr_bps;        // Use ABR in stead of nVBRQuality
    public int nVbrMethod;
    public boolean bNoRes;            // Disable Bit resorvoir (TRUE/FALSE)

    // MISC SETTINGS
    public boolean bStrictIso;        // Use strict ISO encoding rules (TRUE/FALSE)
    public short nQuality;        // Quality Setting, HIGH BYTE should be NOT LOW byte, otherwhise quality=5

    // FUTURE USE, SET TO 0, align strucutre to 331 bytes
    public byte[] btReserved = new byte[255 - 4 * 4 - 2];
}

