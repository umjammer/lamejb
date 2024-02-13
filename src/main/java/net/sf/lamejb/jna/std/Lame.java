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


import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;


/**
 * Wrapper for the functions declared in lame.h (C header file)
 * 
 */
public interface Lame extends Library
{

    Lame INSTANCE = 
    	(Lame)Native.loadLibrary((Platform.isWindows() ? "libmp3lame" : "mp3lame"), Lame.class);


	//void (*func)(const char *, va_list)
	public interface LameMsgCallback extends Callback 
	{
		void callback(String format, String params);
	}
	
    
	
    /**
     * REQUIRED:
     * initialize the encoder.  sets default for all encoder parameters,
     * returns NULL if some malloc()'s failed
     * otherwise returns pointer to structure needed for all future
     * API calls.
     *
     * <pre>lame_global_flags * CDECL lame_init(void);</pre>
     */    
    LameGlobalFlags lame_init();
    
    /**
     * obsolete version
     *
     * <pre>int CDECL lame_init_old(lame_global_flags *);</pre>   
     */
    int lame_init_old(LameGlobalFlags flags);
    
    /**
     * number of samples.  default = 2^32-1   
     *
     * <pre>int CDECL lame_set_num_samples(lame_global_flags *, unsigned long);</pre> 
     */
    int lame_set_num_samples(LameGlobalFlags flags, int samples);


    /**
     * number of samples.  default = 2^32-1      
     *
     * <pre>unsigned long CDECL lame_get_num_samples(const lame_global_flags *);</pre>
     */    
    int lame_get_num_samples(LameGlobalFlags flags);

    
    /**
     * input sample rate in Hz.  default = 44100hz 
     *
       <pre>int CDECL lame_set_in_samplerate(lame_global_flags *, int);</pre>
     */
    int lame_set_in_samplerate(LameGlobalFlags flags, int rate);

    /**
     * input sample rate in Hz.  default = 44100hz      
     *
        <pre>int CDECL lame_get_in_samplerate(const lame_global_flags *);</pre>    
     */
    int lame_get_in_samplerate(LameGlobalFlags flags);
    
    /** 
     * number of channels in input stream. default=2  
     *
     * <pre>int CDECL lame_set_num_channels(lame_global_flags *, int);</pre>
     */
    int lame_set_num_channels(LameGlobalFlags flags, int channels);
    
    /** 
     * number of channels in input stream. default=2  
     *
     * <pre>int CDECL lame_get_num_channels(const lame_global_flags *);</pre>
     */    
    int lame_get_num_channels(LameGlobalFlags flags);

    /**
     * scale the input by this amount before encoding.  default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale(lame_global_flags *, float);</pre>
     */
    int lame_set_scale(LameGlobalFlags flags, float scale);

    /**
     * scale the input by this amount before encoding.  default=0 (disabled)
     * (not used by decoding routines)     
     *
     * <pre>float CDECL lame_get_scale(const lame_global_flags *);</pre>
     */    
    float lame_get_scale(LameGlobalFlags flags);
   
    /**
      scale the channel 0 (left) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale_left(lame_global_flags *, float);</pre>
    */
    int lame_set_scale_left(LameGlobalFlags flags, float scale);
    
    /**
      scale the channel 0 (left) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)
     *     
     * <pre>float CDECL lame_get_scale_left(const lame_global_flags *);</pre>
    */    
    float lame_get_scale_left(LameGlobalFlags flags);

    /**
     * scale the channel 1 (right) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale_right(lame_global_flags *, float);</pre>
    */
    int lame_set_scale_right(LameGlobalFlags flags, float scale);

    /**
     * scale the channel 1 (right) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)     
     *
     * <pre>float CDECL lame_get_scale_right(const lame_global_flags *);</pre> 
    */    
    float lame_get_scale_right(LameGlobalFlags flags);    

    /**
     *      output sample rate in Hz.  default = 0, which means Lame picks best value
     *      based on the amount of compression.  MPEG only allows:
     *      MPEG1    32, 44.1,   48khz
     *      MPEG2    16, 22.05,  24
     *      MPEG2.5   8, 11.025, 12
     *      (not used by decoding routines)
     * 
     * <pre>int CDECL lame_set_out_samplerate(lame_global_flags *, int);</pre>
     */
    int lame_set_out_samplerate(LameGlobalFlags flags, int rate);

    /**
     *      output sample rate in Hz.  default = 0, which means Lame picks best value
     *      based on the amount of compression.  MPEG only allows:
     *      MPEG1    32, 44.1,   48khz
     *      MPEG2    16, 22.05,  24
     *      MPEG2.5   8, 11.025, 12
     *      (not used by decoding routines)     
     * 
     * <pre>int CDECL lame_get_out_samplerate(const lame_global_flags *);</pre>
     */    
    int lame_get_out_samplerate(LameGlobalFlags flags);
   
    /**
     * 1=cause Lame to collect data for an MP3 frame analyzer. default=0 
     * <pre>int CDECL lame_set_analysis(lame_global_flags *, int);</pre>
     */
    int lame_set_analysis(LameGlobalFlags flags, int analysis);

    /**
     * 1=cause Lame to collect data for an MP3 frame analyzer. default=0
     *    
     * <pre>int CDECL lame_get_analysis(const lame_global_flags *);</pre>
     */
    int lame_get_analysis(LameGlobalFlags flags);   

    /**
      1 = write a Xing VBR header frame.
      default = 1
      this variable must have been added by a Hungarian notation Windows programmer :-)
     *
     * <pre>int CDECL lame_set_bWriteVbrTag(lame_global_flags *, int);</pre>
    */
    int lame_set_bWriteVbrTag(LameGlobalFlags flags, int vbrTag);

    /**
      1 = write a Xing VBR header frame.
      default = 1
      this variable must have been added by a Hungarian notation Windows programmer :-)
     
     *
     * <pre>int CDECL lame_get_bWriteVbrTag(const lame_global_flags *);</pre>   
    */     
    int lame_get_bWriteVbrTag(LameGlobalFlags flags);

    /** 
     * 1=decode only.  use lame/mpglib to convert mp3/ogg to wav.  default=0 
     *
     * <pre>int CDECL lame_set_decode_only(lame_global_flags *, int);</pre>
     */
    int lame_set_decode_only(LameGlobalFlags flags, int deconly);
    
    /** 
     * 1=decode only.  use lame/mpglib to convert mp3/ogg to wav.  default=0 
     *
     * <pre>int CDECL lame_get_decode_only(lame_global_flags *);</pre>
     */    
    int lame_get_decode_only(LameGlobalFlags flags);

    /**
     * 1=encode a Vorbis .ogg file.  default=0 
     * DEPRECATED
     *
     * <pre>int CDECL lame_set_ogg(lame_global_flags*, int);</pre>
     */
    int lame_set_ogg(LameGlobalFlags flags, int ogg);

    /**
     * 1=encode a Vorbis .ogg file.  default=0 
     * DEPRECATED
     *
     * <pre>int CDECL lame_get_ogg(const lame_global_flags *);</pre>
     */    
    int lame_get_ogg(LameGlobalFlags flags);
    
