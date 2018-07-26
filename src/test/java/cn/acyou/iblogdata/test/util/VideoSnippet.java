package cn.acyou.iblogdata.test.util;

import it.sauronsoftware.jave.*;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-23 下午 05:33]
 **/
public class VideoSnippet {

    public static void main(String[] args) throws IOException {
        File source = new File("F:\\iotest\\123.mp4");
        File target = new File("F:\\iotest\\44.mp4");
/*      Files.copy(source.toPath(), target.toPath());*/
        try {
            Encoder encoder = new Encoder();
            MultimediaInfo mediaInfo = encoder.getInfo(source);
            VideoSize vs = mediaInfo.getVideo().getSize();
            System.out.println(vs);
            System.out.println(mediaInfo.getFormat());
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");// libfaac PGM编码
            audio.setBitRate(56000);// 音频比特率
            audio.setChannels(2);// 声道
            audio.setSamplingRate(22050);// 采样率
            VideoAttributes video = new VideoAttributes();
            //VideoSize vss = new VideoSize(1080, 1920);
            //video.setSize(vss);
            video.setCodec("mpeg4");// 视频编码
            video.setBitRate(800000);// 视频比特率
            video.setFrameRate(15);// 帧率
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test1() throws EncoderException {
        File source = new File("F:\\iotest\\44.mp4");
        Encoder encoder = new Encoder();
        MultimediaInfo mediaInfo = encoder.getInfo(source);
        System.out.println(mediaInfo.getVideo().getSize());
    }
}