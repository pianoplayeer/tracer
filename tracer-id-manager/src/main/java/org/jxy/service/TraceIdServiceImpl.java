package org.jxy.service;

import org.jxy.api.TraceIdService;

import java.util.concurrent.atomic.AtomicLong;

public class TraceIdServiceImpl implements TraceIdService {
    private static AtomicLong current = new AtomicLong(0);

    @Override
    public long getNewID() {
        return current.getAndIncrement();
    }
}
