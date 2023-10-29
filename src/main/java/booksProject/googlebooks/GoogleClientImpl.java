package booksProject.googlebooks;

import booksProject.HttpClient;
import okhttp3.Response;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleClientImpl implements GoogleClient{

    private static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1";

    @Override
    public Pair<Integer, String> getBookVolume(String identifier) throws IOException {

        String endpoint = String.format("/volumes/%s", identifier);
        String uri = GOOGLE_BOOKS_URL + endpoint;
        Response result = HttpClient.get(uri);
        return Pair.of(result.code(), result.body().string());
    }
}
