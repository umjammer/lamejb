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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sf.lamejb.LamejbException;
import net.sf.lamejb.jna.std.Lame;
import net.sf.lamejb.jna.std.MpegMode;


/**
 * WAV file/stream encoder
 */
public class StreamEncoderWAVImpl extends StreamEncoderImpl {

    public StreamEncoderWAVImpl(String sourceFile) {
        super(sourceFile);
    }

    public StreamEncoderWAVImpl(InputStream input) {
        super(input);
    }

    public static boolean isWAV(String file) {
        if (file == null)
            throw new IllegalArgumentException("file == null");

        FileInputStream stream;
        try {
            stream = new FileInputStream(file);
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }

        try {
            return readString("RIFF", stream);
        } catch (IOException ex) {
            closeStream(stream);
            throw new LamejbException(ex);
        }
    }

    public static boolean isWAV(InputStream stream) {
        if (stream == null)
            throw new IllegalArgumentException("stream == null");

        try {
            return readString("RIFF", stream);
        } catch (IOException ex) {
            closeStream(stream);
            throw new LamejbException(ex);
        }
    }

    @Override
    public void parseHeader() {
        // http://ccrma.stanford.edu/courses/422/projects/WaveFormat/
        try {
            if (!readString("RIFF", sourceStream)) // 4 bytes
                throw new RuntimeException("Not a WAV stream");

            long skipped = sourceStream.skip(4); // 4 bytes (ChunkSize)

            if (!readString("WAVE", sourceStream)) // 4 bytes
                throw new RuntimeException("Not a WAV stream");

            if (!readString("fmt ", sourceStream)) // 4 bytes
                throw new RuntimeException("Not a WAV stream");

            long subchunk1Size = readUInt(sourceStream);  // 4 byte
            if (subchunk1Size != 16)
                throw new RuntimeException("Expected 16=PCM, bad WAV header");

            int audioFormat = readUShort(sourceStream);  // 2 byte
            if (audioFormat != 1)
                throw new RuntimeException("WAV stream is compressed");

            int numChannels = readUShort(sourceStream);  // 2 byte
            if ((numChannels < 1) || (numChannels > 2))
                throw new RuntimeException("Unsupported number of channels: " + numChannels);

            int rc;
            rc = Lame.INSTANCE.lame_set_num_channels(flags, numChannels);
            LameUtil.checkError(rc);

            int mpegMode;
            if (numChannels == 2) mpegMode = MpegMode.JOINT_STEREO;
            else mpegMode = MpegMode.MONO;
            rc = Lame.INSTANCE.lame_set_mode(flags, mpegMode);
            LameUtil.checkError(rc);

            long sampleRate = readUInt(sourceStream);  // 4 byte
            rc = Lame.INSTANCE.lame_set_in_samplerate(flags, (int) sampleRate); // Ex 44100
            LameUtil.checkError(rc);

            skipped = sourceStream.skip(4); // 4 bytes (ByteRate)

            skipped = sourceStream.skip(2); // 2 bytes (BlockAlign)            

            int bitsPerSample = readUShort(sourceStream);  // 2 byte
            if (bitsPerSample != 16)
                throw new RuntimeException("Unsupported number of bits per sample (must be 16): " + bitsPerSample);

            if (!readString("data", sourceStream)) // 4 bytes
                throw new RuntimeException("Not a WAV stream");

            skipped = sourceStream.skip(4); // 4 bytes (Subchunk2Size)            

            // Total header: 44
        } catch (IOException ex) {
            throw new LamejbException(ex);
        }
    }

    public static int readUShort(InputStream stream) throws IOException {
        byte[] buff = new byte[2];
        int read = stream.read(buff); // 2 byte
        return toUShort(buff);
    }

    public static long readUInt(InputStream stream) throws IOException {
        byte[] buff = new byte[4];
        int read = stream.read(buff); // 4 byte
        return toUInt(buff);
    }

    public static boolean readString(String name, InputStream stream) throws IOException {
        byte[] nameBuff = new byte[name.length()];
        int read = stream.read(nameBuff);
        StringBuilder nameReaded = new StringBuilder();
        for (byte b : nameBuff) nameReaded.append((char) b);

        return nameReaded.toString().equals(name);
    }

    public static int toUShort(byte[] num) {
        // big endian format
        if (num.length != 2) throw new LamejbException("Internal Error");

        return (toUInt(num[0]) | (toUInt(num[1]) << 8));
    }

    public static long toUInt(byte[] num) {
        // big endian format
        if (num.length != 4) throw new LamejbException("Internal Error");
        return toULong(num[0]) | (toULong(num[1]) << 8) | (toULong(num[2]) << 16) | (toULong(num[3]) << 24);
    }

    public static short toUShort(byte value) {
        return (short) (((short) value) & ((short) 0x00FF));
    }

    public static int toUInt(byte value) {
        return toUShort(value);
    }

    public static long toULong(byte value) {
        return toUShort(value);
    }
}
