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

import com.sun.jna.Structure;


/**
 * The <code>BE_CONFIG</code> class is the Java symmetric representation of the corresponding
 * C LAME structure as declared in the file BladeMP3EncDLL.h.
 *
 * <code><pre>
 * typedef struct	{
 * DWORD	dwConfig;			// BE_CONFIG_XXXXX
 * // Currently only BE_CONFIG_MP3 is supported
 * union	{
 *
 * struct	{
 *
 * DWORD	dwSampleRate;		// 48000, 44100 and 32000 allowed
 * BYTE	byMode;			// BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
 * WORD	wBitrate;		// 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256 and 320 allowed
 * BOOL	bPrivate;
 * BOOL	bCRC;
 * BOOL	bCopyright;
 * BOOL	bOriginal;
 *
 * } mp3;				// BE_CONFIG_MP3
 *
 * struct
 * {
 * // STRUCTURE INFORMATION
 * DWORD			dwStructVersion;
 * DWORD			dwStructSize;
 *
 * // BASIC ENCODER SETTINGS
 * DWORD			dwSampleRate;		// SAMPLERATE OF INPUT FILE
 * DWORD			dwReSampleRate;		// DOWNSAMPLERATE, 0=ENCODER DECIDES
 * LONG			nMode;			// BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
 * DWORD			dwBitrate;		// CBR bitrate, VBR min bitrate
 * DWORD			dwMaxBitrate;		// CBR ignored, VBR Max bitrate
 * LONG			nPreset;		// Quality preset, use one of the settings of the LAME_QUALITY_PRESET enum
 * DWORD			dwMpegVersion;		// FUTURE USE, MPEG-1 OR MPEG-2
 * DWORD			dwPsyModel;		// FUTURE USE, SET TO 0
 * DWORD			dwEmphasis;		// FUTURE USE, SET TO 0
 *
 * // BIT STREAM SETTINGS
 * BOOL			bPrivate;		// Set Private Bit (TRUE/FALSE)
 * BOOL			bCRC;			// Insert CRC (TRUE/FALSE)
 * BOOL			bCopyright;		// Set Copyright Bit (TRUE/FALSE)
 * BOOL			bOriginal;		// Set Original Bit (TRUE/FALSE)
 *
 * // VBR STUFF
 * BOOL			bWriteVBRHeader;	// WRITE XING VBR HEADER (TRUE/FALSE)
 * BOOL			bEnableVBR;		// USE VBR ENCODING (TRUE/FALSE)
 * INT			nVBRQuality;		// VBR QUALITY 0..9
 * DWORD			dwVbrAbr_bps;		// Use ABR in stead of nVBRQuality
 * VBRMETHOD		nVbrMethod;
 * BOOL			bNoRes;			// Disable Bit resorvoir (TRUE/FALSE)
 *
 * // MISC SETTINGS
 * BOOL			bStrictIso;		// Use strict ISO encoding rules (TRUE/FALSE)
 * WORD			nQuality;		// Quality Setting, HIGH BYTE should be NOT LOW byte, otherwhise quality=5
 *
 * // FUTURE USE, SET TO 0, align strucutre to 331 bytes
 * BYTE			btReserved[255-4*sizeof(DWORD) - sizeof( WORD )];
 *
 * } LHV1;			// LAME header version 1
 *
 * struct	{
 *
 * DWORD	dwSampleRate;
 * BYTE	byMode;
 * WORD	wBitrate;
 * BYTE	byEncodingMethod;
 *
 * } aac;
 *
 * } format;
 *
 * } BE_CONFIG, *BE_CONFIG ATTRIBUTE_PACKED;
 * </pre></code>
 */
public class BE_CONFIG extends Structure {

    public int dwConfig;
    public BE_CONFIG_Format format = new BE_CONFIG_Format();
}
