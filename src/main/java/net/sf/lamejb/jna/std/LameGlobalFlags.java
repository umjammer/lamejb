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

import java.util.List;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;


/**
 *
 */
public class LameGlobalFlags extends Structure {

//	typedef enum short_block_e {
//	    short_block_not_set = -1, // allow LAME to decide
//	    short_block_allowed = 0, // LAME may use them, even different block types for L/R
//	    short_block_coupled, // LAME may use them, but always same block types in L/R
//	    short_block_dispensed, // LAME will not use short blocks, long blocks only
//	    short_block_forced  // LAME will not use long blocks, short blocks only
//	} short_block_t;
//
//	  // Control Parameters set by User.  These parameters are here for
//    // backwards compatibility with the old, non-shared lib API.
//	  // Please use the lame_set_variablename() functions below
//
//	struct lame_global_struct {
//	    // input description
//	    unsigned long num_samples; // number of samples. default=2^32-1
//	    int     num_channels;    // input number of channels. default=2
//	    int     in_samplerate;   // input_samp_rate in Hz. default=44.1 kHz
//	    int     out_samplerate;  // output_samp_rate.
//	                                default: LAME picks best value
//	                                at least not used for MP3 decoding:
//	                                Remember 44.1 kHz MP3s and AC97
//	    float   scale;           // scale input by this amount before encoding
//	                                at least not used for MP3 decoding
//	    float   scale_left;      // scale input of channel 0 (left) by this
//	                                amount before encoding
//	    float   scale_right;     // scale input of channel 1 (right) by this
//	                                amount before encoding
//
//	    // general control params
//	    int     analysis;        // collect data for a MP3 frame analyzer?
//	    int     bWriteVbrTag;    // add Xing VBR tag?
//	    int     decode_only;     // use lame/mpglib to convert mp3 to wav
//	    int     quality;         // quality setting 0=best,  9=worst  default=5
//	    MPEG_mode mode;          // see enum in lame.h
//	                                default = LAME picks best value
//	    int     force_ms;        // force M/S mode.  requires mode=1
//	    int     free_format;     // use free format? default=0
//	    int     findReplayGain;  // find the RG value? default=0
//	    int     decode_on_the_fly; // decode on the fly? default=0
//	    int     write_id3tag_automatic; // 1 (default) writes ID3 tags, 0 not
//
//
//	    // set either brate>0  or compression_ratio>0, LAME will compute
//	    // the value of the variable not set.
//	    // Default is compression_ratio = 11.025
//
//	    int     brate;           // bitrate
//	    float   compression_ratio; // sizeof(wav file)/sizeof(mp3 file)
//
//
//	    // frame params
//	    int     copyright;       // mark as copyright. default=0
//	    int     original;        // mark as original. default=1
//	    int     extension;       // the MP3 'private extension' bit.
//	                                Meaningless
//	    int     emphasis;        // Input PCM is emphased PCM (for
//	                                instance from one of the rarely
//	                                emphased CDs), it is STRONGLY not
//	                                recommended to use this, because
//	                                psycho does not take it into account,
//	                                and last but not least many decoders
//	                                don't care about these bits
//	    int     error_protection; // use 2 bytes per frame for a CRC
//	                                 checksum. default=0
//	    int     strict_ISO;      // enforce ISO spec as much as possible
//
//	    int     disable_reservoir; // use bit reservoir?
//
//	    // quantization/noise shaping
//	    int     quant_comp;
//	    int     quant_comp_short;
//	    int     experimentalY;
//	    int     experimentalZ;
//	    int     exp_nspsytune;
//
//	    int     preset;
//
//	    // VBR control
//	    vbr_mode VBR;
//	    float   VBR_q_frac;      // Range [0,...,1[
//	    int     VBR_q;           // Range [0,...,9]
//	    int     VBR_mean_bitrate_kbps;
//	    int     VBR_min_bitrate_kbps;
//	    int     VBR_max_bitrate_kbps;
//	    int     VBR_hard_min;    // strictly enforce VBR_min_bitrate
//	                                normaly, it will be violated for analog
//	                                silence
//
//
//	    // resampling and filtering
//	    int     lowpassfreq;     // freq in Hz. 0=lame choses.
//	                                -1=no filter
//	    int     highpassfreq;    // freq in Hz. 0=lame choses.
//	                                -1=no filter
//	    int     lowpasswidth;    // freq width of filter, in Hz
//	                                (default=15%)
//	    int     highpasswidth;   // freq width of filter, in Hz
//	                                (default=15%)
//
//
//
//	    // psycho acoustics and other arguments which you should not change
//	    // unless you know what you are doing
//
//	    float   maskingadjust;
//	    float   maskingadjust_short;
//	    int     ATHonly;         // only use ATH
//	    int     ATHshort;        // only use ATH for short blocks
//	    int     noATH;           // disable ATH
//	    int     ATHtype;         // select ATH formula
//	    float   ATHcurve;        // change ATH formula 4 shape
//	    float   ATHlower;        // lower ATH by this many db
//	    int     athaa_type;      // select ATH auto-adjust scheme
//	    int     athaa_loudapprox; // select ATH auto-adjust loudness calc
//	    float   athaa_sensitivity; // dB, tune active region of auto-level
//	    short_block_t short_blocks;
//	    int     useTemporal;     // use temporal masking effect
//	    float   interChRatio;
//	    float   msfix;           // Naoki's adjustment of Mid/Side maskings
//
//	    int     tune;            // 0 off, 1 on
//	    float   tune_value_a;    // used to pass values for debugging and stuff
//
//	    struct {
//	        void    (*msgf) (const char *format, va_list ap);
//	        void    (*debugf) (const char *format, va_list ap);
//	        void    (*errorf) (const char *format, va_list ap);
//	    } report;
//
//
//	    // internal variables, do not set...
//	    // provided because they may be of use to calling application
//
//	    int     version;         // 0=MPEG-2/2.5  1=MPEG-1
//	    int     encoder_delay;
//	    int     encoder_padding; // number of samples of padding appended to input
//	    int     framesize;
//	    int     frameNum;        // number of frames encoded
//	    int     lame_allocated_gfp; // is this struct owned by calling
//	                                   program or lame?
//
//
//
//	  // more internal variables are stored in this structure:
//
//	    lame_internal_flags *internal_flags;
//
//
//	    struct {
//	        int     mmx;
//	        int     amd3dnow;
//	        int     sse;
//
//	    } asm_optimizations;
//	};

