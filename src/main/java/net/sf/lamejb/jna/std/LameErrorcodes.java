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
 * <pre>
 * typedef enum {
 * LAME_OKAY             =   0,
 * LAME_NOERROR          =   0,
 * LAME_GENERICERROR     =  -1,
 * LAME_NOMEM            = -10,
 * LAME_BADBITRATE       = -11,
 * LAME_BADSAMPFREQ      = -12,
 * LAME_INTERNALERROR    = -13,
 *
 * FRONTEND_READERROR    = -80,
 * FRONTEND_WRITEERROR   = -81,
 * FRONTEND_FILETOOLARGE = -82
 *
 * } lame_errorcodes_t;
 * </pre>
 */
public class LameErrorcodes {

    public static final int LAME_OKAY = 0;
    public static final int LAME_NOERROR = 0;
    public static final int LAME_GENERICERROR = -1;
    public static final int LAME_NOMEM = -10;
    public static final int LAME_BADBITRATE = -11;
    public static final int LAME_BADSAMPFREQ = -12;
    public static final int LAME_INTERNALERROR = -13;

    public static final int FRONTEND_READERROR = -80;
    public static final int FRONTEND_WRITEERROR = -81;
    public static final int FRONTEND_FILETOOLARGE = -82;
}
