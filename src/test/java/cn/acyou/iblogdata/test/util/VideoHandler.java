package cn.acyou.iblogdata.test.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-20 下午 05:50]
 **/
public class VideoHandler {

    public static void main(String[] args) throws EncoderException {

        File file = new File("F:\\iotest\\321.mp4");
        System.out.println(file.getName());
        System.out.println(getDuration(file));

    }

    /**
     * 获取视频文件的播放长度
     * @param source
     * @return 单位为s
     * @throws EncoderException
     */
    public static long getDuration(File source) throws EncoderException {
        Encoder encoder = new Encoder();
        MultimediaInfo m = encoder.getInfo(source);
        return m.getDuration()/1000;
    }

    public long getDuration(String videoPath) throws EncoderException {
        File source = new File(videoPath);
        return getDuration(source);
    }


}
