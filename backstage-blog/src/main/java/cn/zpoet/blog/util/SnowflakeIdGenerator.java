package cn.zpoet.blog.util;

import org.springframework.stereotype.Component;

/**
 * 雪花算法 ID 生成器（Snowflake）
 * 64 位 Long 类型：
 * 1bit 符号位 + 41bit 时间戳 + 10bit 机器 + 12bit 序列号
 */
@Component
public class SnowflakeIdGenerator {

    private final long twepoch = 1672531200000L; // 起始时间：2023-01-01，可自定义

    private final long workerIdBits = 5L;    // 机器 ID 位数
    private final long datacenterIdBits = 5L; // 数据中心 ID 位数
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits); // 31
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); // 31

    private final long sequenceBits = 12L;   // 序列号位数
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator() {
        this(0, 0);
    }

    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("workerId 必须在 [0,%d] 范围内", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenterId 必须在 [0,%d] 范围内", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 生成下一个 ID
     */
    public synchronized long nextId() {
        long timestamp = currentTime();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("系统时钟回退，拒绝生成 ID");
        }

        if (lastTimestamp == timestamp) {
            // 同一毫秒内，序列号自增
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 序列号溢出，等待下一毫秒
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒，序列号清零
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    private long waitNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    private long currentTime() {
        return System.currentTimeMillis();
    }
}

