import com.lvdreamer.excel.ExcelGenerateLabel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class ExcelReaderTest {
    private static final Logger logger = LoggerFactory.getLogger(ExcelReaderTest.class);

    @Test
    public void read() throws IOException {
        // 设定Excel文件所在路径
        String excelFileName = "Dtemp.xlsx";
        new ExcelGenerateLabel().read(excelFileName);
    }

    @Test
    public void splitStr() {
        String a = "a、b";
        String[] res = a.split("[|;、]");
        System.out.println(Arrays.asList(res));
    }

    @Test
    public void time() {
        System.out.println(1600652530 * 1000);
        System.out.println(1600652530 * 1000L);

    }

    @Test
    public void ite() {
        int i = 0;
        for (int j = 0; j < 20; j++) {
            if (i++ >= 10) {
                break;
            }
            System.out.println(j);
        }

    }

    @Test
    public void timetest() {
        int i = 0;
        for (int j = 0; j < 200; j++) {
            System.out.println(System.currentTimeMillis());
        }

    }

    @Test
    public void timefomat() {
        System.out.println("20201212131415".substring(0, 8));
        System.out.println("20201212131415".substring(8));

    }

    @Test
    public void testThread() throws InterruptedException {
        Map<String, String> phoneResultMap = new HashMap<>();
        for (int i = 0; i < 5000000; i++) {
            phoneResultMap.put(i + "", i + "");
        }
        BlockingQueue<Runnable> saveRedisExecutorQueue = new LinkedBlockingQueue<Runnable>(20);
        // 保存redis线程池
        ThreadPoolExecutor saveRedisExecutor = new ThreadPoolExecutor(20, 20, 1, TimeUnit.HOURS, saveRedisExecutorQueue,
                new CustomerRejectedExecutionHandler());
//         Map<String, String> phoneResultDealMap = new HashMap<>();
        long startTime = System.currentTimeMillis();
//        Map<String, Jedis> redisLink = new HashMap<>();
        //初始化redis链接
//        initRedislink(redisLink, slotHostMap);
        Map<String, List<String>> redisSlotMap = new HashMap<>();
        TreeMap<Long, String> slotHostMap = new TreeMap<>();
        Map<String, String> jedisPoolMap = new TreeMap<>();
        ;

        for (int i = 0; i < 16; i++) {
            slotHostMap.put((long) i, i + "");
            jedisPoolMap.put(i + "", i + "");
        }

        for (Map.Entry<String, String> deal : phoneResultMap.entrySet()) {
            String phone = deal.getKey();
//            phoneResultDealMap.put(phone,deal.getValue());
            int slot = Integer.parseInt(phone) / 16;
            String hostAndPort = "";
            // 防止正好key在节点上 lower方法跳节点
            if (slotHostMap.containsKey(Long.valueOf(slot))) {
                hostAndPort = slotHostMap.get(Long.valueOf(slot));
            } else {
                Map.Entry<Long, String> en = slotHostMap.lowerEntry(Long.valueOf(slot));
                hostAndPort = en.getValue();
            }
            //使用每个节点单独存储数据
            if (redisSlotMap.containsKey(hostAndPort)) {
                redisSlotMap.get(hostAndPort).add(phone);
                //200条处理批量一次
                if (redisSlotMap.get(hostAndPort).size() == 200) {
                    addThread(saveRedisExecutor, redisSlotMap.get(hostAndPort), phoneResultMap);
                    //清除数据
                    redisSlotMap.get(hostAndPort).clear();
                }
            } else {
                List<String> phoneList = new ArrayList<>();
                phoneList.add(phone);
                redisSlotMap.put(hostAndPort, phoneList);
            }
        }
        for (Map.Entry<String, List<String>> data : redisSlotMap.entrySet()) {
            if (data.getKey().length() > 0) {
                addThread(saveRedisExecutor, data.getValue(), phoneResultMap);
            }
        }
        saveRedisExecutor.shutdown();
        while (!saveRedisExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
            //TODO 进度打印
            logger.info("redis已保存{}条！,耗时{}ms", System.currentTimeMillis() - startTime);
            startTime = System.currentTimeMillis();
        }
        logger.info("redis保存完毕，总共处理{}条！");
    }

    /**
     * 为了防止map被清空
     */
    public void addThread(ThreadPoolExecutor saveRedisExecutor, List<String> phoneList, Map<String, String> phoneResultMap) {
        List<String> list = new ArrayList<>();
        list.addAll(phoneList);
        saveRedisExecutor.submit(new EstateRedisSaveThread(list, phoneResultMap));
    }
}

class CustomerRejectedExecutionHandler implements RejectedExecutionHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomerRejectedExecutionHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            // 核心改造点，由blockingqueue的offer改成put阻塞方法
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            logger.error("error", e);
        }

    }

}

class EstateRedisSaveThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(EstateRedisSaveThread.class);

    private List<String> phoneList = new ArrayList<>();
    private Map<String, String> phoneResultMap = new HashMap<>();

    public EstateRedisSaveThread(List<String> phoneList, Map<String, String> phoneResultMap) {
        this.phoneList = phoneList;
        this.phoneResultMap = phoneResultMap;
    }

    @Override
    public void run() {
        System.out.println(phoneList.size());
    }
}