    /**
      internal algorithm selection.  True quality is determined by the bitrate
      but this variable will effect quality by selecting expensive or cheap algorithms.
      quality=0..9.  0=best (very slow).  9=worst.
      recommended:  2     near-best quality, not too slow
                    5     good quality, fast
                    7     ok quality, really fast
     *
     * <pre>int CDECL lame_set_quality(lame_global_flags *, int);</pre>
    */
    int lame_set_quality(LameGlobalFlags flags, int quality);
    
    /**
      internal algorithm selection.  True quality is determined by the bitrate
      but this variable will effect quality by selecting expensive or cheap algorithms.
      quality=0..9.  0=best (very slow).  9=worst.
      recommended:  2     near-best quality, not too slow
                    5     good quality, fast
                    7     ok quality, really fast     
     *
     * <pre>int CDECL lame_get_quality(const lame_global_flags *);</pre>
    */    
    int lame_get_quality(LameGlobalFlags flags);

    
    /**
      mode = 0,1,2,3 = stereo, jstereo, dual channel (not supported), mono
      default: lame picks based on compression ration and input channels
     *
     * <pre>int CDECL lame_set_mode(lame_global_flags *, MPEG_mode);</pre>
    */
    int lame_set_mode(LameGlobalFlags flags, int MPEG_mode);

    
    /**
      mode = 0,1,2,3 = stereo, jstereo, dual channel (not supported), mono
      default: lame picks based on compression ration and input channels     
     *
     * <pre>MPEG_mode CDECL lame_get_mode(const lame_global_flags *);</pre>  
    */    
    int lame_get_mode(LameGlobalFlags flags);   
    
    /**
      mode_automs.  Use a M/S mode with a switching threshold based on
      compression ratio
      DEPRECATED
     *
     * <pre>int CDECL lame_set_mode_automs(lame_global_flags *, int);</pre>
    */
    int lame_set_mode_automs(LameGlobalFlags flags, int mode);
    
    /**
      mode_automs.  Use a M/S mode with a switching threshold based on
      compression ratio
      DEPRECATED
     *
     * <pre>int CDECL lame_get_mode_automs(const lame_global_flags *);</pre>
    */    
    int lame_get_mode_automs(LameGlobalFlags flags);    
    
    /**
      force_ms.  Force M/S for all frames.  For testing only.
      default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_force_ms(lame_global_flags *, int);</pre>
    */
    int lame_set_force_ms(LameGlobalFlags flags, int force);
    
    /**
      force_ms.  Force M/S for all frames.  For testing only.
      default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_force_ms(const lame_global_flags *);</pre>
    */    
    int lame_get_force_ms(LameGlobalFlags flags);    
    
    /**
     * use free_format?  default = 0 (disabled) 
     
       <pre>int CDECL lame_set_free_format(lame_global_flags *, int);</pre>
     */
    int lame_set_free_format(LameGlobalFlags flags, int free);
    
    /**
     * use free_format?  default = 0 (disabled) 
     
       <pre>int CDECL lame_get_free_format(const lame_global_flags *);</pre>
     */    
    int lame_get_free_format(LameGlobalFlags flags);   
   
    /**
     * perform ReplayGain analysis?  default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_findReplayGain(lame_global_flags *, int);</pre>
     */
    int lame_set_findReplayGain(LameGlobalFlags flags, int repGain);
   
    /**
     * perform ReplayGain analysis?  default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_findReplayGain(const lame_global_flags *);</pre>
     */    
    int lame_get_findReplayGain(LameGlobalFlags flags);

    /**
     * decode on the fly. Search for the peak sample. If the ReplayGain
     * analysis is enabled then perform the analysis on the decoded data
     * stream. default = 0 (disabled) 
     * NOTE: if this option is set the build-in decoder should not be used 
     
     * <pre>int CDECL lame_set_decode_on_the_fly(lame_global_flags *, int);</pre>
     */
    int lame_set_decode_on_the_fly(LameGlobalFlags flags, int onthefly);    
    
    /**
     * decode on the fly. Search for the peak sample. If the ReplayGain
     * analysis is enabled then perform the analysis on the decoded data
     * stream. default = 0 (disabled) 
     * NOTE: if this option is set the build-in decoder should not be used 
     
     * <pre>int CDECL lame_get_decode_on_the_fly(const lame_global_flags *);</pre>
     */    
    int lame_get_decode_on_the_fly(LameGlobalFlags flags);

   
    /**
     * DEPRECATED: now does the same as lame_set_findReplayGain() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_ReplayGain_input(lame_global_flags *, int);</pre>
     */
    int lame_set_ReplayGain_input(LameGlobalFlags flags, int repGain);  
 
    /**
     * DEPRECATED: now does the same as lame_set_findReplayGain() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_ReplayGain_input(const lame_global_flags *);</pre>
     */    
    int lame_get_ReplayGain_input(LameGlobalFlags flags);   

    /**
     * DEPRECATED: now does the same as 
       lame_set_decode_on_the_fly() && lame_set_findReplayGain()
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_ReplayGain_decode(lame_global_flags *, int);</pre>
     */
    int lame_set_ReplayGain_decode(LameGlobalFlags flags, int repGain);
 
    /**
     * DEPRECATED: now does the same as 
       lame_set_decode_on_the_fly() && lame_set_findReplayGain()
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_ReplayGain_decode(const lame_global_flags *);</pre>
     */    
    int lame_get_ReplayGain_decode(LameGlobalFlags flags);  


    /**
     * DEPRECATED: now does the same as lame_set_decode_on_the_fly() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_findPeakSample(lame_global_flags *, int);</pre>
     */
    int lame_set_findPeakSample(LameGlobalFlags flags, int findPeak);

    
    /**
     * DEPRECATED: now does the same as lame_set_decode_on_the_fly() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_findPeakSample(const lame_global_flags *);</pre>
     */    
    int lame_get_findPeakSample(LameGlobalFlags flags);   
 
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_set_nogap_total(lame_global_flags*, int);</pre>
     */
    int lame_set_nogap_total(LameGlobalFlags flags, int total);

    
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_get_nogap_total(const lame_global_flags*);</pre>
     */    
    int lame_get_nogap_total(LameGlobalFlags flags);

    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_set_nogap_currentindex(lame_global_flags* , int);</pre>
     */    
    int lame_set_nogap_currentindex(LameGlobalFlags flags, int currindex);
    
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_get_nogap_currentindex(const lame_global_flags*);</pre>
     */    
    int lame_get_nogap_currentindex(LameGlobalFlags flags);    
    
    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     *   void my_debugf(const char *format, va_list ap)
     *   {
     *       (void) vfprintf(stdout, format, ap);
     *   }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_errorf(lame_global_flags *,
                              void (*func)(const char *, va_list));</pre>
     */
    int lame_set_errorf(LameGlobalFlags flags,LameMsgCallback func);   
    
    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     *   void my_debugf(const char *format, va_list ap)
     *   {
     *       (void) vfprintf(stdout, format, ap);
     *   }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_debugf(lame_global_flags *,
                              void (*func)(const char *, va_list));</pre>
     */
    int lame_set_debugf(LameGlobalFlags flags,LameMsgCallback func);   
  
    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     *   void my_debugf(const char *format, va_list ap)
     *   {
     *       (void) vfprintf(stdout, format, ap);
     *   }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_msgf(lame_global_flags *,
                              void (*func)(const char *, va_list));</pre>
     */
    int lame_set_msgf(LameGlobalFlags flags,LameMsgCallback func); 

