package com.example.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.NoHostAvailableException;

/**
 * Created by kmofokeng on 18/08/2015.
 */
class CassandraConnector {
    private Cluster cluster;
    private Session session;

    public void connect() {
        try {
            cluster = Cluster.builder()
                    .addContactPoint("bvbdev1dat1cas.deloittecloud.co.za")
                    .build();
            Metadata metadata = cluster.getMetadata();
            System.out.printf("Connected to cluster: %s\n",
                    metadata.getClusterName());
            System.out.printf("Metadata partitioner: %s\n",
                    metadata.getPartitioner());

            for ( Host host : metadata.getAllHosts() ) {
                System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
                        host.getDatacenter(), host.getAddress(), host.getRack());
            }
            session = cluster.connect();
        }
        catch (NoHostAvailableException e) {
            System.out.println("All host(s) tried for query failed");
        }
    }

    public void createSchema() {
        session.execute("CREATE KEYSPACE IF NOT EXISTS simplex WITH replication " +
                "= {'class':'SimpleStrategy', 'replication_factor':3};");
        session.execute(
                "CREATE TABLE IF NOT EXISTS simplex.songs (" +
                        "id uuid PRIMARY KEY," +
                        "title text," +
                        "album text," +
                        "artist text," +
                        "tags set<text>," +
                        "data blob" +
                        ");");
        session.execute(
                "CREATE TABLE IF NOT EXISTS simplex.playlists (" +
                        "id uuid," +
                        "title text," +
                        "album text, " +
                        "artist text," +
                        "song_id uuid," +
                        "PRIMARY KEY (id, title, album, artist)" +
                        ");");
    }

    public void loadData() {
        session.execute(
                "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
                        "VALUES (" +
                        "756716f7-2e54-4715-9f00-91dcbea6cf50," +
                        "'La Petite Tonkinoise'," +
                        "'Bye Bye Blackbird'," +
                        "'Joséphine Baker'," +
                        "{'jazz', '2013'})" +
                        ";");
        session.execute(
                "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
                        "VALUES (" +
                        "a3e64f8f-bd44-4f28-b8d9-6938726e34d4," +
                        "'La Grange'," +
                        "'ZZ Top'," +
                        "'Tres Hombres'," +
                        "{'jazz', '2013'})" +
                        ";");
        session.execute(
                "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
                        "VALUES (" +
                        "8a172618-b121-4136-bb10-f665cfc469eb," +
                        "'Moving in Stereo'," +
                        "'Fu Manchu'," +
                        "'We Must Obey'," +
                        "{'jazz', '2014'})" +
                        ";");
        session.execute(
                "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
                        "VALUES (" +
                        "2b09185b-fb5a-4734-9b56-49077de9edbf," +
                        "'Outside Woman Blues'," +
                        "'Back Door Slam'," +
                        "'Roll Away'," +
                        "{'jazz', '2015'})" +
                        ";");
        session.execute(
                "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
                        "VALUES (" +
                        "2cc9ccb7-6221-4ccb-8387-f22b6a1b354d," +
                        "756716f7-2e54-4715-9f00-91dcbea6cf50," +
                        "'La Petite Tonkinoise'," +
                        "'Bye Bye Blackbird'," +
                        "'Joséphine Baker'" +
                        ");");
        session.execute(
                "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
                        "VALUES (" +
                        "62c36092-82a1-3a00-93d1-46196ee77204," +
                        "a3e64f8f-bd44-4f28-b8d9-6938726e34d4," +
                        "'La Grange'," +
                        "'ZZ Top'," +
                        "'Tres Hombres'" +
                        ");");
        session.execute(
                "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
                        "VALUES (" +
                        "62c36092-82a1-3a00-93d1-46196ee77204," +
                        "8a172618-b121-4136-bb10-f665cfc469eb," +
                        "'Moving in Stereo'," +
                        "'Fu Manchu'," +
                        "'We Must Obey'" +
                        ");");
        session.execute(
                "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
                        "VALUES (" +
                        "62c36092-82a1-3a00-93d1-46196ee77204," +
                        "2b09185b-fb5a-4734-9b56-49077de9edbf," +
                        "'Outside Woman Blues'," +
                        "'Back Door Slam'," +
                        "'Roll Away'" +
                        ");");
    }

    public void querySchema() {
        ResultSet results = session.execute("SELECT * FROM simplex.playlists;");
        System.out.println("\n"+String.format("%-30s\t%-20s\t%-20s\n%s", "title", "album", "artist",
                "-------------------------------+-----------------------+--------------------"));
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s\t%-20s", row.getString("title"),
                    row.getString("album"),  row.getString("artist")));
        }
        System.out.println();
    }

    public void close() {
        session.close();
        cluster.close();
    }
}