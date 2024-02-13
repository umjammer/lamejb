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


/**
 * Generic codec configuration object. It allows clients to pass
 * encoding parameters to the blade and lame codecs.
 *
 * @author luigi
 */
public class LamejbConfig {

    public enum MpegMode {
        STEREO(0, 0),
        JOINT_STEREO(1, 1),
        MONO(3, 3);

        private final int lameMode;   // for lame
        private final int bladeMode; // for blade

        MpegMode(int lameMode, int bladeMode) {
            this.lameMode = lameMode;
            this.bladeMode = bladeMode;
        }

        public int lameMode() {
            return lameMode;
        }

        public int bladeMode() {
            return bladeMode;
        }
    }

//    public enum MpegType {
//        MPEG1(0, 1),
//        MPEG2(1, 0);
//
//        private final int lameType;   // for lame
//        private final int bladeType; // for blade
//
//        MpegType(int lameType, int bladeType) {
//            this.lameType = lameType;
//            this.bladeType = bladeType;
//        }
//
//        public int lameType() {
//            return lameType;
//        }
//
//        public int bladeType() {
//            return bladeType;
//        }
//    }

    private int sampleRate = 44100;
    private int bitRate = 128;
    private MpegMode mpegMode = MpegMode.JOINT_STEREO;
//    private MpegType mpegType;
    private boolean vbrTag = true;

    public LamejbConfig() {
    }

    public LamejbConfig(int sampleRate, int bitRate,
                        MpegMode mpegMode, boolean vbrTag) {
        this.sampleRate = sampleRate;
        this.bitRate = bitRate;
        this.mpegMode = mpegMode;
        this.vbrTag = vbrTag;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public int getBitRate() {
        return bitRate;
    }

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public MpegMode getMpegMode() {
        return mpegMode;
    }

    public void setMpegMode(MpegMode mpegMode) {
        this.mpegMode = mpegMode;
    }

    public boolean isVbrTag() {
        return vbrTag;
    }

    public void setVbrTag(boolean vbrTag) {
        this.vbrTag = vbrTag;
    }
}
