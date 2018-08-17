package cn.acyou.iblogdata.test.design;

import lombok.Data;

import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-17 下午 04:08]
 **/
@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = -3225493998957954478L;
    private String targetId;
    private String templateId;
    private String detailUrl;
    public static void main(String[] args) {
        GeneralNotice notice = Notice.prepareGeneralNotice();
        notice.setTargetId("100032");
        //notice.setTargetNickname("西据了解");
        notice.setDetailUrl("http://xxx.acyou.cn/xxxx");
        System.out.println(notice);
    }


    public static GeneralNotice prepareGeneralNotice(){
        return new GeneralNotice();
    }
    public static RewardNotice prepareRewardNotice(){
        return new RewardNotice();
    }

    static class GeneralNotice extends Notice{
        private static final long serialVersionUID = -6440979926866647598L;
    }

    static class RewardNotice extends Notice{
        private static final long serialVersionUID = 9200945381307520537L;
        private String targetNickname;

        public String getTargetNickname() {
            return targetNickname;
        }

        public void setTargetNickname(String targetNickname) {
            this.targetNickname = targetNickname;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("RewardNotice{");
            sb.append("targetId='").append(super.targetId).append('\'');
            sb.append(", templateId='").append(super.templateId).append('\'');
            sb.append(", detailUrl='").append(super.detailUrl).append('\'');
            sb.append(", targetNickname='").append(targetNickname).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }



}
