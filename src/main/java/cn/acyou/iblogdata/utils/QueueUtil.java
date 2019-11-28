package cn.acyou.iblogdata.utils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2019/11/28]
 **/
public class QueueUtil {

    public static <E> List<E> dequePop(Deque<E> objDeque, int count) {
        List<E> dequeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            E e = objDeque.poll();
            if (e != null) {
                dequeList.add(e);
            }
        }
        return dequeList;
    }


}
