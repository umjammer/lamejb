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

package net.sf.lamejb.std;

import net.sf.lamejb.jna.std.LameGlobalFlags;

/**
 * This is the base interface of MP3 encoder utilities using the LAME standard API.
 *
 * <p>LAME flags must be set up before any encoding task, if an encoding
 * task was started the LAME configuration is freezed.</p>
 */
public interface Encoder
{
    /**
     * Returns the LAME configuration object of this encoder.
     *
     * @return the LAME configuration object.
     */       
    public LameConfig getLameConfig();
    
    /**
     * Returns the internal global flags object used by this encoder.
     *
     * @return the internal global flags object.
     */      
    public LameGlobalFlags getLameFlags();
    
    /**
     * Informs whether this encoder is closed. If closed must be disposed.
     *
     * @return true if this enconder is closed.
     */       
    public boolean isClosed();
    
    /**
     * Closes this encoder. A closed encoder cannot be reused.
     */    
    public void close();       
    
    /**
     * Writes the VBR tag info to the specified MP3 file.
     *
     * <p>VBR tag info property must be enabled (see 
     * {@link net.sf.lamejb.jna.std.Lame#lame_set_bWriteVbrTag(LameGlobalFlags,int)})
     * otherwise does nothing.</p>
     *
     * <p>The encoder must be open.</p>
     *
     * @param mp3File the file path of the MP3 file.
     */         
    public void writeVbrTag(String mp3File);
}
