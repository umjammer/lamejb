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

import com.sun.jna.Structure;


/**
 * <pre>
 * typedef struct {
 * // generic LAME version
 * int major;
 * int minor;
 * int alpha;               // 0 if not an alpha version
 * int beta;                // 0 if not a beta version
 *
 * // version of the psy model
 * int psy_major;
 * int psy_minor;
 * int psy_alpha;           // 0 if not an alpha version
 * int psy_beta;            // 0 if not a beta version
 *
 * // compile time features
 * const char *features;    // Don't make assumptions about the contents!
 * } lame_version_t;
 * </pre>
 */
public class LameVersion extends Structure {

    public int major;
    public int minor;
    public int alpha;               // 0 if not an alpha version                  
    public int beta;                // 0 if not a beta version                    

    // version of the psy model 
    public int psy_major;
    public int psy_minor;
    public int psy_alpha;           // 0 if not an alpha version                  
    public int psy_beta;            // 0 if not a beta version                    

    // compile time features 
    public String features;    // Don't make assumptions about the contents!    

}
