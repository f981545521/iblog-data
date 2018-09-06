package cn.acyou.iblogdata.test.util;

import com.google.common.collect.Maps;
import it.sauronsoftware.jave.*;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-23 下午 05:33]
 **/
public class VideoSnippet {

    public static void main(String[] args) throws IOException {
        File source = new File("F:\\iotest\\123.mp4");
        File target = new File("F:\\iotest\\te123.mp4");
/*      Files.copy(source.toPath(), target.toPath());*/
        try {
            Encoder encoder = new Encoder();
            //MultimediaInfo mediaInfo = encoder.getInfo(source);
           // VideoSize vs = mediaInfo.getVideo().getSize();
            //System.out.println(vs);
            //System.out.println(mediaInfo.getFormat());
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
            video.setFrameRate(25);// 帧率
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Map<String, String> extraParam = Maps.newHashMap();
            extraParam.put("-metadata:s:v", "rotate=\"0\"");
            attrs.setExtraParam(extraParam);
            encoder.encode(source, target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test1() throws EncoderException {
        File source = new File("F:\\iotest\\vid.mp4");
        Encoder encoder = new Encoder();
        MultimediaInfo mediaInfo = encoder.getInfo(source);
        System.out.println(mediaInfo);
        //String[] decf = encoder.getSupportedDecodingFormats();
        //System.out.println(Arrays.toString(decf));
    }

    @Test
    public void test2() throws EncoderException {
        File source = new File("F:\\iotest\\123.mp4");
        File target = new File("F:\\iotest\\t1234.mp4");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(new Integer(128000));
        audio.setSamplingRate(new Integer(44100));
        audio.setChannels(new Integer(2));
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(new Integer(160000));
        video.setFrameRate(new Integer(15));
        video.setSize(new VideoSize(176, 144));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
    }
    @Test
    public void testAmr2mp3() throws EncoderException {
        File source = new File("F:\\iotest\\zt.amr");
        File target = new File("F:\\iotest\\98k.mp3");
        Encoder encoder = new Encoder();
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setSamplingRate(44100);
        audio.setChannels(2);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        encoder.encode(source, target, attrs);
    }
}
