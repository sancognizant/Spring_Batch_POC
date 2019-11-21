package com.example.productbatch.Processor;

import com.example.productbatch.Model.Product;
import com.example.productbatch.Model.ResponseDiscount;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.batch.item.ItemProcessor;

import java.io.IOException;
import java.util.Map;

// input - l.h.s of itemprocessor from itemreader
// output - r.h.s of itemprocessor to itemwriter
public class ProductProcessor implements ItemProcessor<Product, ResponseDiscount> {

    private final OkHttpClient httpClient = new OkHttpClient();
    private ResponseDiscount responseDiscount = new ResponseDiscount();

    @Override
    public ResponseDiscount process(Product product) throws Exception {

        //System.out.println("Processor is starting");

        // json formatted data - input json data to pass as body to the rest api
        String json = new StringBuilder()
                .append("{")
                .append("\"product\":\"" + product.getProductName() + "\",")
                .append("\"quantity\":\"" + product.getQuantity() + "\"")
                .append("}").toString();

        System.out.println(json);

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );
        // send the json request to the rest api given in the url
        Request request = new Request.Builder()
                .url("http://10.142.110.250:9060/DecisionService/rest/Test_Discount_RuleApp/1.0/Test_Discount/1.4 ")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(body)
                .build();

        // get the response
        try (
                Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            String result = response.body().string();

            // substring / map method
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(result, Map.class);

            for (Map.Entry<String, Object> entry : map.entrySet()) {
               responseDiscount = getData(entry);
            }

    return responseDiscount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return responseDiscount;
    }

    // getting the output data from the map and inserting into the response obj
    ResponseDiscount getData(Map.Entry<String, Object> map) {
        ResponseDiscount responseDiscount1 = new ResponseDiscount();
        switch (map.getKey()) {
            case "Discount": {
                System.out.println(map.getValue());
                responseDiscount1.setDiscount((Double) map.getValue());
                responseDiscount1.setMessage("Your discount is: " + map.getValue());
                break;
            }

        }
        return responseDiscount1;
    }
}

