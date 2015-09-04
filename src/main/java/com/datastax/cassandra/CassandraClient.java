package com.datastax.cassandra;

import com.datastax.driver.core.*;
import static com.datastax.cassandra.Constants.*;

/**
 * Created by kmofokeng on 18/08/2015.
 */
public class CassandraClient {

    public static void main(String[] args) {
        CassandraConnector client = new CassandraConnector();
        displayMetadata(client.connect(ADDRESS));
        client.createSchema(KEYSPACE);
        client.createSchema(SONGS);
        client.createSchema(PLAYLISTS);
        client.loadData(INSERT);
        displayPlaylists(client.querySchema(SELECT));
        client.deleteSchema(DROPSCHEMA);
        client.close();
    }

    public static void displayMetadata(Cluster cluster) {
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n", metadata.getClusterName() + "\n");
        System.out.printf("Metadata partitioner: %s\n", metadata.getPartitioner() + "\n");

        for ( Host host : metadata.getAllHosts() ) {
            System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
        }
    }

    public static void displayPlaylists(ResultSet results) {
        System.out.println("\n"+String.format("%-30s\t%-20s\t%-20s\n%s", "title", "album", "artist",
                "-------------------------------+-----------------------+--------------------"));
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s\t%-20s", row.getString("title"),
                    row.getString("album"),  row.getString("artist")));
        }
        System.out.println();
    }
}