    /**
     * set one of brate compression ratio.  default is compression ratio of 11.  
     
     * <pre>int CDECL lame_set_brate(lame_global_flags *, int);</pre>
     */
    int lame_set_brate(LameGlobalFlags flags, int brate);

    
    /**
     * set one of brate compression ratio.  default is compression ratio of 11.  
     
     * <pre>int CDECL lame_get_brate(const lame_global_flags *);
     */    
    int lame_get_brate(LameGlobalFlags flags);

    /* 
     * <pre>int CDECL lame_set_compression_ratio(lame_global_flags *, float);</pre>
     */
    int lame_set_compression_ratio(LameGlobalFlags flags, float ratio);

    /* 
     * <pre>float CDECL lame_get_compression_ratio(const lame_global_flags *);</pre>
     */   
    float lame_get_compression_ratio(LameGlobalFlags flags);

    /**
     * <pre>int CDECL lame_set_preset( lame_global_flags*  gfp, int );</pre>
     */
    int lame_set_preset(LameGlobalFlags flags, int preset);
    
    /**
     * <pre>int CDECL lame_set_asm_optimizations( lame_global_flags*  gfp, int, int );</pre>
     */
    int lame_set_asm_optimizations(LameGlobalFlags flags, int param1, int param2);

    /**
     * mark as copyright.  default=0 
     *
       <pre>int CDECL lame_set_copyright(lame_global_flags *, int);</pre>
     */
    int lame_set_copyright(LameGlobalFlags flags, int cprght);
    
    /**
     * mark as copyright.  default=0 
     *
       <pre>int CDECL lame_get_copyright(const lame_global_flags *);</pre>
     */    
    int lame_get_copyright(LameGlobalFlags flags);   
    
    /**
     * mark as original.  default=1 
     *
       <pre>int CDECL lame_set_original(lame_global_flags *, int);</pre>
     */
    int lame_set_original(LameGlobalFlags flags, int original);

    /**
     * mark as original.  default=1      
     *
       <pre>int CDECL lame_get_original(const lame_global_flags *);</pre>    
     */
    int lame_get_original(LameGlobalFlags flags);
    
    /**
     * error_protection.  Use 2 bytes from each frame for CRC checksum. default=0
     *
       <pre>int CDECL lame_set_error_protection(lame_global_flags *, int);</pre>  
    */    
     int  lame_set_error_protection(LameGlobalFlags flags, int prot);

    /**
     * error_protection.  Use 2 bytes from each frame for CRC checksum. default=0     
     *
       <pre>int CDECL lame_get_error_protection(const lame_global_flags *);</pre>
    */
    int lame_get_error_protection(LameGlobalFlags flags);   

    /**
     * padding_type. 0=pad no frames  1=pad all frames 2=adjust padding(default)
     *
       <pre>int CDECL lame_set_padding_type(lame_global_flags *, Padding_type);</pre>
    */    
    int lame_set_padding_type(LameGlobalFlags flags, int padding_type);


    /**
     * padding_type. 0=pad no frames  1=pad all frames 2=adjust padding(default)    
     *
       <pre>Padding_type CDECL lame_get_padding_type(const lame_global_flags *);</pre>    
    */
    int lame_get_padding_type(LameGlobalFlags flags);   
    
    /**
    * MP3 'private extension' bit  Meaningless.  default=0 

    * <pre>int CDECL lame_set_extension(lame_global_flags *, int);</pre>
    */
    int lame_set_extension(LameGlobalFlags flags, int ext); 
 
    /**
     * MP3 'private extension' bit  Meaningless.  default=0 
     
     * <pre>int CDECL lame_get_extension(const lame_global_flags *); </pre>
     */     
    int lame_get_extension(LameGlobalFlags flags);      
        
    /**
     * enforce strict ISO compliance.  default=0 
     *
     * <pre>int CDECL lame_set_strict_ISO(lame_global_flags *, int);</pre>
     */
    int lame_set_strict_ISO(LameGlobalFlags flags, int iso); 
    
    /**
     * enforce strict ISO compliance.  default=0 
     *
     * <pre>int CDECL lame_get_strict_ISO(const lame_global_flags *);</pre>
     */
    int lame_get_strict_ISO(LameGlobalFlags flags);  
    
    /**
     * disable the bit reservoir. For testing only. default=0
     *
       <pre>int CDECL lame_set_disable_reservoir(lame_global_flags *, int);</pre>
    */    
    int lame_set_disable_reservoir(LameGlobalFlags flags, int preset);
      
    /**
     * disable the bit reservoir. For testing only. default=0     
     *
       <pre>int CDECL lame_get_disable_reservoir(const lame_global_flags *);</pre>
    */
    int lame_get_disable_reservoir(LameGlobalFlags flags);   
            
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_set_quant_comp(lame_global_flags *, int);</pre>
     */
    int lame_set_quant_comp(LameGlobalFlags flags, int quant); 
    
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_get_quant_comp(const lame_global_flags *);</pre>
     */    
    int lame_get_quant_comp(LameGlobalFlags flags);
        
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_set_quant_comp_short(lame_global_flags *, int);</pre>
     */    
    int lame_set_quant_comp_short(LameGlobalFlags flags, int quant);  
    
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_get_quant_comp_short(const lame_global_flags *);</pre>
     */ 
    int lame_get_quant_comp_short(LameGlobalFlags flags); 
   
    /**
     * <pre>int CDECL lame_set_experimentalX(lame_global_flags *, int);</pre>
     */
    int lame_set_experimentalX(LameGlobalFlags flags, int x);
   
    /**
     * <pre>int CDECL lame_get_experimentalX(const lame_global_flags *);</pre>
     */    
    int lame_get_experimentalX(LameGlobalFlags flags);
    
    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_set_experimentalY(lame_global_flags *, int);</pre>
     */
    int lame_set_experimentalY(LameGlobalFlags flags, int y);
    
    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_get_experimentalY(const lame_global_flags *);</pre>
     */    
    int lame_get_experimentalY(LameGlobalFlags flags);  
    
    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_set_experimentalZ(lame_global_flags *, int);</pre>
     */
    int lame_set_experimentalZ(LameGlobalFlags flags, int z);   

    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_get_experimentalZ(const lame_global_flags *);</pre>
     */    
    int lame_get_experimentalZ(LameGlobalFlags flags);    

    
    /* Naoki's psycho acoustic model.  default=0 
     
      <pre>int CDECL lame_set_exp_nspsytune(lame_global_flags *, int);</pre>
     */
    int lame_set_exp_nspsytune(LameGlobalFlags flags, int tune);  
    
    /* Naoki's psycho acoustic model.  default=0 
     
      <pre>int CDECL lame_get_exp_nspsytune(const lame_global_flags *);</pre>
     */    
    int lame_get_exp_nspsytune(LameGlobalFlags flags);
    
