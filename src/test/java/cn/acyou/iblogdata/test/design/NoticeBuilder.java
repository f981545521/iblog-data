package cn.acyou.iblogdata.test.design;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-17 下午 03:36]
 **/
public class NoticeBuilder {

    public static void main(String[] args) {
        RewardNotice notice = NoticeBuilder.prepareRewardNotice();
        System.out.println(notice);
    }

    public static GeneralNotice prepareGeneralNotice (){
        GeneralNotice general = new GeneralNotice();
        general.setTargetId("1000");
        general.setDetailUrl("http://xxx.acyou.cn");
        return general;
    }

    public static RewardNotice prepareRewardNotice(){
        RewardNotice rewardNotice = new RewardNotice();
        rewardNotice.setTargetId("100032");
        rewardNotice.setTargetNickname("西据了解");
        rewardNotice.setDetailUrl("http://xxx.acyou.cn/xxxx");
        return rewardNotice;
    }


}
