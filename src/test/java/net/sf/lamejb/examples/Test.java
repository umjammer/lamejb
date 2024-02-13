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

package net.sf.lamejb.examples;



public class Test
{
    
    public Test()
    {
    }  
            
    public static void main(String[] args) throws Exception
    {
        String wavFile = args[0];
        String mp3FileBase = args[1];       

        TestBladeAPI testBladeAPI = new TestBladeAPI(); 
        testBladeAPI.testPlainAPI(wavFile, getMP3FileName(mp3FileBase,1));
        testBladeAPI.testOOPAPI(wavFile, getMP3FileName(mp3FileBase,2));

        TestAPI testAPI = new TestAPI(); 
        testAPI.testPlainAPI(wavFile,getMP3FileName(mp3FileBase,3));
        testAPI.testOOPAPIStreamEncoder(wavFile,getMP3FileName(mp3FileBase,4));  
        testAPI.testOOPAPIStreamEncoderProgressive(wavFile,getMP3FileName(mp3FileBase,5));        
        testAPI.testOOPAPIGenericEncoder(wavFile,getMP3FileName(mp3FileBase,6));      

        TestCodec testCodec = new TestCodec();
        testCodec.encodeFile(wavFile, getMP3FileName(mp3FileBase,7));
        testCodec.encodeStream(wavFile, getMP3FileName(mp3FileBase,8));
    }

    
    public static String getMP3FileName(String mp3FileBase,int num)
    {
        return mp3FileBase + "_" + num + ".mp3";
    }
/*    
    public static void checkFiles(String mp3File,String mp3RefFile) throws IOException
    {
        checkFiles(mp3File,mp3RefFile,true);
    }
    
    public static void checkFiles(String mp3File,String mp3RefFile, boolean fully) throws IOException
    {
        File fileRes = new File(mp3File);
        File fileRef = new File(mp3RefFile);
        
        if (fileRef.length() == 0) 
            return; // Mounted Windows directory, cannot check
        
        if (fileRef.length() != fileRes.length())
            throw new RuntimeException("Bad Test, different files");
        
        if (!fully) return;
        
        BufferedInputStream inputStream1 = new BufferedInputStream(new FileInputStream(mp3File));
        BufferedInputStream inputStream2 = new BufferedInputStream(new FileInputStream(mp3RefFile));

        byte[] inputBuff1 = new byte[10*1024];
        byte[] inputBuff2 = new byte[10*1024];        
        
        int read;  
        do
        {
            read = inputStream1.read(inputBuff1,0,inputBuff1.length);                     
            read = inputStream2.read(inputBuff2,0,inputBuff2.length);
           
            for(int i = 0; i < read; i++)
                if (inputBuff1[i] != inputBuff2[i])   
                {
                    throw new RuntimeException("Bad Test, different files");
                }
        }
        while(read != 0);

        inputStream1.close();
        inputStream2.close();        
    }    
*/    
}
