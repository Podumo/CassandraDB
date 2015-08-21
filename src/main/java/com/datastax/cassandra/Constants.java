package com.datastax.cassandra;

import java.util.UUID;

/**
 * Created by kmofokeng on 21/08/2015.
 */
class Constants {
    public static String ADDRESS = "cassandra.host.com";
    public static String KEYSPACE = "CREATE KEYSPACE IF NOT EXISTS simplex WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};";
    public static String SONGS = "CREATE TABLE IF NOT EXISTS simplex.songs (" +
            "id uuid PRIMARY KEY," +
            "title text," +
            "album text," +
            "artist text," +
            "tags set<text>," +
            "data blob" +
            ");";
    public static String PLAYLISTS = "CREATE TABLE IF NOT EXISTS simplex.playlists (" +
            "id uuid," +
            "title text," +
            "album text, " +
            "artist text," +
            "song_id uuid," +
            "PRIMARY KEY (id, title, album, artist)" +
            ");";
    public static String INSERT = "BEGIN BATCH USING TIMESTAMP " +
            "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",'La Petite Tonkinoise'," +
            "'Bye Bye Blackbird'," +
            "'Joséphine Baker'," +
            "{'jazz', '2013'})" +
            ";" +
            "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",'La Grange'," +
            "'ZZ Top'," +
            "'Tres Hombres'," +
            "{'jazz', '2013'})" +
            ";" +
            "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",'Moving in Stereo'," +
            "'Fu Manchu'," +
            "'We Must Obey'," +
            "{'jazz', '2014'})" +
            ";" +
            "INSERT INTO simplex.songs (id, title, album, artist, tags) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",'Outside Woman Blues'," +
            "'Back Door Slam'," +
            "'Roll Away'," +
            "{'jazz', '2015'})" +
            ";" +
            "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",756716f7-2e54-4715-9f00-91dcbea6cf50," +
            "'La Petite Tonkinoise'," +
            "'Bye Bye Blackbird'," +
            "'Joséphine Baker'" +
            ");" +
            "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",a3e64f8f-bd44-4f28-b8d9-6938726e34d4," +
            "'La Grange'," +
            "'ZZ Top'," +
            "'Tres Hombres'" +
            ");" +
            "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",8a172618-b121-4136-bb10-f665cfc469eb," +
            "'Moving in Stereo'," +
            "'Fu Manchu'," +
            "'We Must Obey'" +
            ");" +
            "INSERT INTO simplex.playlists (id, song_id, title, album, artist) " +
            "VALUES (" +
            UUID.randomUUID() +
            ",2b09185b-fb5a-4734-9b56-49077de9edbf," +
            "'Outside Woman Blues'," +
            "'Back Door Slam'," +
            "'Roll Away'" +
            ");" +
            "APPLY BATCH";
    public static String SELECT = "SELECT * FROM simplex.playlists;";
    public static String DROPSCHEMA = "DROP KEYSPACE simplex;";

    private Constants() {
        throw new AssertionError();
    }
}
