package org.daveo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.daveo.model.Mixtape;
import org.daveo.model.change.Change;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: Playlist <mixtape file path> <changes file path>");
            System.exit(1);
        }

        final String mixtapePath = args[0];
        final String changesPath = args[1];

        final ObjectMapper objectMapper = new ObjectMapper();
        final Mixtape mixtape = objectMapper.readValue(new File(mixtapePath), Mixtape.class);
        final List<Change> changes = objectMapper.readValue(new File(changesPath), new TypeReference<List<Change>>(){});

        final MixtapeEngine engine = new MixtapeEngine(mixtape);

        for (Change change : changes) {
            change.apply(engine);
        }

        objectMapper.writeValue(Paths.get("output.json").toFile(), engine.toModel());
    }
}
