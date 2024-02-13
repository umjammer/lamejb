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

package net.sf.lamejb.impl.std;


import java.io.InputStream;

import net.sf.lamejb.std.GenericEncoder;
import net.sf.lamejb.std.LameEncoderFactory;
import net.sf.lamejb.std.StreamEncoder;


/**
 * Implementation of the EncoderFactory interface 
 *
 */
public class LameEncoderFactoryImpl implements LameEncoderFactory
{
    
    public LameEncoderFactoryImpl()
    {
    }

    public StreamEncoder createStreamEncoder(String file)
    {
        if (StreamEncoderWAVImpl.isWAV(file))
            return new StreamEncoderWAVImpl(file);
        else
            return new StreamEncoderPCMImpl(file);
    }

    public StreamEncoder createStreamEncoder(InputStream stream)
    {
        if (StreamEncoderWAVImpl.isWAV(stream))
            return new StreamEncoderWAVImpl(stream);
        else
            return new StreamEncoderPCMImpl(stream);
    }
    /*
    public StreamEncoder createStreamEncoder(InputStream stream,String mime)
    {
        // http://en.wikipedia.org/wiki/WAV
        if (mime != null)
            if (mime.equals("audio/wav") || mime.equals("audio/wave") || 
                mime.equals("audio/x-wav"))        
                return new StreamEncoderWAVImpl(stream);        
            else
                throw new LamejbException("Unsupported mime type:" + mime);
        else
            return new StreamEncoderPCMImpl(stream);
    }
    */
    
    public GenericEncoder createGenericEncoder()
    {
        return new GenericEncoderImpl();
    }
    
}
