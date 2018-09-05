### 订制jave

#### FFmpeg官网：http://www.ffmpeg.org/download.html

由于该项目早就停止维护，现在已经不适合诸多需求，

在这里，在FFmpeg的基础上，扩展了功能：

- 读取视频旋转角度


解决问题：
1. 手机拍摄的视频旋转90°

2. 在Linux上无法使用

存在问题：
1. 由于版本变化，输出信息需要重写！

官方参考手册：
http://www.sauronsoftware.it/projects/jave/manual.php?PHPSESSID=0m013dv1ahsrhvkvp688slkd82

``` 
PS F:\iotest> .\ffmpeg.exe -i 123.mp4
FFmpeg version SVN-r11143, Copyright (c) 2000-2007 Fabrice Bellard, et al.
  configuration: --enable-gpl --enable-pp --enable-swscaler --enable-pthreads --enable-liba52 --enable-avisynth --enable-libamr-nb --enable-libamr-wb --enable-libfaac --enable-libfaad --enable-libgsm --enable-libmp3lame --enable-libnut --enable-libtheora --enable-libvorbis --enable-libx264 --enable-libxvid --cpu=i686 --enable-memalign-hack --extra-ldflags=-static
  libavutil version: 49.5.0
  libavcodec version: 51.48.0
  libavformat version: 52.1.0
  built on Dec  3 2007 17:42:57, gcc: 4.2.2 (mingw32)
Input #0, mov,mp4,m4a,3gp,3g2,mj2, from '123.mp4':
  Duration: 00:01:51.5, start: 0.000000, bitrate: 641 kb/s
    Stream #0.0(und): Video: h264, yuv420p, 1280x720 [PAR 0:1 DAR 0:1], 24.00 tb(r)
    Stream #0.1(eng): Audio: mpeg4aac, 48000 Hz, stereo
Must supply at least one output file
PS F:\iotest> .\nffmpeg.exe -i 123.mp4
ffmpeg version N-91789-g11cec34829 Copyright (c) 2000-2018 the FFmpeg developers
  built with gcc 8.2.1 (GCC) 20180813
  configuration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-libsoxr --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --enable-libxvid --enable-libaom --enable-libmfx --enable-amf --enable-ffnvcodec --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth
  libavutil      56. 19.100 / 56. 19.100
  libavcodec     58. 27.101 / 58. 27.101
  libavformat    58. 17.106 / 58. 17.106
  libavdevice    58.  4.101 / 58.  4.101
  libavfilter     7. 26.100 /  7. 26.100
  libswscale      5.  2.100 /  5.  2.100
  libswresample   3.  2.100 /  3.  2.100
  libpostproc    55.  2.100 / 55.  2.100
Input #0, mov,mp4,m4a,3gp,3g2,mj2, from '123.mp4':
  Metadata:
    major_brand     : isom
    minor_version   : 512
    compatible_brands: isomiso2avc1mp41
    encoder         : Lavf57.41.100
  Duration: 00:01:51.57, start: 0.000000, bitrate: 641 kb/s
    Stream #0:0(und): Video: h264 (High) (avc1 / 0x31637661), yuv420p, 1280x720, 589 kb/s, 24 fps, 24 tbr, 12288 tbn, 48 tbc (default)
    Metadata:
      handler_name    : VideoHandler
    Stream #0:1(eng): Audio: aac (HE-AAC) (mp4a / 0x6134706D), 48000 Hz, stereo, fltp, 48 kb/s (default)
    Metadata:
      handler_name    : SoundHandler
At least one output file must be specified
PS F:\iotest>


PS F:\iotest> .\nffmpeg.exe -i vid.mp4
ffmpeg version N-91789-g11cec34829 Copyright (c) 2000-2018 the FFmpeg developers
  built with gcc 8.2.1 (GCC) 20180813
  configuration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-libsoxr --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --enable-libxvid --enable-libaom --enable-libmfx --enable-amf --enable-ffnvcodec --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth
  libavutil      56. 19.100 / 56. 19.100
  libavcodec     58. 27.101 / 58. 27.101
  libavformat    58. 17.106 / 58. 17.106
  libavdevice    58.  4.101 / 58.  4.101
  libavfilter     7. 26.100 /  7. 26.100
  libswscale      5.  2.100 /  5.  2.100
  libswresample   3.  2.100 /  3.  2.100
  libpostproc    55.  2.100 / 55.  2.100
Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'vid.mp4':
  Metadata:
    major_brand     : mp42
    minor_version   : 0
    compatible_brands: isommp42
    creation_time   : 2018-09-03T13:02:08.000000Z
    location        : +31.9316+118.8404/
    location-eng    : +31.9316+118.8404/
    com.android.version: 8.0.0
  Duration: 00:00:03.11, start: 0.000000, bitrate: 15256 kb/s
    Stream #0:0(eng): Video: h264 (Baseline) (avc1 / 0x31637661), yuvj420p(pc, smpte170m), 1280x720, 14121 kb/s, SAR 1:1 DAR 16:9, 29.30 fps, 29.33 tbr, 90k tbn, 180k tbc (default)
    Metadata:
      rotate          : 90
      creation_time   : 2018-09-03T13:02:08.000000Z
      handler_name    : VideoHandle
    Side data:
      displaymatrix: rotation of -90.00 degrees
    Stream #0:1(eng): Audio: aac (LC) (mp4a / 0x6134706D), 48000 Hz, stereo, fltp, 97 kb/s (default)
    Metadata:
      creation_time   : 2018-09-03T13:02:08.000000Z
      handler_name    : SoundHandle
At least one output file must be specified
PS F:\iotest> .\nffmpeg.exe -i xtr.mp3
ffmpeg version N-91789-g11cec34829 Copyright (c) 2000-2018 the FFmpeg developers
  built with gcc 8.2.1 (GCC) 20180813
  configuration: --enable-gpl --enable-version3 --enable-sdl2 --enable-fontconfig --enable-gnutls --enable-iconv --enable-libass --enable-libbluray --enable-libfreetype --enable-libmp3lame --enable-libopencore-amrnb --enable-libopencore-amrwb --enable-libopenjpeg --enable-libopus --enable-libshine --enable-libsnappy --enable-libsoxr --enable-libtheora --enable-libtwolame --enable-libvpx --enable-libwavpack --enable-libwebp --enable-libx264 --enable-libx265 --enable-libxml2 --enable-libzimg --enable-lzma --enable-zlib --enable-gmp --enable-libvidstab --enable-libvorbis --enable-libvo-amrwbenc --enable-libmysofa --enable-libspeex --enable-libxvid --enable-libaom --enable-libmfx --enable-amf --enable-ffnvcodec --enable-cuvid --enable-d3d11va --enable-nvenc --enable-nvdec --enable-dxva2 --enable-avisynth
  libavutil      56. 19.100 / 56. 19.100
  libavcodec     58. 27.101 / 58. 27.101
  libavformat    58. 17.106 / 58. 17.106
  libavdevice    58.  4.101 / 58.  4.101
  libavfilter     7. 26.100 /  7. 26.100
  libswscale      5.  2.100 /  5.  2.100
  libswresample   3.  2.100 /  3.  2.100
  libpostproc    55.  2.100 / 55.  2.100
Input #0, mp3, from 'xtr.mp3':
  Duration: 00:05:58.01, start: 0.025057, bitrate: 128 kb/s
    Stream #0:0: Audio: mp3, 44100 Hz, stereo, fltp, 128 kb/s
    Metadata:
      encoder         : LAME3.99r
    Side data:
      replaygain: track gain - -7.900000, track peak - unknown, album gain - unknown, album peak - unknown,
At least one output file must be specified
PS F:\iotest>



C:\Users\youfang\AppData\Local\Temp\jave-1\ffmpeg.exe -i F:\iotest\vid.mp4 -vcodec mpeg4 -b 800000 -metadata:s:v rotate="0" -r 15 -acodec libmp3lame -ab 56000 -ac 2 -ar 22050 -f mp4 -y F:\iotest\t444.mp4 

```