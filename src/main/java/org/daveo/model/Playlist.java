package org.daveo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.internal.jline.internal.Nullable;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutablePlaylist.class)
@JsonDeserialize(as = ImmutablePlaylist.class)
public abstract class Playlist {

    @Nullable
    public abstract String id();

    @JsonProperty("user_id")
    public abstract String userId();

    @JsonProperty("song_ids")
    public abstract List<String> songIds();
}
