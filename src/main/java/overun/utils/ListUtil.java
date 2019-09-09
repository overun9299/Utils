package overun.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ListUtil
 * @Description: list集合相关自己封装方法工具类（用来切分数据量过大的list）
 * @author: 壹米滴答-西安-ZhangPY
 * @version: V1.0
 * @date: 2019/9/9 14:27
 * @Copyright: 2019 www.yimidida.com Inc. All rights reserved.
 */
public class ListUtil {

    /**
     *  默认批量个数
     */
    private static final int BATCH_SIZE = 500;

    /**
     * @MethodName split
     * @Description list分段
     * @param list
     * @return
     */
    public static <T> List<List<T>> split(List<T> list)
    {
        int blockSize = (list.size() % BATCH_SIZE) == 0 ? (list.size() / BATCH_SIZE) : (list.size() / BATCH_SIZE + 1);
        return split(list,blockSize);
    }

    /**
     * @MethodName split
     * @Description list分段
     * @param source
     * @param n
     * @return
     */
    public static <T> List<List<T>> split(List<T> source, int n)
    {
        List<List<T>> result = new ArrayList<List<T>>();
        /** (先计算出余数) */
        int remaider = source.size() % n;
        /** 然后是商 */
        int number = source.size() / n;
        /** 偏移量 */
        int offset = 0;
        for (int i = 0; i < n; i++)
        {
            List<T> value = null;
            if (remaider > 0)
            {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            }
            else
            {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}
