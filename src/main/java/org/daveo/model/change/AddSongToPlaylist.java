package org.daveo.model.change;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.daveo.MixtapeEngine;
import org.daveo.exception.PlaylistNotFoundException;
import org.daveo.exception.SongNotFoundException;
import org.daveo.model.ImmutableMixtape;
import org.daveo.model.ImmutablePlaylist;
import org.daveo.model.Playlist;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableAddSongToPlaylist.class)
@JsonDeserialize(as = ImmutableAddSongToPlaylist.class)
public abstract class AddSongToPlaylist extends Change {

    @JsonProperty("song_id")
    public abstract String songId();
    
    @JsonProperty("playlist_id")
    public abstract String playlistId();

    @Override
    public void apply(MixtapeEngine mixtape) {

        if (!mixtape.getSongs().containsKey(songId())) {
            throw new SongNotFoundException();
        }

        if (!mixtape.getPlaylists().containsKey(playlistId())) {
            throw new PlaylistNotFoundException();
        }

        Playlist playlist = mixtape.getPlaylists().get(playlistId());
        Playlist newPlaylist = ImmutablePlaylist.builder()
                .from(playlist)
                .addSongIds(songId())
                .build();
        mixtape.getPlaylists().put(newPlaylist.id(), newPlaylist);
    }
}
