package com.lvdreamer.basic;

public class IdGen {
    private final long twepoch = 1288834974657L;
    private final long sequenceMask = -1L ^ (-1L << 4L);
    private final long timestampLeftShift = 4;
    private long lastTimestamp = -1L;
    private long sequence = 0L;
    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = System.currentTimeMillis();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | sequence;
    }

    public static void main(String[] args) {
        System.out.println(Long.toBinaryString((-1L ^ (-1L << 4L))));
        System.out.println((System.currentTimeMillis()-1288834974657L)*16);
        IdGen idGen=new IdGen();
        for(int i=0;i<10;i++){
            System.out.println(idGen.nextId());
        }
    }
}