    /**
     * <pre>void CDECL lame_set_msfix(lame_global_flags *, double);</pre>
     */
    void lame_set_msfix(LameGlobalFlags flags, double msfix);

    
    /**
     * <pre>float CDECL lame_get_msfix(const lame_global_flags *);</pre>
     */    
    float lame_get_msfix(LameGlobalFlags flags);
    
    /**
     * <pre>int lame_set_exp_nspsytune2_int( lame_global_flags*, int, int);</pre>
     */      
    int lame_set_exp_nspsytune2_int( LameGlobalFlags flags, int p1, int p2);

    /**
     * <pre>float lame_set_exp_nspsytune2_real( lame_global_flags*, int, float);</pre>
     */     
    float lame_set_exp_nspsytune2_real( LameGlobalFlags flags, int p1, float p2);
    
    /**
     * <pre>void * lame_set_exp_nspsytune2_pointer( lame_global_flags*, int, void *);</pre>
     */      
    int lame_set_exp_nspsytune2_pointer( LameGlobalFlags flags, int p1, int p2);

    /** 
     * Types of VBR.  default = vbr_off = CBR 
     *
     * <pre>int CDECL lame_set_VBR(lame_global_flags *, vbr_mode);</pre>
     */
    int lame_set_VBR(LameGlobalFlags flags,int vbr_mode); 
    
    /** 
     * Types of VBR.  default = vbr_off = CBR 
     *     
     * <pre>vbr_mode CDECL lame_get_VBR(const lame_global_flags *);</pre>
     */    
    int lame_get_VBR(LameGlobalFlags flags);
    
    /**
     * VBR quality level.  0=highest  9=lowest  
     *
        <pre>int CDECL lame_set_VBR_q(lame_global_flags *, int);</pre>
     */
    int lame_set_VBR_q(LameGlobalFlags flags, int level);
    
    /**
     * VBR quality level.  0=highest  9=lowest
     *     
        <pre>int CDECL lame_get_VBR_q(const lame_global_flags *);</pre>
     */    
    int lame_get_VBR_q(LameGlobalFlags flags);
    
    /**
     * Ignored except for VBR=vbr_abr (ABR mode) 
     *
        <pre>int CDECL lame_set_VBR_mean_bitrate_kbps(lame_global_flags *, int);</pre>    
     */
    int lame_set_VBR_mean_bitrate_kbps(LameGlobalFlags flags, int bitrate);

    /**
     * Ignored except for VBR=vbr_abr (ABR mode)     
     *
        <pre>int CDECL lame_get_VBR_mean_bitrate_kbps(const lame_global_flags *);</pre>  
     */    
    int lame_get_VBR_mean_bitrate_kbps(LameGlobalFlags flags);

    /**
        <pre>int CDECL lame_set_VBR_min_bitrate_kbps(const lame_global_flags *);</pre>  
     */     
    int lame_set_VBR_min_bitrate_kbps(LameGlobalFlags flags, int bitrate);

    /**
        <pre>int CDECL lame_get_VBR_min_bitrate_kbps(const lame_global_flags *);</pre>  
     */    
    int lame_get_VBR_min_bitrate_kbps(LameGlobalFlags flags);

    /**
        <pre>int CDECL lame_set_VBR_max_bitrate_kbps(const lame_global_flags *);</pre>  
     */    
    int lame_set_VBR_max_bitrate_kbps(LameGlobalFlags flags, int bitrate);

    /**
        <pre>int CDECL lame_get_VBR_max_bitrate_kbps(const lame_global_flags *);</pre>  
     */     
    int lame_get_VBR_max_bitrate_kbps(LameGlobalFlags flags);

    /**
      1=strictly enforce VBR_min_bitrate.  Normally it will be violated for
      analog silence
     *
     * <pre>int CDECL lame_set_VBR_hard_min(lame_global_flags *, int);</pre>
    */
    int lame_set_VBR_hard_min(LameGlobalFlags flags, int vbr);
    
    /**
      1=strictly enforce VBR_min_bitrate.  Normally it will be violated for
      analog silence
     *
     * <pre>int CDECL lame_get_VBR_hard_min(const lame_global_flags *);</pre>
    */    
    int lame_get_VBR_hard_min(LameGlobalFlags flags);
    
    
    /** 
     * for preset 
     
     * <pre>int CDECL lame_set_preset_expopts(lame_global_flags *, int);</pre>
     */
    int lame_set_preset_expopts(LameGlobalFlags flags, int expopts);

    
    /**
     * freq in Hz to apply lowpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_set_lowpassfreq(lame_global_flags *, int);</pre>
     */
    int lame_set_lowpassfreq(LameGlobalFlags flags, int freq);
    
    /**
     * freq in Hz to apply lowpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_get_lowpassfreq(const lame_global_flags *);</pre>
     */    
    int lame_get_lowpassfreq(LameGlobalFlags flags);

    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_set_lowpasswidth(lame_global_flags *, int);</pre>
     */
    int lame_set_lowpasswidth(LameGlobalFlags flags, int width);      
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_get_lowpasswidth(const lame_global_flags *);</pre>
     */    
    int lame_get_lowpasswidth(LameGlobalFlags flags);
    
    
    /** freq in Hz to apply highpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_set_highpassfreq(lame_global_flags *, int);</pre>
     */
    int lame_set_highpassfreq(LameGlobalFlags flags, int freq);     
    
    /** freq in Hz to apply highpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_get_highpassfreq(const lame_global_flags *);</pre>
     */    
    int lame_get_highpassfreq(LameGlobalFlags flags);     
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_set_highpasswidth(lame_global_flags *, int);</pre>
     */
    int lame_set_highpasswidth(LameGlobalFlags flags, int width);   
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_get_highpasswidth(const lame_global_flags *);</pre>
     */    
    int lame_get_highpasswidth(LameGlobalFlags flags);    
    
    /** 
     * only use ATH for masking 
     
     * <pre>int CDECL lame_set_ATHonly(lame_global_flags *, int);</pre>
     */
    int lame_set_ATHonly(LameGlobalFlags flags, int athOnly);
    
    /** 
     * only use ATH for masking 
     
     * <pre>int CDECL lame_get_ATHonly(const lame_global_flags *);</pre>
     */    
    int lame_get_ATHonly(LameGlobalFlags flags);
    
    /**
     * only use ATH for short blocks 
     
     * <pre>int CDECL lame_set_ATHshort(lame_global_flags *, int);</pre>
     */
    int lame_set_ATHshort(LameGlobalFlags flags, int athShort);
   
    /**
     * only use ATH for short blocks 
     
     * <pre>int CDECL lame_get_ATHshort(const lame_global_flags *);</pre>
     */    
    int lame_get_ATHshort(LameGlobalFlags flags);
   
    /**
     * disable ATH 
     
     * <pre>int CDECL lame_set_noATH(lame_global_flags *, int);</pre>
     */
    int lame_set_noATH(LameGlobalFlags flags, int noATH);
    
    /**
     * disable ATH 
     
     * <pre>int CDECL lame_get_noATH(const lame_global_flags *);</pre>
     */    
    int lame_get_noATH(LameGlobalFlags flags);
    
