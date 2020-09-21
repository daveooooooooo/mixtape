package org.daveo.model.change;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.daveo.MixtapeEngine;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddPlaylist.class, name = "AddPlaylist"),
        @JsonSubTypes.Type(value = AddSongToPlaylist.class, name = "AddSongToPlaylist"),
        @JsonSubTypes.Type(value = RemovePlaylist.class, name = "RemovePlaylist")
})
public abstract class Change {
    public abstract void apply(final MixtapeEngine mixtape);
}
