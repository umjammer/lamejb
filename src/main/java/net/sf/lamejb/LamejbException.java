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

package net.sf.lamejb;

import java.io.Serial;


/**
 * The <code>LamejbException</code> is the exception class used to throw errors
 * detected inside the Lamejb library.
 * <br />
 * If a LAME method returns a non-null error code, this code is used to raise
 * this exception.
 */
public class LamejbException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    protected int code;

    /**
     * Constructs a new exception with the specified detail message.
     * <br/>
     * The corresponding base constructor is called.
     *
     * @param message the detail message.
     */
    public LamejbException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * <br/>
     * The corresponding base constructor is called.
     *
     * @param message the detail message.
     * @param cause   the cause.
     */
    public LamejbException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * <br/>
     * The corresponding base constructor is called.
     *
     * @param cause the cause.
     */
    public LamejbException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified LAME error code.
     *
     * @param msg explanation message.
     * @code the LAME error code.
     * @see #getCode()
     */
    public LamejbException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    /**
     * Returns the LAME error code.
     *
     * @return the LAME error code if was defined else returns 0.
     * @see #LamejbException(String, int)
     */
    public int getCode() {
        return code;
    }
}
