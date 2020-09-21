package org.daveo;

import org.daveo.model.*;

import java.util.*;

public class MixtapeEngine {
    private Map<String, Playlist> playlists;
    private Map<String, Song> songs;
    private Map<String, User> users;
    private int userIncrementId = 0;
    private int playlistIncrementId = 0;
    private int songIncrementId = 0;

    public MixtapeEngine(org.daveo.model.Mixtape mixtape) {
        playlists = new HashMap<>();
        songs = new HashMap<>();
        users = new HashMap<>();
        for (Playlist playlist : mixtape.playlists()) {
            playlists.put(playlist.id(), playlist);
            this.playlistIncrementId = Integer.valueOf(playlist.id());
        }

        for (Song song : mixtape.songs()) {
            songs.put(song.id(), song);
            this.songIncrementId = Integer.valueOf(song.id());
        }

        for (User user : mixtape.users()) {
            users.put(user.id(), user);
            this.userIncrementId = Integer.valueOf(user.id());
        }
    }

    public int getAndIncrementUserId() {
        this.userIncrementId++;
        return this.userIncrementId;
    }

    public int getAndIncrementPlaylistId() {
        this.playlistIncrementId++;
        return this.playlistIncrementId;
    }
    public int getAndIncrementSongId() {
        this.songIncrementId++;
        return this.songIncrementId;
    }

    public Map<String, Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Map<String, Playlist> playlists) {
        this.playlists = playlists;
    }

    public Map<String, Song> getSongs() {
        return songs;
    }

    public void setSongs(Map<String, Song> songs) {
        this.songs = songs;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Mixtape toModel() {
        List<Playlist> modelPlaylists = new ArrayList<>();
        List<String> playlistKeys = new ArrayList<>(this.playlists.keySet());
        Collections.sort(playlistKeys, Comparator.comparing(Integer::valueOf));
        for (String playlistId : playlistKeys) {
            modelPlaylists.add(this.playlists.get(playlistId));
        }

        List<User> modelUsers = new ArrayList<>();
        List<String> userKeys = new ArrayList<>(this.users.keySet());
        Collections.sort(userKeys, Comparator.comparing(Integer::valueOf));
        for (String userId : userKeys) {
            modelUsers.add(this.users.get(userId));
        }

        List<Song> modelSongs = new ArrayList<>();
        List<String> songKeys = new ArrayList<>(this.songs.keySet());
        Collections.sort(songKeys, Comparator.comparing(Integer::valueOf));
        for (String songId : songKeys) {
            modelSongs.add(this.songs.get(songId));
        }

        return ImmutableMixtape.builder()
                .playlists(modelPlaylists)
                .users(modelUsers)
                .songs(modelSongs)
                .build();
    }
}
