package com.web.util;

import java.util.Date;

public class RedisSortedSetUtil {

    public static void main(String[] args) {
        Date endTime = TimeUtil.parse("2050-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(endTime.getTime() / 1000);

        long score1 = 1040000;
        long time_score1 = endTime.getTime() / 1000 - TimeUtil.getCurrentSecond();
        long newS1 = ((score1 & 0xfffff) << 32) | (time_score1 & 0xffffffff);
        System.out.println("newScore = " + newS1);
        System.out.println("score = " + ((newS1 >> 32) & 0xfffff));

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }

        long score2 = 1040000;
        long time_score2 = endTime.getTime() / 1000 - TimeUtil.getCurrentSecond();
        long newS2 = ((score2 & 0xfffff) << 32) | (time_score2 & 0xffffffff);
        System.out.println(newS2);
        System.out.println((newS2 >> 32) & 0xfffff);

        System.out.println(time_score2);
        System.out.println(Math.pow(2, 32));


        //        long score2 = 1040000;
        //        long time_score2 = endTime.getTime() / 1000 - TimeUtil.getCurrentSecond();
        //        long newS2 = ((score2 & 0xfffff) << 32) | (time_score2 & 0xffffffff);
        //        System.out.println(newS2);
        //        System.out.println((newS2 >> 32) & 0xfffff);

        //        long cur = System.currentTimeMillis();
        //        for (int i = 0; i < 10000000; i++) {
        //            calc1(score1);
        //        }
        //        System.out.println(System.currentTimeMillis() - cur);
        //
        //        cur = System.currentTimeMillis();
        //        for (int i = 0; i < 10000000; i++) {
        //            calc2(score1);
        //        }
        //        System.out.println(System.currentTimeMillis() - cur);
    }

    public static double calc1(long score) {
        double newScore = score + (9999999999d - TimeUtil.getCurrentSecond()) / 10000000000d;
        return newScore;
    }

    public static double calc2(long score) {
        long time_score2 = 2524579200L - TimeUtil.getCurrentSecond();
        long newS2 = ((score & 0xfffff) << 32) | (time_score2 & 0xffffffff);
        return newS2;
    }
}
