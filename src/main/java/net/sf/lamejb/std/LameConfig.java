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

import net.sf.lamejb.jna.std.Lame;
import net.sf.lamejb.jna.std.LameGlobalFlags;
import net.sf.lamejb.jna.std.LameVersion;


/**
 * This interface basically wraps the low level {@link net.sf.lamejb.jna.std.Lame}
 * API with some added checking.
 *
 * <p>If an encoding process was started the configuration is freezed (no change
 * is allowed).</p>
 *
 * @see Encoder#getLameConfig()
 */
public interface LameConfig {

    /**
     * The LAME flags associated to this configuration object.
     *
     * @return the flags object.
     */
    LameGlobalFlags getLameFlags();

    /**
     * number of samples.  default = 2^32-1
     *
     * <pre>int CDECL lame_set_num_samples(lame_global_flags *, unsigned long);</pre>
     */
    void setNumSamples(int samples);

    /**
     * number of samples.  default = 2^32-1
     *
     * <pre>unsigned long CDECL lame_get_num_samples(const lame_global_flags *);</pre>
     */
    int getNumSamples();

    /**
     * input sample rate in Hz.  default = 44100hz
     *
     * <pre>int CDECL lame_set_in_samplerate(lame_global_flags *, int);</pre>
     */
    void setInSamplerate(int rate);

    /**
     * input sample rate in Hz.  default = 44100hz
     *
     * <pre>int CDECL lame_get_in_samplerate(const lame_global_flags *);</pre>
     */
    int getInSamplerate();

    /**
     * number of channels in input stream. default=2
     *
     * <pre>int CDECL lame_set_num_channels(lame_global_flags *, int);</pre>
     */
    void setNumChannels(int channels);

    /**
     * number of channels in input stream. default=2
     *
     * <pre>int CDECL lame_get_num_channels(const lame_global_flags *);</pre>
     */
    int getNumChannels();

    /**
     * scale the input by this amount before encoding.  default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale(lame_global_flags *, float);</pre>
     */
    void setScale(float scale);

    /**
     * scale the input by this amount before encoding.  default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>float CDECL lame_get_scale(const lame_global_flags *);</pre>
     */
    float getScale();

    /**
     * scale the channel 0 (left) input by this amount before encoding.
     * default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale_left(lame_global_flags *, float);</pre>
     */
    void setScaleLeft(float scale);

    /**
     * scale the channel 0 (left) input by this amount before encoding.
     * default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>float CDECL lame_get_scale_left(const lame_global_flags *);</pre>
     */
    float getScaleLeft();

    /**
     * scale the channel 1 (right) input by this amount before encoding.
     * default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale_right(lame_global_flags *, float);</pre>
     */
    void setScaleRight(float scale);

    /**
     * scale the channel 1 (right) input by this amount before encoding.
     * default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>float CDECL lame_get_scale_right(const lame_global_flags *);</pre>
     */
    float getScaleRight();

    /**
     * output sample rate in Hz.  default = 0, which means Lame picks best value
     * based on the amount of compression.  MPEG only allows:
     * MPEG1    32, 44.1,   48khz
     * MPEG2    16, 22.05,  24
     * MPEG2.5   8, 11.025, 12
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_out_samplerate(lame_global_flags *, int);</pre>
     */
    void setOutSamplerate(int rate);

    /**
     * output sample rate in Hz.  default = 0, which means Lame picks best value
     * based on the amount of compression.  MPEG only allows:
     * MPEG1    32, 44.1,   48khz
     * MPEG2    16, 22.05,  24
     * MPEG2.5   8, 11.025, 12
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_get_out_samplerate(const lame_global_flags *);</pre>
     */
    int getOutSamplerate();

    /**
     * 1=cause Lame to collect data for an MP3 frame analyzer. default=0
     * <pre>int CDECL lame_set_analysis(lame_global_flags *, int);</pre>
     */
    void setAnalysis(boolean analysis);

    /**
     * 1=cause Lame to collect data for an MP3 frame analyzer. default=0
     *
     * <pre>int CDECL lame_get_analysis(const lame_global_flags *);</pre>
     */
    boolean isAnalysis();

    /**
     * 1 = write a Xing VBR header frame.
     * default = 1
     * this variable must have been added by a Hungarian notation Windows programmer :-)
     *
     * <pre>int CDECL lame_set_bWriteVbrTag(lame_global_flags *, int);</pre>
     */
    void setBWriteVbrTag(boolean vbrTag);

    /**
     * 1 = write a Xing VBR header frame.
     * default = 1
     * this variable must have been added by a Hungarian notation Windows programmer :-)
     *
     *
     * <pre>int CDECL lame_get_bWriteVbrTag(const lame_global_flags *);</pre>
     */
    boolean isBWriteVbrTag();

