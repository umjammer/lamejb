package net.sf.lamejb.jna.blade;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;


/**
 * The <code>BladeMP3EncDLL</code> class is the container of
 * the symmetric C LAME exported methods and constants declared
 * in the file BladeMP3EncDLL.h.
 */
public interface BladeMP3Enc extends Library {

    BladeMP3Enc INSTANCE = Native.load((Platform.isWindows() ? "lame_enc" : null), BladeMP3Enc.class);

    // encoding formats

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_CONFIG_MP3			0
     *  </pre>
     * </code>
     */
    int BE_CONFIG_MP3 = 0;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_CONFIG_LAME			256
     *  </pre>
     * </code>
     */
    int BE_CONFIG_LAME = 256;

    /* error codes */

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_ERR_SUCCESSFUL	0x00000000
     *  </pre>
     * </code>
     */
    int BE_ERR_SUCCESSFUL = 0x00000000;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_ERR_INVALID_FORMAT	0x00000001
     *  </pre>
     * </code>
     */
    int BE_ERR_INVALID_FORMAT = 0x00000001;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_ERR_INVALID_FORMAT_PARAMETERS	0x00000002
     *  </pre>
     * </code>
     */
    int BE_ERR_INVALID_FORMAT_PARAMETERS = 0x00000002;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_ERR_NO_MORE_HANDLES	0x00000003
     *  </pre>
     * </code>
     */
    int BE_ERR_NO_MORE_HANDLES = 0x00000003;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_ERR_INVALID_HANDLE	0x00000004
     *  </pre>
     * </code>
     */
    int BE_ERR_INVALID_HANDLE = 0x00000004;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_ERR_BUFFER_TOO_SMALL	0x00000005
     *  </pre>
     * </code>
     */
    int BE_ERR_BUFFER_TOO_SMALL = 0x00000005;

    /* other constants */

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_MAX_HOMEPAGE			128
     *  </pre>
     * </code>
     */
    int BE_MAX_HOMEPAGE = 128;

    /* format specific variables */

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_MP3_MODE_STEREO		0
     *  </pre>
     * </code>
     */
    NativeLong BE_MP3_MODE_STEREO = new NativeLong(0);

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_MP3_MODE_JSTEREO		1
     *  </pre>
     * </code>
     */
    NativeLong BE_MP3_MODE_JSTEREO = new NativeLong(1);

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_MP3_MODE_DUALCHANNEL		2
     *  </pre>
     * </code>
     */
    NativeLong BE_MP3_MODE_DUALCHANNEL = new NativeLong(2);

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		BE_MP3_MODE_MONO		3
     *  </pre>
     * </code>
     */
    NativeLong BE_MP3_MODE_MONO = new NativeLong(3);

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		MPEG1	1
     *  </pre>
     * </code>
     */
    int MPEG1 = 1;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define		MPEG2	0
     *  </pre>
     * </code>
     */
    int MPEG2 = 0;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define CURRENT_STRUCT_VERSION 1
     *  </pre>
     * </code>
     */
    int CURRENT_STRUCT_VERSION = 1;

    /**
     * Corresponds to C declaration:
     * <code>
     * <pre>
     *  #define CURRENT_STRUCT_SIZE sizeof(BE_CONFIG)	// is currently 331 bytes
     *  </pre>
     * </code>
     */
    int CURRENT_STRUCT_SIZE = 331;

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beCloseStream(HBE_STREAM hbeStream);
     * </pre>
     * </code>
     */
    NativeLong beCloseStream(NativeLong hbeStream);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beDeinitStream(HBE_STREAM hbeStream, PBYTE pOutput, PDWORD pdwOutput);
     * </pre>
     * </code>
     */
    NativeLong beDeinitStream(NativeLong hbeStream, byte[] pOutput, IntByReference pdwOutput);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beEncodeChunk(HBE_STREAM hbeStream, DWORD nSamples, PSHORT pSamples, PBYTE pOutput, PDWORD pdwOutput);
     * </pre>
     * </code>
     */
    NativeLong beEncodeChunk(NativeLong hbeStream, int nSamples, short[] pSamples,
                             byte[] pOutput, IntByReference pdwOutput);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beEncodeChunk(HBE_STREAM hbeStream, DWORD nSamples, PSHORT pSamples, PBYTE pOutput, PDWORD pdwOutput);
     * </pre>
     * </code>
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #beEncodeChunk(NativeLong, int, short[], byte[], IntByReference)
     */
    NativeLong beEncodeChunk(NativeLong hbeStream, int nSamples, byte[] pSamples,
                             byte[] pOutput, IntByReference pdwOutput);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beEncodeChunkFloatS16NI(HBE_STREAM hbeStream, DWORD nSamples, PFLOAT buffer_l,
     * PFLOAT buffer_r, PBYTE pOutput, PDWORD pdwOutput);
     * </pre>
     * </code>
     */
    NativeLong beEncodeChunkFloatS16NI(NativeLong hbeStream, int nSamples, float[] buffer_l,
                                       float[] buffer_r, byte[] pOutput, IntByReference pdwOutput);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beFlushNoGap(HBE_STREAM hbeStream, PBYTE pOutput, PDWORD pdwOutput);
     * </pre>
     * </code>
     */
    NativeLong beFlushNoGap(NativeLong hbeStream, byte[] pOutput, IntByReference pdwOutput);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beInitStream(PBE_CONFIG pbeConfig, PDWORD dwSamples, PDWORD dwBufferSize, PHBE_STREAM phbeStream);
     * </pre>
     * </code>
     */
    NativeLong beInitStream(BE_CONFIG pbeConfig, IntByReference pDwSamples,
                            IntByReference pDwBufferSize, NativeLongByReference phbeStream);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * VOID beVersion(PBE_VERSION pbeVersion);
     * </pre>
     * </code>
     */
    void beVersion(BE_VERSION pbeVersion);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * VOID beWriteVBRHeader( LPCSTR pszMP3FileName );
     * </pre>
     * </code>
     */
    NativeLong beWriteVBRHeader(String pszMP3FileName);

    /**
     * Corresponds with the C method:
     * <code>
     * <pre>
     * BE_ERR	beWriteInfoTag( HBE_STREAM hbeStream, LPCSTR lpszFileName );
     * </pre>
     * </code>
     */
    NativeLong beInitStream(NativeLong hbeStream, String lpszFileName);
}
