package booksProject.googlebooks;

import booksProject.HttpClient;
import okhttp3.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GoogleClientImpl implements GoogleClient{

    private final String googleBooksUrl;

    public GoogleClientImpl(@Value("${googleBookApi.url}") String googleBooksUrl) {
        this.googleBooksUrl = googleBooksUrl;
    }

    @Override
    public Pair<Integer, String> getBookVolume(String identifier) throws IOException {

        String endpoint = String.format("/volumes/%s", identifier);
        String uri = googleBooksUrl + endpoint;
        Response result = HttpClient.get(uri);
        return Pair.of(result.code(), result.body().string());
    }

    @Override
    public Pair<Integer, String> getBooksList(String searchingParameters) throws IOException {

        String endpoint = String.format("/volumes?q=%s", searchingParameters);
        String uri = googleBooksUrl + endpoint;
        Response result = HttpClient.get(uri);
        return Pair.of(result.code(), result.body().string());
    }
}