    /**
     * 1=decode only.  use lame/mpglib to convert mp3/ogg to wav.  default=0
     *
     * <pre>int CDECL lame_set_decode_only(lame_global_flags *, int);</pre>
     */
    void setDecodeOnly(boolean deconly);

    /**
     * 1=decode only.  use lame/mpglib to convert mp3/ogg to wav.  default=0
     *
     * <pre>int CDECL lame_get_decode_only(lame_global_flags *);</pre>
     */
    boolean isDecodeOnly();

    /**
     * 1=encode a Vorbis .ogg file.  default=0
     * DEPRECATED
     *
     * <pre>int CDECL lame_set_ogg(lame_global_flags*, int);</pre>
     */
    void setOgg(boolean ogg);

    /**
     * 1=encode a Vorbis .ogg file.  default=0
     * DEPRECATED
     *
     * <pre>int CDECL lame_get_ogg(const lame_global_flags *);</pre>
     */
    boolean isOgg();

    /**
     * internal algorithm selection.  True quality is determined by the bitrate
     * but this variable will affect quality by selecting expensive or cheap algorithms.
     * quality=0..9.  0=best (very slow).  9=worst.
     * recommended:  2     near-best quality, not too slow
     * 5     good quality, fast
     * 7     ok quality, really fast
     *
     * <pre>int CDECL lame_set_quality(lame_global_flags *, int);</pre>
     */
    void setQuality(int quality);

    /**
     * internal algorithm selection.  True quality is determined by the bitrate
     * but this variable will affect quality by selecting expensive or cheap algorithms.
     * quality=0..9.  0=best (very slow).  9=worst.
     * recommended:  2     near-best quality, not too slow
     * 5     good quality, fast
     * 7     ok quality, really fast
     *
     * <pre>int CDECL lame_get_quality(const lame_global_flags *);</pre>
     */
    int getQuality();

    /**
     * mode = 0,1,2,3 = stereo, jstereo, dual channel (not supported), mono
     * default: lame picks based on compression ration and input channels
     *
     * <pre>int CDECL lame_set_mode(lame_global_flags *, MPEG_mode);</pre>
     */
    void setMode(int mpeg_mode);

    /**
     * mode = 0,1,2,3 = stereo, jstereo, dual channel (not supported), mono
     * default: lame picks based on compression ration and input channels
     *
     * <pre>MPEG_mode CDECL lame_get_mode(const lame_global_flags *);</pre>
     */
    int getMode();

    /**
     * mode_automs.  Use a M/S mode with a switching threshold based on
     * compression ratio
     * DEPRECATED
     *
     * <pre>int CDECL lame_set_mode_automs(lame_global_flags *, int);</pre>
     */
    void setModeAutoms(int mode);

    /**
     * mode_automs.  Use a M/S mode with a switching threshold based on
     * compression ratio
     * DEPRECATED
     *
     * <pre>int CDECL lame_get_mode_automs(const lame_global_flags *);</pre>
     */
    int getModeAutoms();

    /**
     * force_ms.  Force M/S for all frames.  For testing only.
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_force_ms(lame_global_flags *, int);</pre>
     */
    void setForceMs(boolean force);

    /**
     * force_ms.  Force M/S for all frames.  For testing only.
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_force_ms(const lame_global_flags *);</pre>
     */
    boolean isForceMs();

    /**
     * use free_format?  default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_free_format(lame_global_flags *, int);</pre>
     */
    void setFreeFormat(boolean free);

    /**
     * use free_format?  default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_free_format(const lame_global_flags *);</pre>
     */
    boolean isFreeFormat();

    /**
     * perform ReplayGain analysis?  default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_findReplayGain(lame_global_flags *, int);</pre>
     */
    void setFindReplayGain(boolean repGain);

    /**
     * perform ReplayGain analysis?  default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_findReplayGain(const lame_global_flags *);</pre>
     */
    boolean isFindReplayGain();

    /**
     * decode on the fly. Search for the peak sample. If the ReplayGain
     * analysis is enabled then perform the analysis on the decoded data
     * stream. default = 0 (disabled)
     * NOTE: if this option is set the build-in decoder should not be used
     *
     * <pre>int CDECL lame_set_decode_on_the_fly(lame_global_flags *, int);</pre>
     */
    void setDecodeOnTheFly(boolean onthefly);

    /**
     * decode on the fly. Search for the peak sample. If the ReplayGain
     * analysis is enabled then perform the analysis on the decoded data
     * stream. default = 0 (disabled)
     * NOTE: if this option is set the build-in decoder should not be used
     *
     * <pre>int CDECL lame_get_decode_on_the_fly(const lame_global_flags *);</pre>
     */
    boolean isDecodeOnTheFly();