    /**
     * select ATH formula 
     
     * <pre>int CDECL lame_set_ATHtype(lame_global_flags *, int);</pre>
     */
    int lame_set_ATHtype(LameGlobalFlags flags, int ATHType);
    
    /**
     * select ATH formula 
     
     * <pre>int CDECL lame_get_ATHtype(lame_global_flags *);</pre>
     */    
    int lame_get_ATHtype(LameGlobalFlags flags);

    /**
     * lower ATH by this many db 
     
     * <pre>int CDECL lame_set_ATHlower(lame_global_flags *, float);</pre>
     */
    int lame_set_ATHlower(LameGlobalFlags flags, float ATHLower);
   
    /**
     * lower ATH by this many db 
     
     * <pre>floag CDECL lame_get_ATHlower(lame_global_flags *);</pre>
     */    
    float lame_get_ATHlower(LameGlobalFlags flags);
    
    /**
     * select ATH adaptive adjustment type 
     
     * <pre>int CDECL lame_set_athaa_type( lame_global_flags *, int);</pre>
     */
    int lame_set_athaa_type( LameGlobalFlags flags, int ATHadjust);
  
    /**
     * select ATH adaptive adjustment type 
     
     * <pre>int CDECL lame_get_athaa_type( const lame_global_flags *);</pre>
     */    
    int lame_get_athaa_type(LameGlobalFlags flags);    
   
    /**
     * select the loudness approximation used by the ATH adaptive auto-leveling  
     
     * <pre>int CDECL lame_set_athaa_loudapprox( lame_global_flags *, int);</pre>
     */
    int lame_set_athaa_loudapprox( LameGlobalFlags flags, int loud);
    
    /**
     * select the loudness approximation used by the ATH adaptive auto-leveling  
     
     * <pre>int CDECL lame_get_athaa_loudapprox( const lame_global_flags *);</pre>
     */    
    int lame_get_athaa_loudapprox(LameGlobalFlags flags);     
    
    /**
     * adjust (in dB) the point below which adaptive ATH level adjustment occurs 
     
     * <pre>int CDECL lame_set_athaa_sensitivity( lame_global_flags *, float);</pre>
     */
    int lame_set_athaa_sensitivity( LameGlobalFlags flags, float sen);  
    
    /**
     * adjust (in dB) the point below which adaptive ATH level adjustment occurs 
     
     * <pre>float CDECL lame_get_athaa_sensitivity( const lame_global_flags* );</pre>
     */    
    float lame_get_athaa_sensitivity( LameGlobalFlags flags );   
    
    /** 
     * predictability limit (ISO tonality formula) 
     
       <pre>int CDECL lame_set_cwlimit(lame_global_flags *, int);</pre>
     */
    int lame_set_cwlimit(LameGlobalFlags flags, int limit);
   
    /** 
     * predictability limit (ISO tonality formula) 
     
       <pre>int CDECL lame_get_cwlimit(const lame_global_flags *);</pre>
     */    
    int lame_get_cwlimit(LameGlobalFlags flags);
   
    /**
      allow blocktypes to differ between channels?
      default: 0 for jstereo, 1 for stereo
     *
     * <pre>int CDECL lame_set_allow_diff_short(lame_global_flags *, int);</pre>
    */
    int lame_set_allow_diff_short(LameGlobalFlags flags, int allow);  
    
    /**
      allow blocktypes to differ between channels?
      default: 0 for jstereo, 1 for stereo
     *
     * <pre>int CDECL lame_get_allow_diff_short(const lame_global_flags *);</pre>
    */    
    int lame_get_allow_diff_short(LameGlobalFlags flags); 
    
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>int CDECL lame_set_useTemporal(lame_global_flags *, int);</pre>
     */
    int lame_set_useTemporal(LameGlobalFlags flags, int maskEffect);
    
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>int CDECL lame_get_useTemporal(const lame_global_flags *);</pre>
     */    
    int lame_get_useTemporal(LameGlobalFlags flags);    
   
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>int CDECL lame_set_interChRatio(lame_global_flags *, float);</pre>
     */
    int lame_set_interChRatio(LameGlobalFlags flags, float interChRatio);

    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>float CDECL lame_get_interChRatio(const lame_global_flags *);</pre>
     */    
    float lame_get_interChRatio(LameGlobalFlags flags);
   
    /**
     * disable short blocks 
     
       <pre>int CDECL lame_set_no_short_blocks(lame_global_flags *, int);</pre>
     */
    int lame_set_no_short_blocks(LameGlobalFlags flags, int noShort);
  
    /**
     * disable short blocks 
     
       <pre>int CDECL lame_get_no_short_blocks(const lame_global_flags *);</pre>
     */    
    int lame_get_no_short_blocks(LameGlobalFlags flags);
    
    /** 
     * force short blocks 
     
     * <pre>int CDECL lame_set_force_short_blocks(lame_global_flags *, int);</pre>
     */
    int lame_set_force_short_blocks(LameGlobalFlags flgas, int force); 
    
    /** 
     * force short blocks 
     
     * <pre>int CDECL lame_get_force_short_blocks(const lame_global_flags *);</pre>
     */    
    int lame_get_force_short_blocks(LameGlobalFlags flags);    

    /**
     * Input PCM is emphased PCM (for instance from one of the rarely
       emphased CDs), it is STRONGLY not recommended to use this, because
       psycho does not take it into account, and last but not least many decoders
       ignore these bits      
     
     * <pre>int CDECL lame_set_emphasis(lame_global_flags *, int);</pre>
     */
    int lame_set_emphasis(LameGlobalFlags flags, int emph);    
    
    /**
     * Input PCM is emphased PCM (for instance from one of the rarely
       emphased CDs), it is STRONGLY not recommended to use this, because
       psycho does not take it into account, and last but not least many decoders
       ignore these bits      
     
     * <pre>int CDECL lame_get_emphasis(const lame_global_flags *);</pre>
     */    
    int lame_get_emphasis(LameGlobalFlags flags);
    
    /**
     * version  0=MPEG-2  1=MPEG-1  (2=MPEG-2.5)    
     *
     * <pre>int CDECL lame_get_version(const lame_global_flags *);</pre> 
     */
    int lame_get_version(LameGlobalFlags flags);

    /** 
     * encoder delay   
     
     * <pre>int CDECL lame_get_encoder_delay(const lame_global_flags *);</pre>
     */
    int lame_get_encoder_delay(LameGlobalFlags flags);
    
    /**
      padding appended to the input to make sure decoder can fully decode
      all input.  Note that this value can only be calculated during the
      call to lame_encoder_flush().  Before lame_encoder_flush() has
      been called, the value of encoder_padding = 0.
     *
     * <pre>int CDECL lame_get_encoder_padding(const lame_global_flags *);  </pre>
    */
    int lame_get_encoder_padding(LameGlobalFlags flags);
   
    /**
     * size of MPEG frame 
     
      <pre>int CDECL lame_get_framesize(const lame_global_flags *);</pre>
     */
    int lame_get_framesize(LameGlobalFlags flags);
    
    /**
     * number of PCM samples buffered, but not yet encoded to mp3 data. 
     
     * <pre>int CDECL lame_get_mf_samples_to_encode( const lame_global_flags*  gfp );</pre>
     */
    int lame_get_mf_samples_to_encode( LameGlobalFlags flags);
    
