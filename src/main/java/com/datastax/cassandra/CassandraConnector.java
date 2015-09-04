package com.datastax.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;


/**
 * Created by kmofokeng on 18/08/2015.
 */
class CassandraConnector {
    public Cluster cluster;
    public Session session;

    public Cluster connect(String address) {
        try {
            cluster = Cluster.builder().addContactPoint(address).withCredentials("admin", "hR5#squ3E5").build();
            session = cluster.connect();
        }
        catch (NoHostAvailableException e) {
            System.out.println("All host(s) for the address: " + address  + " tried for query failed");
        }
        return cluster;
    }

    public ResultSet createSchema(String query) {
        return session.execute(query);
    }

    public ResultSet loadData(String insert) {
        return session.execute(insert);
    }

    public ResultSet querySchema(String select) {
        return session.execute(select);
    }

    public void deleteSchema(String dropSchema) {
        session.execute(dropSchema);
    }

    public void close() {
        session.close();
        cluster.close();
    }
}
