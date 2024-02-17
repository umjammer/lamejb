[![Release](https://jitpack.io/v/umjammer/lamejb.svg)](https://jitpack.io/#umjammer/lamejb)
[![Java CI](https://github.com/umjammer/lamejb/actions/workflows/maven.yml/badge.svg)](https://github.com/umjammer/lamejb/actions/workflows/maven.yml)
[![CodeQL](https://github.com/umjammer/lamejb/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/umjammer/lamejb/actions/workflows/codeql-analysis.yml)
![Java](https://img.shields.io/badge/Java-17-b07219)

# lamejb

<img src="https://lame.sourceforge.io/images/logo.gif" width="120" /><sub><a href="http://www.brightercreative.co.uk/">© Sam Fisher</a></sub>

MP3 Java Sound SPI over lamejb (jna)

## Install

### maven

 https://jitpack.io/#umjammer/lamejb

### lame

e.g.

```shell
$ brew install lame
```

### jvm argument

e.g.

`-Djna.library.path=/opt/homebrew/lib`

## Usage

 [sample](src/test/java/vavi/sound/sampled/lamejb/Test3.java)

## References

* https://lame.sourceforge.io/

## TODO

* ~~encoding parameter by properties~~
* ~~AudioFileWriter sample~~
* rename to vavi-sound-mp3-lamejb

---

# [Original](https://sourceforge.net/projects/lamejb/)

Java wrapper for the LAME mp3 encoding library. 

This is not a Java port of LAME, but only a set of wrapper classes based on 
JNA (Java Native Access). Tested on Windows and Linux, using lame 3.98, it 
should work on other platforms too.
The LAME library is not provided, the source code can be downloaded from Sourceforge 
or you can probably find binary distributions available from other web sites.  

For usage examples have a look at the source code in the examples subdirectory.

This software is released under the GNU Lesser General Public License and is
based on LAMEOnJ by Jose Maria Arranz. Unfortunately LAMEOnJ, which is itself 
released under the LGPL, is based on a JNI wrapper library which is not free 
software (as in free beer and free speech...) and for that reason this project 
was born.