    /**
      size (bytes) of mp3 data buffered, but not yet encoded.
      this is the number of bytes which would be output by a call to 
      lame_encode_flush_nogap.  NOTE: lame_encode_flush() will return
      more bytes than this because it will encode the reamining buffered
      PCM samples before flushing the mp3 buffers.
     *
     * <pre>int CDECL lame_get_size_mp3buffer( const lame_global_flags*  gfp );</pre>
    */
    int lame_get_size_mp3buffer( LameGlobalFlags flags );
    
    /**
     * number of frames encoded so far 
     
      <pre>int CDECL lame_get_frameNum(const lame_global_flags *);</pre>
     */
    int lame_get_frameNum(LameGlobalFlags flags);
   
    /**
      lame's estimate of the total number of frames to be encoded
       only valid if calling program set num_samples
     *
     * <pre>int CDECL lame_get_totalframes(const lame_global_flags *);</pre>
    */
    int lame_get_totalframes(LameGlobalFlags flags);    
    
    /** 
     * RadioGain value. Multiplied by 10 and rounded to the nearest. 
     
     * <pre>int CDECL lame_get_RadioGain(const lame_global_flags *);</pre>
     */
    int lame_get_RadioGain(LameGlobalFlags flags);
   
    /**
     * AudiophileGain value. Multipled by 10 and rounded to the nearest. 
     
      <pre>int CDECL lame_get_AudiophileGain(const lame_global_flags *);</pre>
     */
    int lame_get_AudiophileGain(LameGlobalFlags flags);
   
    /**
     * the peak sample 
     
      <pre>float CDECL lame_get_PeakSample(const lame_global_flags *);</pre>
     */
    float lame_get_PeakSample(LameGlobalFlags flags);
    
    /**
     * Gain change required for preventing clipping. The value is correct only if 
       peak sample searching was enabled. If negative then the waveform 
       already does not clip. The value is multiplied by 10 and rounded up. 
     
      <pre>int CDECL lame_get_noclipGainChange(const lame_global_flags *);</pre>
     */
    int lame_get_noclipGainChange(LameGlobalFlags flags);
    
    /**
     * user-specified scale factor required for preventing clipping. Value is 
       correct only if peak sample searching was enabled and no user-specified
       scaling was performed. If negative then either the waveform already does
       not clip or the value cannot be determined 
     
     * <pre>float CDECL lame_get_noclipScale(const lame_global_flags *);</pre>
     */
    float lame_get_noclipScale(LameGlobalFlags flags);
   
    /**
     * REQUIRED:
     * sets more internal configuration based on data provided above.
     * returns -1 if something failed.
     *
     * <pre>int CDECL lame_init_params(lame_global_flags * const );</pre>
     */
    int lame_init_params(LameGlobalFlags flags);
   
    /**
     * OPTIONAL:
     * get the version number, in a string. of the form:  
     * "3.63 (beta)" or just "3.63". 
     *
     * <pre>const char*  CDECL get_lame_version( void );</pre>
     */
    String get_lame_version();
   
    /**
        <pre>const char*  CDECL get_lame_short_version( void );</pre>
     */    
    String get_lame_short_version();
    
    /**
        <pre>const char*  CDECL get_lame_very_short_version( void );</pre>
     */    
    String get_lame_very_short_version();
   
    /**
        <pre>const char*  CDECL get_psy_version( void );</pre>
     */     
    String get_psy_version();   
    
    /**
        <pre>const char*  CDECL get_lame_url( void );</pre>
     */     
    String get_lame_url();
     
    /*
     * OPTIONAL:
     * get the version numbers in numerical form.
     *
     * <pre>void CDECL get_lame_version_numerical ( lame_version_t *const );</pre>
     */
    void get_lame_version_numerical ( LameVersion version );

    /*
     * OPTIONAL:
     * print internal lame configuration to message handler
     *
     * <pre>void CDECL lame_print_config(const lame_global_flags*  gfp);</pre>
     */
    void lame_print_config(LameGlobalFlags flags);    
    
    /**
     * <pre>void CDECL lame_print_internals( const lame_global_flags *gfp);</pre>
     */
    void lame_print_internals( LameGlobalFlags flags);    
    
    /**
     * input pcm data, output (maybe) mp3 frames.
     * This routine handles all buffering, resampling and filtering for you.
     * 
     * return code     number of bytes output in mp3buf. Can be 0 
     *                 -1:  mp3buf was too small
     *                 -2:  malloc() problem
     *                 -3:  lame_init_params() not called
     *                 -4:  psycho acoustic problems 
     * 
     * The required mp3buf_size can be computed from num_samples, 
     * samplerate and encoding rate, but here is a worst case estimate:
     * 
     * mp3buf_size in bytes = 1.25*num_samples + 7200
     * 
     * I think a tighter bound could be:  (mt, March 2000)
     * MPEG1:
     *    num_samples*(bitrate/8)/samplerate + 4*1152*(bitrate/8)/samplerate + 512
     * MPEG2:
     *    num_samples*(bitrate/8)/samplerate + 4*576*(bitrate/8)/samplerate + 256
     * 
     * but test first if you use that!
     * 
     * set mp3buf_size = 0 and Lame will not check if mp3buf_size is
     * large enough.
     * 
     * NOTE:
     * if gfp->num_channels=2, but gfp->mode = 3 (mono), the L & R channels
     * will be averaged into the L channel before encoding only the L channel
     * This will overwrite the data in buffer_l[] and buffer_r[].
     * 
     * <pre>
     *    int CDECL lame_encode_buffer (
     *            lame_global_flags*  gfp,            global context handle  
     *            const short int     buffer_l [],    PCM data for left channel     
     *            const short int     buffer_r [],    PCM data for right channel    
     *            const int           nsamples,       number of samples per channel 
     *            unsigned char*      mp3buf,         pointer to encoded MP3 stream 
     *            const int           mp3buf_size );  number of valid octets in this
     *                                                  stream
     * </pre>
     */
    int lame_encode_buffer(
            LameGlobalFlags  gfp,    
            short[] buffer_l,  
            short[] buffer_r, 
            int     nsamples,    
            byte[]  mp3buf, 
            int     mp3buf_size);
   
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_encode_buffer(LameGlobalFlags,short[],short[],int,byte[],int)
     */   
    int lame_encode_buffer(
            LameGlobalFlags  gfp,    
            byte[] buffer_l,  
            byte[] buffer_r, 
            int     nsamples,    
            byte[]  mp3buf, 
            int     mp3buf_size);
   
    /**
    * as above (lame_encode_buffer), but input has L & R channel data interleaved.
    * NOTE: 
    * num_samples = number of samples in the L (or R)
    * channel, not the total number of samples in pcm[]  
    *
    * <pre>
    * int CDECL lame_encode_buffer_interleaved(
        lame_global_flags*  gfp,            global context handlei 
        short int           pcm[],          PCM data for left and right
                                              channel, interleaved   
        int                 num_samples,    number of samples per channel,
                                              _not_ number of samples in
                                              pcm[]                  
        unsigned char*      mp3buf,         pointer to encoded MP3 stream 
        int                 mp3buf_size );  number of valid octets in this
                                              stream                 
    * </pre>
    */
    int lame_encode_buffer_interleaved(
            LameGlobalFlags  gfp,
            short[] pcm,
            int num_samples, 
            byte[]  mp3buf,
            int mp3buf_size);
   
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_encode_buffer_interleaved(LameGlobalFlags,short[],int,byte[],int)
     */   
    int lame_encode_buffer_interleaved(
            LameGlobalFlags  gfp,
            byte[] pcm,
            int num_samples, 
            byte[]  mp3buf,
            int mp3buf_size);
   
