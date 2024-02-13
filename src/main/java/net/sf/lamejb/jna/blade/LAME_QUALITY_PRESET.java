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


/**
 * Java representation of the corresponding C LAME enumeration as declared
 * in the file BladeMP3EncDLL.h.
 * <code>
 * <pre>
 * typedef enum
 * {
 * LQP_NOPRESET			=-1,
 *
 * // QUALITY PRESETS
 * LQP_NORMAL_QUALITY		= 0,
 * LQP_LOW_QUALITY			= 1,
 * LQP_HIGH_QUALITY		= 2,
 * LQP_VOICE_QUALITY		= 3,
 * LQP_R3MIX				= 4,
 * LQP_VERYHIGH_QUALITY	= 5,
 * LQP_STANDARD			= 6,
 * LQP_FAST_STANDARD		= 7,
 * LQP_EXTREME				= 8,
 * LQP_FAST_EXTREME		= 9,
 * LQP_INSANE				= 10,
 * LQP_ABR					= 11,
 * LQP_CBR					= 12,
 * LQP_MEDIUM				= 13,
 * LQP_FAST_MEDIUM			= 14,
 *
 * // NEW PRESET VALUES
 * LQP_PHONE	=1000,
 * LQP_SW		=2000,
 * LQP_AM		=3000,
 * LQP_FM		=4000,
 * LQP_VOICE	=5000,
 * LQP_RADIO	=6000,
 * LQP_TAPE	=7000,
 * LQP_HIFI	=8000,
 * LQP_CD		=9000,
 * LQP_STUDIO	=10000
 *
 * } LAME_QUALITY_PRESET;
 * </pre>
 * </code>
 */
public class LAME_QUALITY_PRESET {

    public static final int LQP_NOPRESET = -1;

    // QUALITY PRESETS
    public static final int LQP_NORMAL_QUALITY = 0;
    public static final int LQP_LOW_QUALITY = 1;
    public static final int LQP_HIGH_QUALITY = 2;
    public static final int LQP_VOICE_QUALITY = 3;
    public static final int LQP_R3MIX = 4;
    public static final int LQP_VERYHIGH_QUALITY = 5;
    public static final int LQP_STANDARD = 6;
    public static final int LQP_FAST_STANDARD = 7;
    public static final int LQP_EXTREME = 8;
    public static final int LQP_FAST_EXTREME = 9;
    public static final int LQP_INSANE = 10;
    public static final int LQP_ABR = 11;
    public static final int LQP_CBR = 12;
    public static final int LQP_MEDIUM = 13;
    public static final int LQP_FAST_MEDIUM = 14;

    // NEW PRESET VALUES
    public static final int LQP_PHONE = 1000;
    public static final int LQP_SW = 2000;
    public static final int LQP_AM = 3000;
    public static final int LQP_FM = 4000;
    public static final int LQP_VOICE = 5000;
    public static final int LQP_RADIO = 6000;
    public static final int LQP_TAPE = 7000;
    public static final int LQP_HIFI = 8000;
    public static final int LQP_CD = 9000;
    public static final int LQP_STUDIO = 10000;
}
