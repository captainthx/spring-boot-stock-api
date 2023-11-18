package com.yutsuki.stock.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public final class TimeUtils {

    private TimeUtils() {}

    public static Date toDate(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime);
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime toLocalDatetime(Date date) {
        Objects.requireNonNull(date);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static long currentMilli() {
        return toMilli(LocalDateTime.now());
    }

    public static long toSecond(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime);
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static long toMilli(LocalDateTime dateTime) {
        Objects.requireNonNull(dateTime);
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime fromMilli(Long milli) {
        return Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public static LocalDateTime fromUnix(Long timestamp) {
        return Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
