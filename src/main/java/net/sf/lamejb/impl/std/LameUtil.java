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


import net.sf.lamejb.LamejbException;
import net.sf.lamejb.jna.std.LameErrorcodes;


/**
 * Utility methods for the lame API
 *
 * @author luigi
 */
public class LameUtil {

    /**
     * Verifies if there has been any error in the lame encoder
     * and throws an exception
     *
     * @param err the error code
     */
    public static void checkError(int err) {
        if (err == LameErrorcodes.LAME_OKAY) return; // Idem LAME_NOERROR

        String errMsg = switch (err) {
            case LameErrorcodes.LAME_GENERICERROR -> "LAME_GENERICERROR";
            case LameErrorcodes.LAME_NOMEM -> "LAME_NOMEM";
            case LameErrorcodes.LAME_BADBITRATE -> "LAME_BADBITRATE";
            case LameErrorcodes.LAME_BADSAMPFREQ -> "LAME_BADSAMPFREQ";
            case LameErrorcodes.LAME_INTERNALERROR -> "LAME_INTERNALERROR";
            case LameErrorcodes.FRONTEND_READERROR -> "FRONTEND_READERROR";
            case LameErrorcodes.FRONTEND_WRITEERROR -> "FRONTEND_WRITEERROR";
            case LameErrorcodes.FRONTEND_FILETOOLARGE -> "FRONTEND_FILETOOLARGE";
            default -> "UNKNOWN";
        };

        throw new LamejbException("ERROR: " + errMsg, err);
    }
}