    /**
     * DEPRECATED: now does the same as lame_set_findReplayGain()
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_ReplayGain_input(lame_global_flags *, int);</pre>
     */
    void setReplayGainInput(boolean repGain);

    /**
     * DEPRECATED: now does the same as lame_set_findReplayGain()
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_ReplayGain_input(const lame_global_flags *);</pre>
     */
    boolean isReplayGainInput();

    /**
     * DEPRECATED: now does the same as
     * lame_set_decode_on_the_fly() && lame_set_findReplayGain()
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_ReplayGain_decode(lame_global_flags *, int);</pre>
     */
    void setReplayGainDecode(boolean repGain);

    /**
     * DEPRECATED: now does the same as
     * lame_set_decode_on_the_fly() && lame_set_findReplayGain()
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_ReplayGain_decode(const lame_global_flags *);</pre>
     */
    boolean isReplayGainDecode();

    /**
     * DEPRECATED: now does the same as lame_set_decode_on_the_fly()
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_findPeakSample(lame_global_flags *, int);</pre>
     */
    void setFindPeakSample(boolean findPeak);

    /**
     * DEPRECATED: now does the same as lame_set_decode_on_the_fly()
     * default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_findPeakSample(const lame_global_flags *);</pre>
     */
    boolean isFindPeakSample();

    /**
     * counter for gapless encoding
     *
     * <pre>int CDECL lame_set_nogap_total(lame_global_flags*, int);</pre>
     */
    void setNogapTotal(int total);

    /**
     * counter for gapless encoding
     *
     * <pre>int CDECL lame_get_nogap_total(const lame_global_flags*);</pre>
     */
    int getNogapTotal();

    /**
     * counter for gapless encoding
     *
     * <pre>int CDECL lame_set_nogap_currentindex(lame_global_flags* , int);</pre>
     */
    void setNogapCurrentIndex(int currindex);

    /**
     * counter for gapless encoding
     *
     * <pre>int CDECL lame_get_nogap_currentindex(const lame_global_flags*);</pre>
     */
    int getNogapCurrentindex();

    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     * void my_debugf(const char *format, va_list ap)
     * {
     * (void) vfprintf(stdout, format, ap);
     * }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_errorf(lame_global_flags *,
     * void (*func)(const char *, va_list));</pre>
     */
    void setErrorf(Lame.LameMsgCallback func);

    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     * void my_debugf(const char *format, va_list ap)
     * {
     * (void) vfprintf(stdout, format, ap);
     * }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_debugf(lame_global_flags *,
     * void (*func)(const char *, va_list));</pre>
     */
    void setDebugf(Lame.LameMsgCallback func);

    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     * void my_debugf(const char *format, va_list ap)
     * {
     * (void) vfprintf(stdout, format, ap);
     * }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_msgf(lame_global_flags *,
     * void (*func)(const char *, va_list));</pre>
     */
    void setMsgf(Lame.LameMsgCallback func);

    /**
     * set one of brate compression ratio.  default is compression ratio of 11.
     *
     * <pre>int CDECL lame_set_brate(lame_global_flags *, int);</pre>
     */
    void setBrate(int brate);

    /**
     * set one of brate compression ratio.  default is compression ratio of 11.
     *
     * <pre>int CDECL lame_get_brate(const lame_global_flags *);
     */
    int getBrate();

    /*
     * <pre>int CDECL lame_set_compression_ratio(lame_global_flags *, float);</pre>
     */
    void setCompressionRatio(float ratio);

    /*
     * <pre>float CDECL lame_get_compression_ratio(const lame_global_flags *);</pre>
     */
    float getCompressionRatio();

    /**
     * <pre>int CDECL lame_set_preset( lame_global_flags*  gfp, int );</pre>
     */
    void setPreset(int preset);

    /**
     * <pre>int CDECL lame_set_asm_optimizations( lame_global_flags*  gfp, int, int );</pre>
     */
    void setAsmOptimizations(int param1, int param2);

    /**
     * mark as copyright.  default=0
     *
     * <pre>int CDECL lame_set_copyright(lame_global_flags *, int);</pre>
     */
    void setCopyright(boolean cprght);

    /**
     * mark as copyright.  default=0
     *
     * <pre>int CDECL lame_get_copyright(const lame_global_flags *);</pre>
     */
    boolean isCopyright();

    /**
     * mark as original.  default=1
     *
     * <pre>int CDECL lame_set_original(lame_global_flags *, int);</pre>
     */
    void setOriginal(boolean original);

