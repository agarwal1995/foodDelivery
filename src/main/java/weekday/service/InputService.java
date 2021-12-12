package weekday.service;

import weekday.model.OrderRequest;

import java.util.List;

/**
 * Interface to read the input from the respective
 */
public interface InputService {

    List<OrderRequest> read();
}
