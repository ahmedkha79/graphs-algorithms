package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceLoader {

    public static Path getResourcePath(String resourceName) throws URISyntaxException {
        // Lade die Ressource als URL
        URL resourceUrl = null;
        try {
            resourceUrl = ResourceLoader.class.getClassLoader().getResource(resourceName);


            if (resourceUrl == null) {
                throw new IllegalArgumentException("Resource not found: " + resourceName);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        // Konvertiere die URL in einen Pfad
        return Paths.get(resourceUrl.toURI());
    }
}
