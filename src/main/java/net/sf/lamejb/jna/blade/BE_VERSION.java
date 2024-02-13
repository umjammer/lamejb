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

package net.sf.lamejb.jna.blade;


import com.sun.jna.Structure;


/**
 * The <code>BE_VERSION</code> class is the Java symmetric representation of the corresponding
 * C LAME structure as declared in the file BladeMP3EncDLL.h.
 *
 * <code><pre>
 * typedef struct	{
 *
 * // BladeEnc DLL Version number
 *
 * BYTE	byDLLMajorVersion;
 * BYTE	byDLLMinorVersion;
 *
 * // BladeEnc Engine Version Number
 *
 * BYTE	byMajorVersion;
 * BYTE	byMinorVersion;
 *
 * // DLL Release date
 *
 * BYTE	byDay;
 * BYTE	byMonth;
 * WORD	wYear;
 *
 * // BladeEnc	Homepage URL
 *
 * CHAR	zHomepage[BE_MAX_HOMEPAGE + 1];
 *
 * BYTE	byAlphaLevel;
 * BYTE	byBetaLevel;
 * BYTE	byMMXEnabled;
 *
 * BYTE	btReserved[125];
 *
 *
 * } BE_VERSION, *PBE_VERSION ATTRIBUTE_PACKED;
 * </pre></code>
 */
public class BE_VERSION extends Structure {

    public byte byDLLMajorVersion;
    public byte byDLLMinorVersion;
    public byte byMajorVersion;
    public byte byMinorVersion;
    public byte byDay;
    public byte byMonth;
    public short wYear;
    public byte[] zHomepage = new byte[BladeMP3Enc.BE_MAX_HOMEPAGE + 1]; // 129, embedded
    public byte byAlphaLevel;
    public byte byBetaLevel;
    public byte byMMXEnabled;
    public byte[] btReserved = new byte[125]; // embedded    


    /**
     * Returns the <code>zHomepage</code> attribute as a String.
     *
     * @return the String converted value of <code>zHomepage</code>
     */
    public String getZHomepageAsString() {
        byte[] url = zHomepage;
        String res = "";
        for (int i = 0; i < url.length; i++) {
            if (url[i] == 0) break;
            res += (char) url[i];
        }
        return res;
    }

}
