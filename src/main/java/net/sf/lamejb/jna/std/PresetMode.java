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
 * Presets:
 * <pre>
 * typedef enum preset_mode_e {
 * //values from 8 to 320 should be reserved for abr bitrates
 * //for abr I'd suggest to directly use the targeted bitrate as a value
 * ABR_8 = 8,
 * ABR_320 = 320,
 *
 * V9 = 410,  // Vx to match Lame and VBR_xx to match FhG
 * VBR_10 = 410,
 * V8 = 420,
 * VBR_20 = 420,
 * V7 = 430,
 * VBR_30 = 430,
 * V6 = 440,
 * VBR_40 = 440,
 * V5 = 450,
 * VBR_50 = 450,
 * V4 = 460,
 * VBR_60 = 460,
 * V3 = 470,
 * VBR_70 = 470,
 * V2 = 480,
 * VBR_80 = 480,
 * V1 = 490,
 * VBR_90 = 490,
 * V0 = 500,
 * VBR_100 = 500,
 *
 *
 * //still there for compatibility
 * R3MIX = 1000,
 * STANDARD = 1001,
 * EXTREME = 1002,
 * INSANE = 1003,
 * STANDARD_FAST = 1004,
 * EXTREME_FAST = 1005,
 * MEDIUM = 1006,
 * MEDIUM_FAST = 1007
 * } preset_mode;
 *  </pre>
 */
public class PresetMode {

    public static final int ABR_8 = 8;
    public static final int ABR_320 = 320;
    public static final int V9 = 410;
    public static final int VBR_10 = 410;
    public static final int V8 = 420;
    public static final int VBR_20 = 420;
    public static final int V7 = 430;
    public static final int VBR_30 = 430;
    public static final int V6 = 440;
    public static final int VBR_40 = 440;
    public static final int V5 = 450;
    public static final int VBR_50 = 450;
    public static final int V4 = 460;
    public static final int VBR_60 = 460;
    public static final int V3 = 470;
    public static final int VBR_70 = 470;
    public static final int V2 = 480;
    public static final int VBR_80 = 480;
    public static final int V1 = 490;
    public static final int VBR_90 = 490;
    public static final int V0 = 500;
    public static final int VBR_100 = 500;


    public static final int R3MIX = 1000;
    public static final int STANDARD = 1001;
    public static final int EXTREME = 1002;
    public static final int INSANE = 1003;
    public static final int STANDARD_FAST = 1004;
    public static final int EXTREME_FAST = 1005;
    public static final int MEDIUM = 1006;
    public static final int MEDIUM_FAST = 1007;

}
