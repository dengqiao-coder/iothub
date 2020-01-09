package com.lemon.commonutils.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author dengqiao
 * @date 2020-01-08 18:49
 */
public class IdWorker {
    private final long workerId;
    private final static long twepoch = 1288834974657L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;
    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;
    private long lastTimestamp = -1L;

    public IdWorker(final long workerId) {
        super();
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",
                    maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                        String.format(
                                "Clock moved backwards. Refusing to generate id for %d milliseconds",
                                this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << workerIdShift) | (this.sequence);
        System.out.println("timestamp:" + timestamp + ",timestampLeftShift:"
                + timestampLeftShift + ",nextId:" + nextId + ",workerId:"
                + workerId + ",sequence:" + sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static String getIdByUuid() {
        int hashCodev = UUID.randomUUID().toString().hashCode();
        if (hashCodev < 0) {
            hashCodev = -hashCodev;
        }
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int random = (int) (Math.random() * 26);
        return str.charAt(random) + String.format("%08d", hashCodev);
    }

    public static String getRandomSeq() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int random = (int) (Math.random() * 26);
        String str0 = "abcdefghijklmnopqrstuvwxyz";
        int random0 = (int) (Math.random() * 26);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.charAt(random));
        stringBuffer.append(str0.charAt(random0));
        Random ran = new Random();
        int num = ran.nextInt(9999);
        stringBuffer.append(String.format("%04d", num));
        return stringBuffer.toString();
    }
}