    /**
     * as lame_encode_buffer, but for 'float's.
     * !! NOTE: !! data must still be scaled to be in the same range as 
     * short int, +/- 32768  
     *
     * <pre>
     *     int CDECL lame_encode_buffer_float(
            lame_global_flags*  gfp,           // global context handle  
            const float     buffer_l [],       // PCM data for left channel     
            const float     buffer_r [],       // PCM data for right channel    
            const int           nsamples,      // number of samples per channel 
            unsigned char*      mp3buf,        // pointer to encoded MP3 stream 
            const int           mp3buf_size ); // number of valid octets in this stream   
      </pre>
     */
    int lame_encode_buffer_float(
            LameGlobalFlags  gfp,
            float     buffer_l [],
            float     buffer_r [],
            int       nsamples,
            byte[]    mp3buf,
            int       mp3buf_size );
 
    /* as lame_encode_buffer, but for long's 
     * !! NOTE: !! data must still be scaled to be in the same range as 
     * short int, +/- 32768  
     *
     * This scaling was a mistake (doesn't allow one to exploit full
     * precision of type 'long'.  Use lame_encode_buffer_long2() instead.
     *
     * <pre>
     *     int CDECL lame_encode_buffer_long(
            lame_global_flags*  gfp,          // global context handle  
            const long     buffer_l [],       // PCM data for left channel     
            const long     buffer_r [],       // PCM data for right channel    
            const int           nsamples,      // number of samples per channel 
            unsigned char*      mp3buf,        // pointer to encoded MP3 stream 
            const int           mp3buf_size ); // number of valid octets in this stream
       </pre>     
     */
    int lame_encode_buffer_long(
            LameGlobalFlags  gfp,
            int     buffer_l [],
            int     buffer_r [],
            int     nsamples, 
            byte[]  mp3buf,
            int     mp3buf_size );  
    
    /* Same as lame_encode_buffer_long(), but with correct scaling. 
     * !! NOTE: !! data must still be scaled to be in the same range as  
     * type 'long'.   Data should be in the range:  +/- 2^(8*size(long)-1)
     *
     * <pre>
     *    int CDECL lame_encode_buffer_long2(
            lame_global_flags*  gfp,           // global context handle  
            const long     buffer_l [],       // PCM data for left channel     
            const long     buffer_r [],       // PCM data for right channel    
            const int           nsamples,      // number of samples per channel 
            unsigned char*      mp3buf,        // pointer to encoded MP3 stream 
            const int           mp3buf_size ); // number of valid octets in this stream
     * </pre>
     */
    int lame_encode_buffer_long2(
            LameGlobalFlags  gfp,
            int     buffer_l [],
            int     buffer_r [],   
            int     nsamples, 
            byte[]  mp3buf, 
            int     mp3buf_size );
   
    /* as lame_encode_buffer, but for int's 
     * !! NOTE: !! input should be scaled to the maximum range of 'int'
     * If int is 4 bytes, then the values should range from
     * +/- 2147483648.  
     *
     * This routine does not (and cannot, without loosing precision) use
     * the same scaling as the rest of the lame_encode_buffer() routines.
     * 
     * <pre>
     *     int CDECL lame_encode_buffer_int(
            lame_global_flags*  gfp,           // global context handle  
            const int      buffer_l [],       // PCM data for left channel     
            const int      buffer_r [],       // PCM data for right channel    
            const int           nsamples,      // number of samples per channel 
            unsigned char*      mp3buf,        // pointer to encoded MP3 stream 
            const int           mp3buf_size ); // number of valid octets in this stream
     * </pre>
     */   
    int lame_encode_buffer_int(
            LameGlobalFlags  gfp,
            int      buffer_l [],
            int      buffer_r [],
            int      nsamples,
            byte[] mp3buf,
            int      mp3buf_size );   
    
    /**
     * REQUIRED:
     * lame_encode_flush will flush the intenal PCM buffers, padding with 
     * 0's to make sure the final frame is complete, and then flush
     * the internal MP3 buffers, and thus may return a 
     * final few mp3 frames.  'mp3buf' should be at least 7200 bytes long
     * to hold all possible emitted data.
     *
     * will also write id3v1 tags (if any) into the bitstream
     *
     * return code = number of bytes output to mp3buf. Can be 0
     *
     * <pre>
     * int CDECL lame_encode_flush(
         lame_global_flags *  gfp,     // global context handle         
         unsigned char*       mp3buf,  // pointer to encoded MP3 stream  
         int                  size);   // number of valid octets in this stream 
     * </pre>
     */
    int lame_encode_flush(LameGlobalFlags  gfp, byte[] mp3buf, int size);   
    
    /*
     * OPTIONAL:
     * lame_encode_flush_nogap will flush the internal mp3 buffers and pad
     * the last frame with ancillary data so it is a complete mp3 frame.
     * 
     * 'mp3buf' should be at least 7200 bytes long
     * to hold all possible emitted data.
     *
     * After a call to this routine, the outputed mp3 data is complete, but
     * you may continue to encode new PCM samples and write future mp3 data
     * to a different file.  The two mp3 files will play back with no gaps
     * if they are concatenated together.
     *
     * This routine will NOT write id3v1 tags into the bitstream.
     *
     * return code = number of bytes output to mp3buf. Can be 0
     *
     * <pre>
     *     int CDECL lame_encode_flush_nogap(
            lame_global_flags *  gfp,    // global context handle          
            unsigned char*       mp3buf, // pointer to encoded MP3 stream  
            int                  size);  // number of valid octets in this stream 
     * </pre>
     */
    int lame_encode_flush_nogap(
            LameGlobalFlags  gfp,
            byte[]   mp3buf,
            int      size); 
    
