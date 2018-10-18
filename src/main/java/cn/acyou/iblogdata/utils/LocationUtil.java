package cn.acyou.iblogdata.utils;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-18 下午 04:46]
 * @since [天天健身/运动模块]
 **/

public class LocationUtil {
    private static double EARTH_RADIUS = 6378.137;//地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param myLat 自己
     * @param myLng 自己
     * @param distLat 目标
     * @param distLng 目标
     * @return 米
     */
    public static int getDistance(double myLat, double myLng, double distLat,
                                     double distLng) {
        double radLat1 = rad(myLat);
        double radLat2 = rad(distLat);
        double a = radLat1 - radLat2;
        double b = rad(myLng) - rad(distLng);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s*1000;
        return new Double(s).intValue();
    }

    public static void main(String[] args) {
        double mylat = 32.039192;
        double mylng = 118.819705;
        double distlat = 32.04012;
        double distlng = 118.805736;
        int distince = LocationUtil.getDistance(mylat, mylng, distlat, distlng);
        System.out.println(distince);
    }
}