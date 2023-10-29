package booksProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class HttpClient {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static Response get(String url) throws IOException {

        Request request = new Request.Builder()
                                     .url(url)
                                     .get()
                                     .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return response;
    }

    public static Response post(String url, Object object) throws IOException {

        String value = new ObjectMapper().writeValueAsString(object);
        RequestBody body = RequestBody.create(value, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                                     .url(url)
                                     .post(body)
                                     .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return response;
    }

    public static Response put(String url, Object object) throws IOException {

        String value = new ObjectMapper().writeValueAsString(object);
        RequestBody body = RequestBody.create(value, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return response;
    }

    public static Response delete(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        Response response = HTTP_CLIENT.newCall(request).execute();
        return response;
    }
}
