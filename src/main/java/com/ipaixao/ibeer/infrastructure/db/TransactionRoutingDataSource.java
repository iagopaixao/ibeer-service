package com.ipaixao.ibeer.infrastructure.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.ipaixao.ibeer.infrastructure.db.TransactionRoutingDataSource.DataSourceType.READ_ONLY;
import static com.ipaixao.ibeer.infrastructure.db.TransactionRoutingDataSource.DataSourceType.READ_WRITE;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

    public enum DataSourceType {
        READ_WRITE,
        READ_ONLY
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? READ_ONLY : READ_WRITE;
    }
}