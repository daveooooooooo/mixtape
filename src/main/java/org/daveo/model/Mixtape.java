package org.daveo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableMixtape.class)
@JsonDeserialize(as = ImmutableMixtape.class)
public abstract class Mixtape {
    public abstract List<User> users();
    public abstract List<Playlist> playlists();
    public abstract List<Song> songs();
}
