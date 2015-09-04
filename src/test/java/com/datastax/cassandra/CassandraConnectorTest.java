package com.datastax.cassandra;

//import com.datastax.driver.core.Cluster;

import junit.framework.TestCase;
import org.junit.Test;

//import static com.datastax.cassandra.Constants.*;

/**
 * Created by kmofokeng on 19/08/2015.
 */
public class CassandraConnectorTest extends TestCase {
    @Test
    public void testGeneric(){
        
    }
    /*private Cluster cluster;

    private CassandraConnector connector = new CassandraConnector();

    @Test
    public void testConnection() {
        cluster = connector.connect(ADDRESS);
        assertEquals("Cluster address is incorrect", cluster.getMetadata().getClusterName(), "BVBDEV1DAT1CAS");
        connector.close();
    }

    @Test
    public void testQuerySchema() {
        cluster = connector.connect(ADDRESS);
        connector.createSchema(KEYSPACE);
        connector.createSchema(SONGS);
        connector.createSchema(PLAYLISTS);
        connector.loadData(INSERT);
        assertTrue(!connector.querySchema(SELECT).toString().isEmpty());
        connector.close();
    }

    @Test
    public void testDeleteSchema() {
        cluster = connector.connect(ADDRESS);
        connector.createSchema(KEYSPACE);
        assertTrue(connector.createSchema(DROPSCHEMA).getExecutionInfo().isSchemaInAgreement());
        connector.close();
    }

    @Test
    public void testCloseConnection() {
        cluster = connector.connect(ADDRESS);
        connector.close();
        assertTrue(cluster.isClosed());
    }*/
}
