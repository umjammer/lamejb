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


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.jna.NativeLong;
import net.sf.lamejb.blade.BladeEnc;
import net.sf.lamejb.impl.blade.BladeEncImpl;
import net.sf.lamejb.jna.blade.BE_CONFIG;
import net.sf.lamejb.jna.blade.BE_CONFIG_Format_LHV1;
import net.sf.lamejb.jna.blade.BE_VERSION;
import net.sf.lamejb.jna.blade.BladeMP3Enc;


/**
 * Blade implementation of the generic lamejb codec
 *
 * @author luigi
 */
public class BladeCodec implements LamejbCodec {

    @Override
    public void encodeFile(String inputFile, String outputFile, LamejbConfig config) {
        if (inputFile == null)
            throw new IllegalArgumentException("input file == null");

        if (config == null)
            config = new LamejbConfig();

        if (outputFile == null)
            outputFile = inputFile + ".mp3";

        BladeEnc blade = new BladeEncImpl();
        BE_VERSION version = blade.getVersion();
        BE_CONFIG beConfig = bladeInit(config);

        blade.encode(inputFile, outputFile, beConfig);
    }

    @Override
    public OutputStream encodeStream(InputStream inputStream, OutputStream outputStream, LamejbConfig config) {
        if (inputStream == null)
            throw new IllegalArgumentException("input stream == null");

        if (config == null)
            config = new LamejbConfig();

        if (outputStream == null)
            outputStream = new BufferedOutputStream(new ByteArrayOutputStream());

        BladeEnc blade = new BladeEncImpl();
        BE_VERSION version = blade.getVersion();
        BE_CONFIG beConfig = bladeInit(config);

        boolean isWav = true;
        blade.encode(inputStream, outputStream, beConfig, isWav);

        return outputStream;
    }

    private BE_CONFIG bladeInit(LamejbConfig config) {
        //System.out.println("LAME Version: " + version.byMajorVersion + "." + version.byMinorVersion);
        //System.out.println("Home page: " + version.getZHomepageAsString());
        BE_CONFIG beConfig = new BE_CONFIG();
        beConfig.format.setType(BE_CONFIG_Format_LHV1.class);

        beConfig.dwConfig = BladeMP3Enc.BE_CONFIG_LAME;
        beConfig.format.lhv1.dwStructVersion = 1;
        beConfig.format.lhv1.dwStructSize = beConfig.size();

        beConfig.format.lhv1.dwSampleRate = config.getSampleRate();
        beConfig.format.lhv1.nMode = new NativeLong(config.getMpegMode().bladeMode());
        beConfig.format.lhv1.dwBitrate = config.getBitRate();
        beConfig.format.lhv1.bWriteVBRHeader = config.isVbrTag();  // Write INFO tag

        //beConfig.format.lhv1.dwMpegVersion = BladeMP3Enc.MPEG1;
        //beConfig.format.lhv1.dwReSampleRate = 0; // NO RESAMPLE       
        //beConfig.format.lhv1.dwPsyModel = 0; //  USE DEFAULT PSYCHOACOUSTIC MODEL     
        //beConfig.format.lhv1.dwEmphasis = 0; // NO EMPHASIS TURNED ON
        //beConfig.format.lhv1.bOriginal = true;
        //beConfig.format.lhv1.bNoRes = true;

        return beConfig;
    }
}
