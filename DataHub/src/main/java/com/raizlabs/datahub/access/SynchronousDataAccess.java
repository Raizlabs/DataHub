package com.raizlabs.datahub.access;


public interface SynchronousDataAccess<Data> extends DataAccess {
    DataAccessResult<Data> get();
    void importData(Data data);
}