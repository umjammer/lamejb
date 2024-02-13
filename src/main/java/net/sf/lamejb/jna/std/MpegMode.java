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


/**
 * MPEG modes:
 * <pre>
 * typedef enum MPEG_mode_e {
 * STEREO = 0,
 * JOINT_STEREO,
 * DUAL_CHANNEL,    // LAME doesn't supports this!
 * MONO,
 * NOT_SET,
 * MAX_INDICATOR    // Don't use this! It's used for sanity checks.
 * } MPEG_mode;
 * </pre>
 */
public class MpegMode {

    public static final int STEREO = 0;
    public static final int JOINT_STEREO = 1;
    public static final int DUAL_CHANNEL = 2;
    public static final int MONO = 3;
    public static final int NOT_SET = 4;
    public static final int MAX_INDICATOR = 5;
}
