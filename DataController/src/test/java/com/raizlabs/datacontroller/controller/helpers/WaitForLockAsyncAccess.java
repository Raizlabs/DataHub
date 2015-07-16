package com.raizlabs.datacontroller.controller.helpers;

import com.raizlabs.datacontroller.DataAccessResult;
import com.raizlabs.datacontroller.access.AsynchronousDataAccess;
import com.raizlabs.datacontroller.utils.OneShotLock;

public class WaitForLockAsyncAccess<T> implements AsynchronousDataAccess<T> {

    private final OneShotLock completionLock = new OneShotLock();

    private final DataAccessResult<T> result;
    private final OneShotLock startLock;

    private final int sourceId;

    public WaitForLockAsyncAccess(DataAccessResult<T> result, OneShotLock lock, int sourceId) {
        this.result = result;
        this.startLock = lock;
        this.sourceId = sourceId;
    }

    @Override
    public void get(final Callback<T> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                startLock.waitUntilUnlocked();
                callback.onResult(result, WaitForLockAsyncAccess.this);
                completionLock.unlock();
            }
        }).start();
    }

    @Override
    public void importData(T t) {

    }

    @Override
    public void close() {

    }

    @Override
    public int getSourceId() {
        return sourceId;
    }

    public OneShotLock getCompletionLock() {
        return completionLock;
    }
}
