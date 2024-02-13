/*
 * Copyright (c) 2024 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.sound.sampled.lamejb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import net.sf.lamejb.jna.blade.BE_CONFIG_Format_LHV1;
import net.sf.lamejb.jna.blade.BE_CONFIG_Format_MP3;
import net.sf.lamejb.jna.blade.BE_VERSION;
import net.sf.lamejb.jna.std.LameGlobalFlags;
import net.sf.lamejb.jna.std.LameVersion;
import net.sf.lamejb.jna.std.Mp3data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.tritonus.share.TDebug;
import vavi.util.Debug;
import vavi.util.properties.annotation.Property;
import vavi.util.properties.annotation.PropsEntity;
import vavix.util.Checksum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vavi.sound.sampled.lamejb.LamejbAudioFileWriter.MP3;


/**
 * lamejb.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (umjammer)
 * @version 0.00 2024/02/13 umjammer initial version <br>
 */
@PropsEntity(url = "file://${user.dir}/local.properties")
class Test3 {

    static boolean localPropertiesExists() {
        return Files.exists(Paths.get("local.properties"));
    }

    static {
        System.setProperty("vavi.util.logging.VaviFormatter.extraClassMethod", "org\\.tritonus\\.share\\.TDebug#out");

        TDebug.TraceAudioConverter = false;
        TDebug.TraceCircularBuffer = false;
    }

    static final double volume = Double.parseDouble(System.getProperty("vavi.test.volume",  "0.2"));

    @Property
    String wav = "src/test/resources/test.wav";

    @Property
    String mp3 = "src/test/resources/lamejb.mp3";

    @BeforeAll
    static void setupAll() throws Exception {
        Files.createDirectories(Paths.get("tmp"));
    }

    @BeforeEach
    void setup() throws Exception {
        if (localPropertiesExists()) {
            PropsEntity.Util.bind(this);
        }
    }

    @Test
    @DisplayName("encoding")
    void test1() throws Exception {
        AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(Files.newInputStream(Path.of(wav))));
        AudioFormat inFormat = ais.getFormat();
Debug.println(inFormat);
        AudioFormat outFormat = new AudioFormat(
                LamejbFormatConversionProvider.MPEG1L3,
                -1f,
                -1,
                2,
                -1,
                -1f,
                false);
Debug.println(outFormat);
        AudioInputStream aout = AudioSystem.getAudioInputStream(outFormat, ais);

        Path out = Paths.get("tmp", "out.mp3");
        OutputStream fos = Files.newOutputStream(out, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        byte[] buf = new byte[8192];
        while (true) {
            int r = aout.read(buf, 0, buf.length);
            if (r < 0) {
                break;
            }
            fos.write(buf, 0, r);
        }
        fos.close();
        aout.close();

        assertEquals(Checksum.getChecksum(out), Checksum.getChecksum(Paths.get(mp3)));
    }

    @Test
    @DisplayName("writer")
    void test3() throws Exception {
        AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(Files.newInputStream(Path.of(wav))));
        AudioFormat inFormat = ais.getFormat();
Debug.println(inFormat);
        AudioFormat outFormat = new AudioFormat(
                LamejbFormatConversionProvider.MPEG1L3,
                inFormat.getSampleRate(),
                -1,
                inFormat.getChannels(),
                -1,
                -1f,
                false);
Debug.println(outFormat);
        AudioInputStream aout = AudioSystem.getAudioInputStream(outFormat, ais);
Debug.println(aout.getFormat());

        Path out2 = Paths.get("tmp", "out2.mp3");
        AudioSystem.write(aout, MP3, new BufferedOutputStream(Files.newOutputStream(out2)));

        assertEquals(Checksum.getChecksum(out2), Checksum.getChecksum(Paths.get(mp3)));
    }

    @Test
    @Disabled("jna method maker")
    void fields() throws Exception {
        Class<?>[] classes = new Class[] {
                BE_VERSION.class,
                BE_CONFIG_Format_LHV1.class,
                BE_CONFIG_Format_MP3.class,
                LameGlobalFlags.class,
                LameVersion.class,
                Mp3data.class
        };
        for (Class<?> c : classes) {
            System.out.println(c.getName());
            Arrays.stream(c.getDeclaredFields()).forEach(f -> {
                System.out.print("\"" + f.getName() + "\", ");
            });
            System.out.println();
        }
    }
}

/* */