    /**
     * mark as original.  default=1
     *
     * <pre>int CDECL lame_get_original(const lame_global_flags *);</pre>
     */
    boolean isOriginal();

    /**
     * error_protection.  Use 2 bytes from each frame for CRC checksum. default=0
     *
     * <pre>int CDECL lame_set_error_protection(lame_global_flags *, int);</pre>
     */
    void setErrorProtection(boolean prot);

    /**
     * error_protection.  Use 2 bytes from each frame for CRC checksum. default=0
     *
     * <pre>int CDECL lame_get_error_protection(const lame_global_flags *);</pre>
     */
    boolean isErrorProtection();

    /**
     * padding_type. 0=pad no frames  1=pad all frames 2=adjust padding(default)
     *
     * <pre>int CDECL lame_set_padding_type(lame_global_flags *, Padding_type);</pre>
     */
    void setPaddingType(int padding_type);

    /**
     * padding_type. 0=pad no frames  1=pad all frames 2=adjust padding(default)
     *
     * <pre>Padding_type CDECL lame_get_padding_type(const lame_global_flags *);</pre>
     */
    int getPaddingType();

    /**
     * MP3 'private extension' bit  Meaningless.  default=0
     *
     * <pre>int CDECL lame_set_extension(lame_global_flags *, int);</pre>
     */
    void setExtension(int ext);

    /**
     * MP3 'private extension' bit  Meaningless.  default=0
     *
     * <pre>int CDECL lame_get_extension(const lame_global_flags *); </pre>
     */
    int getExtension();

    /**
     * enforce strict ISO compliance.  default=0
     *
     * <pre>int CDECL lame_set_strict_ISO(lame_global_flags *, int);</pre>
     */
    void setStrictISO(boolean iso);

    /**
     * enforce strict ISO compliance.  default=0
     *
     * <pre>int CDECL lame_get_strict_ISO(const lame_global_flags *);</pre>
     */
    boolean isStrictISO();

    /**
     * disable the bit reservoir. For testing only. default=0
     *
     * <pre>int CDECL lame_set_disable_reservoir(lame_global_flags *, int);</pre>
     */
    void setDisableReservoir(boolean preset);

    /**
     * disable the bit reservoir. For testing only. default=0
     *
     * <pre>int CDECL lame_get_disable_reservoir(const lame_global_flags *);</pre>
     */
    boolean isDisableReservoir();

    /**
     * select a different "best quantization" function. default=0
     *
     * <pre>int CDECL lame_set_quant_comp(lame_global_flags *, int);</pre>
     */
    void setQuantComp(int quant);

    /**
     * select a different "best quantization" function. default=0
     *
     * <pre>int CDECL lame_get_quant_comp(const lame_global_flags *);</pre>
     */
    int getQuantComp();

    /**
     * select a different "best quantization" function. default=0
     *
     * <pre>int CDECL lame_set_quant_comp_short(lame_global_flags *, int);</pre>
     */
    void setQuantCompShort(int quant);

    /**
     * select a different "best quantization" function. default=0
     *
     * <pre>int CDECL lame_get_quant_comp_short(const lame_global_flags *);</pre>
     */
    int getQuantCompShort();

    /**
     * <pre>int CDECL lame_set_experimentalX(lame_global_flags *, int);</pre>
     */
    void setExperimentalX(int x);

    /**
     * <pre>int CDECL lame_get_experimentalX(const lame_global_flags *);</pre>
     */
    int getExperimentalX();

    /**
     * another experimental option.  for testing only
     *
     * <pre>int CDECL lame_set_experimentalY(lame_global_flags *, int);</pre>
     */
    void setExperimentalY(int y);

    /**
     * another experimental option.  for testing only
     *
     * <pre>int CDECL lame_get_experimentalY(const lame_global_flags *);</pre>
     */
    int getExperimentalY();

    /**
     * another experimental option.  for testing only
     *
     * <pre>int CDECL lame_set_experimentalZ(lame_global_flags *, int);</pre>
     */
    void setExperimentalZ(int z);

    /**
     * another experimental option.  for testing only
     *
     * <pre>int CDECL lame_get_experimentalZ(const lame_global_flags *);</pre>
     */
    int getExperimentalZ();

    /**
     * Naoki's psycho acoustic model.  default=0
     *
     * <pre>int CDECL lame_set_exp_nspsytune(lame_global_flags *, int);</pre>
     */
    void setExpNspsytune(int tune);

    /**
     * Naoki's psycho acoustic model.  default=0
     *
     * <pre>int CDECL lame_get_exp_nspsytune(const lame_global_flags *);</pre>
     */
    int getExpNspsytune();

