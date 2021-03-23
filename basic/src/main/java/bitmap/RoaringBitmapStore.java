package bitmap;

import com.lvdreamer.basic.LogbackPrint;
import org.apache.lucene.util.RamUsageEstimator;
import org.roaringbitmap.longlong.Roaring64NavigableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoaringBitmapStore {
    private static final Logger logger = LoggerFactory.getLogger(LogbackPrint.class);

    /**
     * 返回手机号码
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,178".split(",");
    static Object lock = new Object();

    private static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String middle = String.valueOf(getNum(1, 999) + 10000).substring(1);
        String last = String.valueOf(getNum(1, 9999) + 10000).substring(1);
        return first + middle + last;
    }


    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static void main(String[] args) throws InterruptedException {
/*        Set<Long> phoneSet = new HashSet<>(5000000);
        while (phoneSet.size() < 5000000) {
            phoneSet.add(Long.parseLong(getTel()));
        }
        System.out.println(phoneSet.size());
        System.out.println("set空间占用:" + RamUsageEstimator.humanSizeOf(phoneSet));*/
        Roaring64NavigableMap roaringBitmap = new Roaring64NavigableMap();
        new Thread(() -> {
            while (true) {
                logger.info("定时输出bitMap大小：{},空间占用:{}", roaringBitmap.getIntCardinality(), RamUsageEstimator.humanSizeOf(roaringBitmap));
                try {
                    Thread.sleep(30000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        int i = 1;
        int j = 10000;
        while (i++ < j || ((j = 10000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("1w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 50000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("5w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 100000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("10w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 200000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("20w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 500000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("50w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 1000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("100w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 2000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("200w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 5000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("500w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 10000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("1000w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 20000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("2000w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 50000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("5000w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        while (i++ < j || ((j = 130000000 - roaringBitmap.getIntCardinality()) > 0)) {
            roaringBitmap.add(Long.parseLong(getTel()));
            if (i > j) {
                i = 1;
            }
        }
        logger.info("13000w bitMap空间占用:{},大小：{}", RamUsageEstimator.humanSizeOf(roaringBitmap), roaringBitmap.getIntCardinality());
        //无限期等待
        while (true) {
            synchronized (lock) {
                System.out.println("无限期等待中...");
                lock.wait(); //等待，直到其它线程调用 lock.notify()
            }
        }

    }
}
/**
 * [SUB-INFO PRINT]2021-02-22 20:43:07.572 [Thread-0] INFO  com.lvdreamer.basic.LogbackPrint - 定时输出bitMap大小：0,空间占用:344.1 KB
 * [SUB-INFO PRINT]2021-02-22 20:43:07.975 [main] INFO  com.lvdreamer.basic.LogbackPrint - 100w bitMap空间占用:2.7 MB,大小：1000000
 * [SUB-INFO PRINT]2021-02-22 20:43:10.269 [main] INFO  com.lvdreamer.basic.LogbackPrint - 500w bitMap空间占用:10.2 MB,大小：5000000
 * [SUB-INFO PRINT]2021-02-22 20:43:13.095 [main] INFO  com.lvdreamer.basic.LogbackPrint - 1000w bitMap空间占用:15.6 MB,大小：10000000
 * [SUB-INFO PRINT]2021-02-22 20:43:16.464 [main] INFO  com.lvdreamer.basic.LogbackPrint - 2000w bitMap空间占用:15.6 MB,大小：20000000
 * [SUB-INFO PRINT]2021-02-22 20:43:28.737 [main] INFO  com.lvdreamer.basic.LogbackPrint - 5000w bitMap空间占用:15.7 MB,大小：50000000
 * [SUB-INFO PRINT]2021-02-22 20:43:37.577 [Thread-0] INFO  com.lvdreamer.basic.LogbackPrint - 定时输出bitMap大小：66258879,空间占用:15.7 MB
 * [SUB-INFO PRINT]2021-02-22 20:44:07.578 [Thread-0] INFO  com.lvdreamer.basic.LogbackPrint - 定时输出bitMap大小：100417007,空间占用:15.7 MB
 * [SUB-INFO PRINT]2021-02-22 20:44:37.579 [Thread-0] INFO  com.lvdreamer.basic.LogbackPrint - 定时输出bitMap大小：116349924,空间占用:15.7 MB
 * [SUB-INFO PRINT]2021-02-22 20:45:07.579 [Thread-0] INFO  com.lvdreamer.basic.LogbackPrint - 定时输出bitMap大小：123616886,空间占用:15.7 MB
 **/