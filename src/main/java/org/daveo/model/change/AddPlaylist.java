package org.daveo.model.change;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.daveo.MixtapeEngine;
import org.daveo.exception.EmptyPlaylistException;
import org.daveo.exception.SongNotFoundException;
import org.daveo.exception.UserNotFoundException;
import org.daveo.model.ImmutableMixtape;
import org.daveo.model.ImmutablePlaylist;
import org.daveo.model.Playlist;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableAddPlaylist.class)
@JsonDeserialize(as = ImmutableAddPlaylist.class)
public abstract class AddPlaylist extends Change {

    public abstract Playlist playlist();

    @Override
    public void apply(MixtapeEngine mixtape) {
        if (playlist().songIds().isEmpty()) {
            throw new EmptyPlaylistException();
        }

        for (String songId : playlist().songIds()) {
            if (!mixtape.getSongs().containsKey(songId)) {
                throw new SongNotFoundException();
            }
        }

        if (!mixtape.getUsers().containsKey(playlist().userId())) {
            throw new UserNotFoundException();
        }

        Playlist playlistToAdd = ImmutablePlaylist.builder()
                .from(playlist())
                .id(String.valueOf(mixtape.getAndIncrementPlaylistId()))
                .build();
        mixtape.getPlaylists().put(playlistToAdd.id(), playlistToAdd);
    }
}
