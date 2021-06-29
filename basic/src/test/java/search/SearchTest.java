package search;

import org.apache.lucene.util.RamUsageEstimator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SearchTest {

    /**
     * 数据加载完成，数组空间占用:6.3 GB,HashMap空间占用:10.9 GB
     * 数组获取第9千万个数据:a90000000耗时:0
     * HashMap获取第9千万个数据:a90000000耗时:0
     * 数组获取第8千万-9千万个数据:a89999999耗时:5
     * HashMap获取第8千万-9千万个数据:a89999999耗时:146
     */
    @Test
    public void compareSearch() {
        final int dataSize = 100000000;
        String[] sArray = new String[100000000];
        Map<Integer, String> map = new HashMap<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            String v = "a" + String.valueOf(i);
            sArray[i] = v;
            map.put(i, v);
        }
        System.out.println("数据加载完成，数组空间占用:" + RamUsageEstimator.humanSizeOf(sArray) + ",HashMap空间占用:" + RamUsageEstimator.humanSizeOf(map));
        long start = System.currentTimeMillis();
        String s9000 = sArray[90000000];
        System.out.println("数组获取第9千万个数据:" + s9000 + "耗时:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        s9000 = map.get(90000000);
        System.out.println("HashMap获取第9千万个数据:" + s9000 + "耗时:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int i = 80000000; i < 90000000; i++) {
            s9000 = sArray[i];
        }
        System.out.println("数组获取第8千万-9千万个数据:" + s9000 + "耗时:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        for (int i = 80000000; i < 90000000; i++) {
            s9000 = map.get(i);
        }
        System.out.println("HashMap获取第8千万-9千万个数据:" + s9000 + "耗时:" + (System.currentTimeMillis() - start));
    }

}
