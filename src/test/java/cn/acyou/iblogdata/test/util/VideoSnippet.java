package cn.acyou.iblogdata.test.util;

import it.sauronsoftware.jave.*;

import java.io.File;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-23 下午 05:33]
 **/
public class VideoSnippet {

    public static void main(String[] args) {
        File source = new File("F:\\iotest\\123.mp4");
        File target = new File("F:\\iotest\\44.mp4");
        try {
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");// libfaac PGM编码
            audio.setBitRate(56000);// 音频比特率
            audio.setChannels(1);// 声道
            audio.setSamplingRate(22050);// 采样率
            VideoAttributes video = new VideoAttributes();
            video.setCodec("mpeg4");// 视频编码
            video.setBitRate(800000);// 视频比特率
            video.setFrameRate(20);// 帧率
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
