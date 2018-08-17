package cn.acyou.iblogdata.test.design;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-17 下午 03:59]
 **/
public class NoticeFactory {
    public static void main(String[] args) {
        GeneralNotice notice = NoticeFactory.getGeneralNotice().setTargetId("123").setTemplateType(1).setDetailUrl("https://xxxx.com/xxxx");
        System.out.println(notice);

    }

    public static GeneralNotice getGeneralNotice(){
        return new GeneralNotice();
    }

    public static RewardNotice getRewardNotice(){
        return new RewardNotice();
    }
}