    public NativeLong num_samples;
    public int num_channels;
    public int in_samplerate;
    public int out_samplerate;
    public float scale;
    public float scale_left;
    public float scale_right;
    public int analysis;
    public int bWriteVbrTag;
    public int decode_only;
    public int quality;
    public int mode;                        // MpegMode constant values;
    public int force_ms;
    public int free_format;
    public int findReplayGain;
    public int decode_on_the_fly;
    public int brate;
    public float compression_ratio;
    public int copyright;
    public int original;
    public int extension;
    public int emphasis;
    public int error_protection;
    public int strict_ISO;
    public int disable_reservoir;
    public int quant_comp;
    public int quant_comp_short;
    public int experimentalY;
    public int exp_nspsytune;
    public int preset;
    public int VBR;                        // VbrMode constant values;
    public int VBR_q;
    public int VBR_mean_bitrate_kbps;
    public int VBR_min_bitrate_kbps;
    public int VBR_max_bitrate_kbps;
    public int VBR_hard_min;
    public int lowpassfreq;
    public int highpassfreq;
    public int lowpasswidth;
    public int highpasswidth;
    public int psymodel;
    public float maskingadjust;
    public float maskingadjust_short;
    public int ATHonly;
    public int ATHshort;
    public int noATH;
    public int ATHtype;
    public float ATHcurve;
    public float ATHlower;
    public int athaa_type;
    public int athaa_loudapprox;
    public float athaa_sensitivity;
    public int cwlimit;
    //short_block_t 	short_blocks
    public int short_blocks;
    public int useTemporal;
    public float interChRatio;
    public float msfix;
    public int tune;
    public float tune_value_a;
    public int quantization_type;
    //struct {
    //   void(*   msgf )(const char *format, va_list ap)
    //   void(*   debugf )(const char *format, va_list ap)
    //   void(*   errorf )(const char *format, va_list ap)
    //} 	report
    public Pointer report;
    public int version;
    public int encoder_delay;
    public int encoder_padding;
    public int framesize;
    public int frameNum;
    public int lame_allocated_gfp;
    //lame_internal_flags * 	internal_flags
    public Pointer internal_flags;
    public AsmOptimizations asm_optimizations;

    public LameGlobalFlags() {}

    @Override
    protected List<String> getFieldOrder() {
        return List.of(
                "num_samples", "num_channels", "in_samplerate", "out_samplerate", "scale", "scale_left", "scale_right", "analysis", "bWriteVbrTag", "decode_only", "quality", "mode", "force_ms", "free_format", "findReplayGain", "decode_on_the_fly", "brate", "compression_ratio", "copyright", "original", "extension", "emphasis", "error_protection", "strict_ISO", "disable_reservoir", "quant_comp", "quant_comp_short", "experimentalY", "exp_nspsytune", "preset", "VBR", "VBR_q", "VBR_mean_bitrate_kbps", "VBR_min_bitrate_kbps", "VBR_max_bitrate_kbps", "VBR_hard_min", "lowpassfreq", "highpassfreq", "lowpasswidth", "highpasswidth", "psymodel", "maskingadjust", "maskingadjust_short", "ATHonly", "ATHshort", "noATH", "ATHtype", "ATHcurve", "ATHlower", "athaa_type", "athaa_loudapprox", "athaa_sensitivity", "cwlimit", "short_blocks", "useTemporal", "interChRatio", "msfix", "tune", "tune_value_a", "quantization_type", "report", "version", "encoder_delay", "encoder_padding", "framesize", "frameNum", "lame_allocated_gfp", "internal_flags", "asm_optimizations"
        );
    }
}