    /**
     * <pre>void CDECL lame_set_msfix(lame_global_flags *, double);</pre>
     */
    void setMsfix(double msfix);

    /**
     * <pre>float CDECL lame_get_msfix(const lame_global_flags *);</pre>
     */
    float getMsfix();

    /**
     * <pre>int lame_set_exp_nspsytune2_int( lame_global_flags*, int, int);</pre>
     */
    int setExpNspsytune2Int(int p1, int p2);

    /**
     * <pre>float lame_set_exp_nspsytune2_real( lame_global_flags*, int, float);</pre>
     */
    float setExpNspsytune2Real(int p1, float p2);

    /**
     * <pre>void * lame_set_exp_nspsytune2_pointer( lame_global_flags*, int, void *);</pre>
     */
    int setExpNspsytune2Pointer(int p1, int p2);

    /**
     * Types of VBR.  default = vbr_off = CBR
     *
     * <pre>int CDECL lame_set_VBR(lame_global_flags *, vbr_mode);</pre>
     */
    void setVBR(int vbr_mode);

    /**
     * Types of VBR.  default = vbr_off = CBR
     *
     * <pre>vbr_mode CDECL lame_get_VBR(const lame_global_flags *);</pre>
     */
    int getVBR();

    /**
     * VBR quality level.  0=highest  9=lowest
     *
     * <pre>int CDECL lame_set_VBR_q(lame_global_flags *, int);</pre>
     */
    void setVBRQ(int level);

    /**
     * VBR quality level.  0=highest  9=lowest
     *
     * <pre>int CDECL lame_get_VBR_q(const lame_global_flags *);</pre>
     */
    int getVBRq();

    /**
     * Ignored except for VBR=vbr_abr (ABR mode)
     *
     * <pre>int CDECL lame_set_VBR_mean_bitrate_kbps(lame_global_flags *, int);</pre>
     */
    void setVBRMeanBitrateKbps(int bitrate);

    /**
     * Ignored except for VBR=vbr_abr (ABR mode)
     *
     * <pre>int CDECL lame_get_VBR_mean_bitrate_kbps(const lame_global_flags *);</pre>
     */
    int getVBRMeanBitrateKbps();

    /**
     * <pre>int CDECL lame_set_VBR_min_bitrate_kbps(const lame_global_flags *);</pre>
     */
    void setVBRMinBitrateKbps(int bitrate);

    /**
     * <pre>int CDECL lame_get_VBR_min_bitrate_kbps(const lame_global_flags *);</pre>
     */
    int getVBRMinBitrateKbps();

    /**
     * <pre>int CDECL lame_set_VBR_max_bitrate_kbps(const lame_global_flags *);</pre>
     */
    void setVBRMaxBitrateKbps(int bitrate);

    /**
     * <pre>int CDECL lame_get_VBR_max_bitrate_kbps(const lame_global_flags *);</pre>
     */
    int getVBRMaxBitrateKbps();

    /**
     * 1=strictly enforce VBR_min_bitrate.  Normally it will be violated for
     * analog silence
     *
     * <pre>int CDECL lame_set_VBR_hard_min(lame_global_flags *, int);</pre>
     */
    void setVBRHardMin(boolean vbr);

    /**
     * 1=strictly enforce VBR_min_bitrate.  Normally it will be violated for
     * analog silence
     *
     * <pre>int CDECL lame_get_VBR_hard_min(const lame_global_flags *);</pre>
     */
    boolean isVBRHardMin();

    /**
     * for preset
     *
     * <pre>int CDECL lame_set_preset_expopts(lame_global_flags *, int);</pre>
     */
    void setPresetExpopts(int expopts);

    /**
     * freq in Hz to apply lowpass. Default = 0 = lame chooses.  -1 = disabled
     *
     * <pre>int CDECL lame_set_lowpassfreq(lame_global_flags *, int);</pre>
     */
    void setLowpassfreq(int freq);

    /**
     * freq in Hz to apply lowpass. Default = 0 = lame chooses.  -1 = disabled
     *
     * <pre>int CDECL lame_get_lowpassfreq(const lame_global_flags *);</pre>
     */
    int getLowpassfreq();

    /**
     * width of transition band, in Hz.  Default = one polyphase filter band
     *
     * <pre>int CDECL lame_set_lowpasswidth(lame_global_flags *, int);</pre>
     */
    void setLowpasswidth(int width);

    /**
     * width of transition band, in Hz.  Default = one polyphase filter band
     *
     * <pre>int CDECL lame_get_lowpasswidth(const lame_global_flags *);</pre>
     */
    int getLowpasswidth();

