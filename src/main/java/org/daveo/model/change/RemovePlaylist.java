package org.daveo.model.change;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.daveo.MixtapeEngine;
import org.daveo.model.ImmutableMixtape;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableRemovePlaylist.class)
@JsonDeserialize(as = ImmutableRemovePlaylist.class)
public abstract class RemovePlaylist extends Change {

    @JsonProperty("playlist_id")
    public abstract String playlistId();

    @Override
    public void apply(final MixtapeEngine mixtape) {
        if (mixtape.getPlaylists().containsKey(playlistId())) {
            mixtape.getPlaylists().remove(playlistId());
        }
    }
}
