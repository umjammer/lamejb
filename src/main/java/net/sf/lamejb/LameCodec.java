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

import net.sf.lamejb.impl.std.LameEncoderFactoryImpl;
import net.sf.lamejb.std.LameConfig;
import net.sf.lamejb.std.LameEncoderFactory;
import net.sf.lamejb.std.StreamEncoder;


/**
 * Lame implementation of the generic lamejb codec
 * 
 * @author luigi
 */
public class LameCodec implements LamejbCodec
{

	public void encodeFile(String inputFile, String outputFile, LamejbConfig config)
	{
		if ( inputFile == null )
			throw new IllegalArgumentException("input file == null");
		
		if ( config == null )
			config = new LamejbConfig();
		
		if ( outputFile == null )
			outputFile = inputFile + ".mp3";
		
        LameEncoderFactory encoderFactory = new LameEncoderFactoryImpl();
        StreamEncoder encoder = encoderFactory.createStreamEncoder(inputFile);
        
        LameConfig conf = encoder.getLameConfig();

        conf.setInSamplerate(config.getSampleRate());
        conf.setBrate(config.getBitRate());
        conf.setBWriteVbrTag(config.isVbrTag());
        conf.setMode(config.getMpegMode().lameMode());
        
        // Number of channels is detected by lamejb
        //conf.setErrorProtection(true);        
        //conf.setOutSamplerate(0);  // LAME selects    
        //conf.setDisableReservoir(true);
        //conf.setPaddingType(PaddingType.PAD_NO);
        //conf.setQuality(2);
        //conf.setOriginal(true);
        
        encoder.encode(outputFile);
	}
	
	
	public OutputStream encodeStream(InputStream inputStream, OutputStream outputStream, LamejbConfig config)
	{
		if ( inputStream == null )
			throw new IllegalArgumentException("input stream == null");
		
		if ( config == null )
			config = new LamejbConfig();
		
		if ( outputStream == null )
			outputStream = new BufferedOutputStream(new ByteArrayOutputStream());
		
        LameEncoderFactory encoderFactory = new LameEncoderFactoryImpl();
        StreamEncoder encoder = encoderFactory.createStreamEncoder(inputStream);
        
        LameConfig conf = encoder.getLameConfig();

        conf.setInSamplerate(config.getSampleRate());
        conf.setBrate(config.getBitRate());
        conf.setBWriteVbrTag(config.isVbrTag());
        conf.setMode(config.getMpegMode().lameMode());
        
        encoder.encode(outputStream);
        return outputStream;
	}


	/*
	private LameConfig lameInit(LamejbConfig config)
	{
		
	}
	*/

}
