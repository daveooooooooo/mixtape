package org.daveo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jdk.internal.jline.internal.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSong.class)
@JsonDeserialize(as = ImmutableSong.class)
public abstract class Song {
    @Nullable
    public abstract String id();
    public abstract String artist();
    public abstract String title();
}
