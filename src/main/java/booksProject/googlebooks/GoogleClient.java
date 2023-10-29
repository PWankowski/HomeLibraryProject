package booksProject.googlebooks;



import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;

public interface GoogleClient {

    Pair<Integer, String> getBookVolume(String identifier) throws IOException;
}