    /*
     * OPTIONAL:
     * Normally, this is called by lame_init_params().  It writes id3v2 and
     * Xing headers into the front of the bitstream, and sets frame counters
     * and bitrate histogram data to 0.  You can also call this after 
     * lame_encode_flush_nogap().  
     *
     * <pre>
     *     int CDECL lame_init_bitstream(
              lame_global_flags *  gfp);    // global context handle
     * </pre>    
     */
    int lame_init_bitstream(LameGlobalFlags  gfp);
    
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
                const lame_global_flags *const gfp, 
                int   bitrate_count[14] );
     * </pre>
     */
    void lame_bitrate_hist( 
            LameGlobalFlags gfp, 
            int[] bitrate_count );
    
    /**
     * See {@link #lame_bitrate_hist(LameGlobalFlags,int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_kbps( 
            const lame_global_flags *const gfp, 
            int bitrate_kbps [14] );  
     * </pre>
     */
    void lame_bitrate_kbps( 
            LameGlobalFlags gfp, 
            int[]  bitrate_kbps);    
   
    /**
     * See {@link #lame_bitrate_hist(LameGlobalFlags,int[])}
     *
     * <pre>
     * void CDECL lame_stereo_mode_hist( 
            const lame_global_flags *const gfp, 
            int stereo_mode_count[4] );  
     * </pre>
     */    
    void lame_stereo_mode_hist( 
            LameGlobalFlags gfp, 
            int[] stereo_mode_count); 
    
    /**
     * See {@link #lame_bitrate_hist(LameGlobalFlags,int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_stereo_mode_hist ( 
        const lame_global_flags * const gfp, 
        int  bitrate_stmode_count [14] [4] );
     * </pre>
     */      
    void lame_bitrate_stereo_mode_hist( 
            LameGlobalFlags gfp, 
            int[][]  bitrate_stmode_count);
   
    /**
     * See {@link #lame_bitrate_hist(LameGlobalFlags,int[])}
     *
     * <pre>
     * void CDECL lame_block_type_hist (
        const lame_global_flags * const gfp, 
        int btype_count[6] );
     * </pre>
     */      
    void lame_block_type_hist( 
            LameGlobalFlags gfp, 
            int[]  btype_count);   
    
    /**
     * See {@link #lame_bitrate_hist(LameGlobalFlags,int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_block_type_hist ( 
        const lame_global_flags * const gfp, 
        int bitrate_btype_count[14][6] );
     * </pre>
     */      
    void lame_bitrate_block_type_hist( 
            LameGlobalFlags gfp, 
           int[][]  bitrate_btype_count);
    
    /**
     * OPTIONAL:
     * lame_mp3_tags_fid will append a Xing VBR tag to the mp3 file with file
     * pointer fid.  These calls perform forward and backwards seeks, so make
     * sure fid is a real file.  Make sure lame_encode_flush has been called,
     * and all mp3 data has been written to the file before calling this
     * function.
     * NOTE:
     * if VBR  tags are turned off by the user, or turned off by Lame because
     * the output is not a regular file, this call does nothing
     * 
     * <pre>void CDECL lame_mp3_tags_fid(lame_global_flags *,FILE* fid);</pre>
     */
    void lame_mp3_tags_fid(LameGlobalFlags flags,Pointer fid);    

    /**
     * REQUIRED:
     * final call to free all remaining buffers
     *
     * <pre>int  CDECL lame_close (lame_global_flags *);</pre> 
     */
    int lame_close(LameGlobalFlags flags);
    
    /**
     * required call to initialize decoder 
     * NOTE: the decoder should not be used when encoding is performed
     * with decoding on the fly 
     *
     * <pre>int CDECL lame_decode_init(void);</pre>
     */
    int lame_decode_init();
    
    /**
     * input 1 mp3 frame, output (maybe) pcm data.  
     *
     *  nout = lame_decode(mp3buf,len,pcm_l,pcm_r);
     *
     * input:  
     *    len          :  number of bytes of mp3 data in mp3buf
     *    mp3buf[len]  :  mp3 data to be decoded
     *
     * output:
     *    nout:  -1    : decoding error
     *            0    : need more data before we can complete the decode 
     *           >0    : returned 'nout' samples worth of data in pcm_l,pcm_r
     *    pcm_l[nout]  : left channel data
     *    pcm_r[nout]  : right channel data 
     *
     * <pre>
     *    int CDECL lame_decode(
            unsigned char *  mp3buf,
            int              len,
            short            pcm_l[],
            short            pcm_r[] ); 
     * </pre>    
     */
    int lame_decode(
            byte[]  mp3buf,
            int     len,
            short[] pcm_l,
            short[] pcm_r);
  
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_decode(byte[],int,short[],short[])
     */    
    int lame_decode(
            byte[]  mp3buf,
            int     len,
            byte[] pcm_l,
            byte[] pcm_r);  
    
    /**
     * same as lame_decode, and also returns mp3 header data 
     
     * <pre>
     *    int CDECL lame_decode_headers(
            unsigned char*   mp3buf,
            int              len,
            short            pcm_l[],
            short            pcm_r[],
            mp3data_struct*  mp3data );
     * </pre>
     */
    int lame_decode_headers(
            byte[]  mp3buf,
            int              len,
            short[]            pcm_l,
            short[]            pcm_r,
            Mp3data  mp3data );
   
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_decode_headers(byte[],int,short[],short[],Mp3data)
     */    
    int lame_decode_headers(
            byte[]  mp3buf,
           int     len,
            byte[]  pcm_l,
            byte[]  pcm_r,
            Mp3data  mp3data ); 
    
    /**
     * same as lame_decode, but returns at most one frame 
     
     * <pre>
     * int CDECL lame_decode1(
            unsigned char*  mp3buf,
            int             len,
            short           pcm_l[],
            short           pcm_r[] ); 
     * </pre>
     */
    int lame_decode1(
            byte[]  mp3buf,
            int     len,
            short[] pcm_l,
            short[] pcm_r);
   
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_decode1(byte[],int,short[],short[])
     */ 
    int lame_decode1(
            byte[]  mp3buf,
            int     len,
            byte[] pcm_l,
            byte[] pcm_r);  
    
    /**
     * same as lame_decode1, but returns at most one frame and mp3 header data 
     
     * <pre>
     *    int CDECL lame_decode1_headers(
            unsigned char*   mp3buf,
            int              len,
            short            pcm_l[],
            short            pcm_r[],
            mp3data_struct*  mp3data );
     * </pre>
     */
    int lame_decode1_headers(
            byte[]   mp3buf,
            int      len,
            short[]  pcm_l,
            short[]  pcm_r,
            Mp3data  mp3data );
   
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_decode1_headers(byte[],int,short[],short[],Mp3data)
     */    
    int lame_decode1_headers(
            byte[]   mp3buf,
            int      len,
            byte[]  pcm_l,
            byte[]  pcm_r,
            Mp3data  mp3data );
   
    /**
     * same as lame_decode1_headers, but also returns enc_delay and enc_padding
       from VBR Info tag, (-1 if no info tag was found) 
     
     * <pre>
     *    int CDECL lame_decode1_headersB(
            unsigned char*   mp3buf,
            int              len,
            short            pcm_l[],
            short            pcm_r[],
            mp3data_struct*  mp3data,
            int              *enc_delay,
            int              *enc_padding ); 
     * </pre>     
     */
    int lame_decode1_headersB(
            byte[]   mp3buf,
            int      len,
            short[]    pcm_l,
            short[]    pcm_r,
            Mp3data  mp3data,
            int[]   enc_delay,
            int[]   enc_padding );
   
    /**
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #lame_decode1_headersB(byte[],int,short[],short[],Mp3data,int[],int[])
     */     
    int lame_decode1_headersB(
            byte[]   mp3buf,
            int      len,
            byte[]    pcm_l,
            byte[]    pcm_r,
             Mp3data  mp3data,
            int[]   enc_delay,
            int[]   enc_padding );  
    
    /**
     * cleanup call to exit decoder  
     *
     * <pre>int CDECL lame_decode_exit(void);</pre>
     */
    int lame_decode_exit();
   
}
