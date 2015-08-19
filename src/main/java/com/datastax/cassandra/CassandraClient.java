package com.datastax.cassandra;

/**
 * Created by kmofokeng on 18/08/2015.
 */
public class CassandraClient {
    
    public static void main(String[] args) {
        CassandraConnector client = new CassandraConnector();
        client.connect();
        client.createSchema();
        client.loadData();
        client.querySchema();
        client.close();
    }
}