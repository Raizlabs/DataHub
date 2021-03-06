package com.raizlabs.datahub.hub.helpers;

import com.raizlabs.datahub.access.AsyncDataAccess;
import com.raizlabs.datahub.access.DataAccessResult;
import com.raizlabs.datahub.utils.OneShotLock;

public class ImmediateResponseAsyncAccess<T> implements AsyncDataAccess<T> {

    private OneShotLock completionLock = new OneShotLock();

    private final DataAccessResult<T> result;
    private final int typeId;

    public ImmediateResponseAsyncAccess(DataAccessResult<T> result, int typeId) {
        this.result = result;
        this.typeId = typeId;
    }

    @Override
    public void get(AsyncDataCallback<T> asyncDataCallback) {
        asyncDataCallback.onResult(result, this);
        completionLock.unlock();
    }

    @Override
    public void importData(T t) {

    }

    @Override
    public void close() {

    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    public OneShotLock getCompletionLock() {
        return completionLock;
    }

    public void reset() {
        completionLock = new OneShotLock();
    }
}