    /**
     * freq in Hz to apply highpass. Default = 0 = lame chooses.  -1 = disabled
     *
     * <pre>int CDECL lame_set_highpassfreq(lame_global_flags *, int);</pre>
     */
    void setHighpassfreq(int freq);

    /**
     * freq in Hz to apply highpass. Default = 0 = lame chooses.  -1 = disabled
     *
     * <pre>int CDECL lame_get_highpassfreq(const lame_global_flags *);</pre>
     */
    int getHighpassfreq();

    /**
     * width of transition band, in Hz.  Default = one polyphase filter band
     *
     * <pre>int CDECL lame_set_highpasswidth(lame_global_flags *, int);</pre>
     */
    void setHighpasswidth(int width);

    /**
     * width of transition band, in Hz.  Default = one polyphase filter band
     *
     * <pre>int CDECL lame_get_highpasswidth(const lame_global_flags *);</pre>
     */
    int getHighpasswidth();

    /**
     * only use ATH for masking
     *
     * <pre>int CDECL lame_set_ATHonly(lame_global_flags *, int);</pre>
     */
    void setATHonly(boolean athOnly);

    /**
     * only use ATH for masking
     *
     * <pre>int CDECL lame_get_ATHonly(const lame_global_flags *);</pre>
     */
    boolean isATHonly();

    /**
     * only use ATH for short blocks
     *
     * <pre>int CDECL lame_set_ATHshort(lame_global_flags *, int);</pre>
     */
    void setATHshort(boolean athShort);

    /**
     * only use ATH for short blocks
     *
     * <pre>int CDECL lame_get_ATHshort(const lame_global_flags *);</pre>
     */
    boolean isATHshort();

    /**
     * disable ATH
     *
     * <pre>int CDECL lame_set_noATH(lame_global_flags *, int);</pre>
     */
    void setNoATH(boolean noATH);

    /**
     * disable ATH
     *
     * <pre>int CDECL lame_get_noATH(const lame_global_flags *);</pre>
     */
    boolean isNoATH();

    /**
     * select ATH formula
     *
     * <pre>int CDECL lame_set_ATHtype(lame_global_flags *, int);</pre>
     */
    void setATHtype(int ATHType);

    /**
     * select ATH formula
     *
     * <pre>int CDECL lame_get_ATHtype(lame_global_flags *);</pre>
     */
    int getATHtype();

    /**
     * lower ATH by this many db
     *
     * <pre>int CDECL lame_set_ATHlower(lame_global_flags *, float);</pre>
     */
    void setATHlower(float ATHLower);

    /**
     * lower ATH by this many db
     *
     * <pre>floag CDECL lame_get_ATHlower(lame_global_flags *);</pre>
     */
    float getATHlower();

    /**
     * select ATH adaptive adjustment type
     *
     * <pre>int CDECL lame_set_athaa_type( lame_global_flags *, int);</pre>
     */
    void setAthaaType(int ATHadjust);

    /**
     * select ATH adaptive adjustment type
     *
     * <pre>int CDECL lame_get_athaa_type( const lame_global_flags *);</pre>
     */
    int getAthaaType();

    /**
     * select the loudness approximation used by the ATH adaptive auto-leveling
     *
     * <pre>int CDECL lame_set_athaa_loudapprox( lame_global_flags *, int);</pre>
     */
    void setAthaaLoudapprox(int loud);

    /**
     * select the loudness approximation used by the ATH adaptive auto-leveling
     *
     * <pre>int CDECL lame_get_athaa_loudapprox( const lame_global_flags *);</pre>
     */
    int getAthaaLoudapprox();

    /**
     * adjust (in dB) the point below which adaptive ATH level adjustment occurs
     *
     * <pre>int CDECL lame_set_athaa_sensitivity( lame_global_flags *, float);</pre>
     */
    void setAthaaSensitivity(float sen);

    /**
     * adjust (in dB) the point below which adaptive ATH level adjustment occurs
     *
     * <pre>float CDECL lame_get_athaa_sensitivity( const lame_global_flags* );</pre>
     */
    float getAthaaSensitivity();

    /**
     * predictability limit (ISO tonality formula)
     *
     * <pre>int CDECL lame_set_cwlimit(lame_global_flags *, int);</pre>
     */
    void setCwlimit(int limit);

    /**
     * predictability limit (ISO tonality formula)
     *
     * <pre>int CDECL lame_get_cwlimit(const lame_global_flags *);</pre>
     */
    int getCwlimit();

    /**
     * allow blocktypes to differ between channels?
     * default: 0 for jstereo, 1 for stereo
     *
     * <pre>int CDECL lame_set_allow_diff_short(lame_global_flags *, int);</pre>
     */
    void setAllowDiffShort(int allow);

