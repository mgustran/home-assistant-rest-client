package com.mgustran.homeassistant.client.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgustran.homeassistant.client.exception.HaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BaseClient {

    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public <T> BaseResponse<T> execute(final String address, final String method, final Map<String, String> headers,
                                       final Object body, final Class<T> returnClass) throws HaException {

        try {
            URL url = new URL(address);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setRequestProperty("User-Agent", "AnyAgent");
            con.setRequestProperty("Authorization", "AnyAgent");

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

            T res = returnClass == String.class ?
                    (T) response.toString() : mapper.readValue(response.toString(), returnClass);

            return new BaseResponse<>(con.getResponseCode(), res);

        } catch (IOException e) {
            throw new HaException(e);
        }

    }

    public static void main(String[] args) throws  HaException {
        BaseClient client2 = new BaseClient();
//        OriginalDomainToOptimizedDomainConverter converter = new OriginalDomainToOptimizedDomainConverter();

//        final BaseResponse<OriginalHaDomain[]> test = client2.execute("http://localhost:8123/api/services", "GET", Map.of("Authorization", "Bearer xxx",
//                                                                                        "Accept", "application/json"),
//                null, OriginalHaDomain[].class);
//
//        final List<HaDomain> haDomains = converter.convertList(Arrays.asList(test.getResponse()));
//        System.out.println(haDomains);

//        final String json2 = "{\"entity_id\": \"light.bedroom_light\"}";
//
//        final BaseResponse<HaDomain[]> test2 = client2.execute("http://localhost:8123/api/services/light/turn_on",
//                "POST", Map.of("Authorization", "Bearer xxx",
//                        "Accept", "application/json", "Content-Type", "application/json"),
//                json2, HaDomain[].class);

////        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/calendars",
//        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/calendars/calendar.miquel_gustran?start=2022-10-01T07:00:00.000Z&end=2022-11-01T07:00:00.000Z",
////        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/logbook",
////        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/history/period",
//                "GET", Map.of("Authorization", "Bearer xxx",
//                        "Accept", "application/json", "Content-Type", "application/json"),
//                null, String.class);

//        final String json2 = "{\"state\": \"25\", \"entity_id\": \"calendar.miquel_gustran\", \"attributes\": {\"unit_of_measurement\": \"°C\"}}";
//        final String json2 = "{\"next_rising\":\"2022-10-21T03:39:14+00:00\"}";
//        final String json2 = "{\"template\": \"It is {{ now() }}!\"}";
        final String json2 = "{ \"name\": \"HassTurnOff\", \"data\": { \"name\": \"light.bedroom_light_2\" } }";

//        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/calendars",
        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/intent/handle",
//        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/logbook",
//        final BaseResponse<String> test3 = client2.execute("http://localhost:8123/api/history/period",
                "POST", Map.of("Authorization", "Bearer xxx",
                        "Accept", "text/plain", "Content-Type", "application/json"),
                json2, String.class);
//
        System.out.println(test3.getResponse());
    }
}
