package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorCompare {

    public static void main(String[] args) throws InterruptedException {
        final int threadNum = 50;
        CountDownLatch latch = new CountDownLatch(threadNum);
        LatchDemo ld = new LatchDemo(latch);
        //记录开始时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(ld).start();
        }
        try {
            //调用await()方法，阻塞主线程，当上述启动的分线程都执行完后，主线程才会被放行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //记录下结束时间
        long end = System.currentTimeMillis();
        System.out.println("Thread方式消耗的时间为（毫秒）：" + (end - start));
        doWatch(Executors.newSingleThreadExecutor(), threadNum);
        doWatch(Executors.newFixedThreadPool(10), threadNum);
        doWatch(Executors.newCachedThreadPool(), threadNum);

    }

    public static void doWatch(ExecutorService executorService, int threadNum) {
        List<Runnable> taskList = new ArrayList<>(threadNum);
        CountDownLatch latch = new CountDownLatch(threadNum);
        //通过循环创建并启动五个线程
        for (int i = 0; i < threadNum; i++) {
            taskList.add(new LatchDemo(latch));
        }
        //记录开始时间
        long start = System.currentTimeMillis();
        for (Runnable task : taskList) {
            executorService.submit(task);
        }
        try {
            //调用await()方法，阻塞主线程，当上述启动的5个分线程都执行完后，主线程才会被放行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //记录下结束时间
        long end = System.currentTimeMillis();
        System.out.println(executorService + "执行器消耗的时间为（毫秒）：" + (end - start));
    }
}

class LatchDemo implements Runnable {
    //用来存放一个闭锁
    private CountDownLatch latch;

    //构造器初始化
    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //让CountDownLatch计数器减1
            latch.countDown();
        }

    }
}