    /**
     * allow blocktypes to differ between channels?
     * default: 0 for jstereo, 1 for stereo
     *
     * <pre>int CDECL lame_get_allow_diff_short(const lame_global_flags *);</pre>
     */
    int getAllowDiffShort();

    /**
     * use temporal masking effect (default = 1)
     *
     * <pre>int CDECL lame_set_useTemporal(lame_global_flags *, int);</pre>
     */
    void setUseTemporal(boolean maskEffect);

    /**
     * use temporal masking effect (default = 1)
     *
     * <pre>int CDECL lame_get_useTemporal(const lame_global_flags *);</pre>
     */
    boolean isUseTemporal();

    /**
     * use temporal masking effect (default = 1)
     *
     * <pre>int CDECL lame_set_interChRatio(lame_global_flags *, float);</pre>
     */
    void setInterChRatio(float interChRatio);

    /**
     * use temporal masking effect (default = 1)
     *
     * <pre>float CDECL lame_get_interChRatio(const lame_global_flags *);</pre>
     */
    float getInterChRatio();

    /**
     * disable short blocks
     *
     * <pre>int CDECL lame_set_no_short_blocks(lame_global_flags *, int);</pre>
     */
    void setNoShortBlocks(boolean noShort);

    /**
     * disable short blocks
     *
     * <pre>int CDECL lame_get_no_short_blocks(const lame_global_flags *);</pre>
     */
    boolean isNoShortBlocks();

    /**
     * force short blocks
     *
     * <pre>int CDECL lame_set_force_short_blocks(lame_global_flags *, int);</pre>
     */
    void setForceShortBlocks(boolean force);

    /**
     * force short blocks
     *
     * <pre>int CDECL lame_get_force_short_blocks(const lame_global_flags *);</pre>
     */
    boolean isForceShortBlocks();

    /**
     * Input PCM is emphased PCM (for instance from one of the rarely
     * emphased CDs), it is STRONGLY not recommended to use this, because
     * psycho does not take it into account, and last but not least many decoders
     * ignore these bits
     *
     * <pre>int CDECL lame_set_emphasis(lame_global_flags *, int);</pre>
     */
    void setEmphasis(boolean emph);

    /**
     * Input PCM is emphased PCM (for instance from one of the rarely
     * emphased CDs), it is STRONGLY not recommended to use this, because
     * psycho does not take it into account, and last but not least many decoders
     * ignore these bits
     *
     * <pre>int CDECL lame_get_emphasis(const lame_global_flags *);</pre>
     */
    boolean isEmphasis();

    /**
     * version  0=MPEG-2  1=MPEG-1  (2=MPEG-2.5)
     *
     * <pre>int CDECL lame_get_version(const lame_global_flags *);</pre>
     */
    int getVersion();

    /**
     * encoder delay
     *
     * <pre>int CDECL lame_get_encoder_delay(const lame_global_flags *);</pre>
     */
    int getEncoderDelay();

    /**
     * padding appended to the input to make sure decoder can fully decode
     * all input.  Note that this value can only be calculated during the
     * call to lame_encoder_flush().  Before lame_encoder_flush() has
     * been called, the value of encoder_padding = 0.
     *
     * <pre>int CDECL lame_get_encoder_padding(const lame_global_flags *);  </pre>
     */
    int getEncoderPadding();

    /**
     * size of MPEG frame
     *
     * <pre>int CDECL lame_get_framesize(const lame_global_flags *);</pre>
     */
    int getFramesize();

    /**
     * number of PCM samples buffered, but not yet encoded to mp3 data.
     *
     * <pre>int CDECL lame_get_mf_samples_to_encode( const lame_global_flags*  gfp );</pre>
     */
    int getMfSamplesToEncode();

    /**
     * size (bytes) of mp3 data buffered, but not yet encoded.
     * this is the number of bytes which would be output by a call to
     * lame_encode_flush_nogap.  NOTE: lame_encode_flush() will return
     * more bytes than this because it will encode the reamining buffered
     * PCM samples before flushing the mp3 buffers.
     *
     * <pre>int CDECL lame_get_size_mp3buffer( const lame_global_flags*  gfp );</pre>
     */
    int getSizeMP3buffer();

    /**
     * number of frames encoded so far
     *
     * <pre>int CDECL lame_get_frameNum(const lame_global_flags *);</pre>
     */
    int getFrameNum();

    /**
     * lame's estimate of the total number of frames to be encoded
     * only valid if calling program set num_samples
     *
     * <pre>int CDECL lame_get_totalframes(const lame_global_flags *);</pre>
     */
    int getTotalframes();

