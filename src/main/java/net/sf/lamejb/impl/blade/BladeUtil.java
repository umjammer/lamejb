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

package net.sf.lamejb.impl.blade;


import com.sun.jna.NativeLong;
import net.sf.lamejb.LamejbException;
import net.sf.lamejb.jna.blade.BladeMP3Enc;


/**
 * Utility methods for the blade API
 *
 * @author luigi
 */
public class BladeUtil {

    /**
     * Verifies if there has been any error in the blade encoder
     * and throws an exception
     *
     * @param err the error code
     */
    public static void checkError(NativeLong err) {
        if (err.intValue() != BladeMP3Enc.BE_ERR_SUCCESSFUL)
            throw new LamejbException(BladeUtil.getErrorMessage(err), err.intValue());
    }


    /**
     * Returns a blade error message in human readable form
     *
     * @param err the error code
     * @return related error message
     */
    public static String getErrorMessage(NativeLong err) {
        String msg = "";
        switch (err.intValue()) {
        case BladeMP3Enc.BE_ERR_INVALID_FORMAT:
            msg += "BE_ERR_INVALID_FORMAT";
            break;
        case BladeMP3Enc.BE_ERR_INVALID_FORMAT_PARAMETERS:
            msg += "BE_ERR_INVALID_FORMAT_PARAMETERS";
            break;
        case BladeMP3Enc.BE_ERR_NO_MORE_HANDLES:
            msg += "BE_ERR_NO_MORE_HANDLES";
            break;
        case BladeMP3Enc.BE_ERR_INVALID_HANDLE:
            msg += "BE_ERR_INVALID_HANDLE";
            break;
        case BladeMP3Enc.BE_ERR_BUFFER_TOO_SMALL:
            msg += "BE_ERR_BUFFER_TOO_SMALL";
            break;
        default:
            msg += err;
        }
        return msg;
    }


}
