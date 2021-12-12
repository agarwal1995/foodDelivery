package weekday.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import weekday.model.OrderRequest;
import weekday.service.InputService;

import java.lang.reflect.Type;
import java.util.List;

public class JsonStringInputServiceImpl implements InputService {

    private Gson gson = new Gson();
    private static final Type type = new TypeToken<List<OrderRequest>>() {}.getType();

    /**
     * reads the data from the predefined jsonString
     * @return List of OrderRequest
     */
    public List<OrderRequest> read() {
        String input = "[{orderId:12,meals:[\"A\",\"A\"],distance:5},{orderId:21,meals:[\"A\",\"M\"],distance:1},{orderId:14,meals:[\"M\",\"M\",\"M\",\"M\",\"A\",\"A\",\"A\"],distance:10},{orderId:32,meals:[\"M\"],distance:0.1},{orderId:22,meals:[\"A\"],distance:3}]";
        return gson.fromJson(input, type);
    }
}