    /**
     * RadioGain value. Multiplied by 10 and rounded to the nearest.
     *
     * <pre>int CDECL lame_get_RadioGain(const lame_global_flags *);</pre>
     */
    int getRadioGain();

    /**
     * AudiophileGain value. Multipled by 10 and rounded to the nearest.
     *
     * <pre>int CDECL lame_get_AudiophileGain(const lame_global_flags *);</pre>
     */
    int getAudiophileGain();

    /**
     * the peak sample
     *
     * <pre>float CDECL lame_get_PeakSample(const lame_global_flags *);</pre>
     */
    float getPeakSample();

    /**
     * Gain change required for preventing clipping. The value is correct only if
     * peak sample searching was enabled. If negative then the waveform
     * already does not clip. The value is multiplied by 10 and rounded up.
     *
     * <pre>int CDECL lame_get_noclipGainChange(const lame_global_flags *);</pre>
     */
    int getNoclipGainChange();

    /**
     * user-specified scale factor required for preventing clipping. Value is
     * correct only if peak sample searching was enabled and no user-specified
     * scaling was performed. If negative then either the waveform already does
     * not clip or the value cannot be determined
     *
     * <pre>float CDECL lame_get_noclipScale(const lame_global_flags *);</pre>
     */
    float getNoclipScale();

    /**
     * OPTIONAL:
     * get the version number, in a string. of the form:
     * "3.63 (beta)" or just "3.63".
     *
     * <pre>const char*  CDECL get_lame_version( void );</pre>
     */
    String getLameVersion();

    /**
     * <pre>const char*  CDECL get_lame_short_version( void );</pre>
     */
    String getLameShortVersion();

    /**
     * <pre>const char*  CDECL get_lame_very_short_version( void );</pre>
     */
    String getLameVeryShortVersion();

    /**
     * <pre>const char*  CDECL get_psy_version( void );</pre>
     */
    String getPsyVersion();

    /**
     * <pre>const char*  CDECL get_lame_url( void );</pre>
     */
    String getLameUrl();

    /*
     * OPTIONAL:
     * get the version numbers in numerical form.
     *
     * <pre>void CDECL get_lame_version_numerical ( lame_version_t *const );</pre>
     */
    void getLameVersionNumerical(LameVersion version);

    /*
     * OPTIONAL:
     * print internal lame configuration to message handler
     *
     * <pre>void CDECL lame_print_config(const lame_global_flags*  gfp);</pre>
     */
    void printConfig();

    /**
     * <pre>void CDECL lame_print_internals( const lame_global_flags *gfp);</pre>
     */
    void printInternals();

    /*
     * OPTIONAL:    some simple statistics
     * a bitrate histogram to visualize the distribution of used frame sizes
     * a stereo mode histogram to visualize the distribution of used stereo
     *   modes, useful in joint-stereo mode only
     *   0: LR    left-right encoded
     *   1: LR-I  left-right and intensity encoded (currently not supported)
     *   2: MS    mid-side encoded
     *   3: MS-I  mid-side and intensity encoded (currently not supported)
     *
     * attention: don't call them after lame_encode_finish
     * suggested: lame_encode_flush -> lame_*_hist -> lame_close
     *
     * <pre>
     *    void CDECL lame_bitrate_hist( 
     *           const lame_global_flags *const gfp,
     *           int   bitrate_count[14] );
     * </pre>
     */
    void bitrateHist(int[] bitrate_count);

    /**
     * See {@link #bitrateHist(int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_kbps(
     * const lame_global_flags *const gfp,
     * int bitrate_kbps [14] );
     * </pre>
     */
    void bitrateKbps(int[] bitrate_kbps);

    /**
     * See {@link #bitrateHist(int[])}
     *
     * <pre>
     * void CDECL lame_stereo_mode_hist(
     * const lame_global_flags *const gfp,
     * int stereo_mode_count[4] );
     * </pre>
     */
    void stereoModeHist(int[] stereo_mode_count);

    /**
     * See {@link #bitrateHist(int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_stereo_mode_hist (
     * const lame_global_flags * const gfp,
     * int  bitrate_stmode_count [14] [4] );
     * </pre>
     */
    void bitrateStereoModeHist(int[][] bitrate_stmode_count);

    /**
     * See {@link #bitrateHist(int[])}
     *
     * <pre>
     * void CDECL lame_block_type_hist (
     * const lame_global_flags * const gfp,
     * int btype_count[6] );
     * </pre>
     */
    void blockTypeHist(int[] btype_count);

    /**
     * See {@link #bitrateHist(int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_block_type_hist (
     * const lame_global_flags * const gfp,
     * int bitrate_btype_count[14][6] );
     * </pre>
     */
    void bitrateBlockTypeHist(int[][] bitrate_btype_count);
}
