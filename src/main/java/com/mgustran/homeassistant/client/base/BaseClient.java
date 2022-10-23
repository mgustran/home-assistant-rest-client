package com.mgustran.homeassistant.client.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgustran.homeassistant.client.exception.HaException;
import com.mgustran.homeassistant.client.util.HeadersUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BaseClient {

    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public <T> BaseResponse<T> execute(final String address, final String method, final Map<String, String> headers,
                                       final Object body, final Class<T> returnClass) throws HaException {
        try {
            BaseStringResponse response = this.executeStringResponse(address, method, headers, body);
            T res = mapper.readValue(response.getResponseString(), returnClass);

            return new BaseResponse<>(response.getResponseCode(), res);

        } catch (IOException e) {
            throw new HaException(e);
        }
    }

    public BaseResponse<String> execute(final String address, final String method, final Map<String, String> headers,
                                       final Object body) throws HaException {
        try {
            BaseStringResponse response = this.executeStringResponse(address, method, headers, body);
            return new BaseResponse<>(response.getResponseCode(), response.getResponseString());

        } catch (IOException e) {
            throw new HaException(e);
        }
    }

    public BaseStringResponse executeStringResponse(final String address, final String method, final Map<String, String> headers,
                                       final Object body) throws IOException {

        URL url = new URL(address);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("User-Agent", "AnyAgent");

        headers.forEach(con::setRequestProperty);

        con.setDoOutput(true);

        if (body != null) {
            String jsonInputString = body instanceof String ? (String) body : this.mapper.writeValueAsString(body);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {

            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
                if (headers.containsKey("Accept") && headers.get("Accept").equals("text/plain")) {
                    response.append("\n");
                }

            }
        }

        return BaseStringResponse.builder()
                .responseCode(con.getResponseCode())
                .responseString(response.toString())
                .build();

    }

    public static void main(String[] args) throws  HaException {
        BaseClient client2 = new BaseClient();
        HeadersUtils headersUtils = new HeadersUtils();
        final String json2 = "{ \"name\": \"HassTurnOn\", \"data\": { \"name\": \"Bedroom Light\" } }";
        final HashMap<String, String> headers = headersUtils.generateHeaders("",
                HeadersUtils.Header.JSON, HeadersUtils.Header.JSON);

        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/intent/handle",
                "GET", headers, json2);
        System.out.println(test3.getResponse());
    }
}
