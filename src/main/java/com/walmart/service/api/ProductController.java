package com.walmart.service.api;


import com.walmart.config.ConfigProperties;
import com.walmart.dto.Product;
import com.walmart.exceptions.BadRequestException;
import com.walmart.platform.reponse.DataResponse;
import com.walmart.response.BaseResponse;
import com.walmart.service.ProductServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    protected ConfigProperties configProperties;
    @Autowired
    ProductServiceFactory productServiceFactory;

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> searchProduct(@RequestParam("query") String query) {
        //checkRequest(bindingResult);

        List<Product> productList = productServiceFactory.get(configProperties.getDocumentImplementation())
                .search(query);
        return this.createSuccessResponse(productList, "SuccessFull Search");

    }

    protected void checkRequest(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
    }

    protected ResponseEntity<BaseResponse> createSuccessResponse(Object resultData, String message) {
        return this.createOkResponse(resultData, message, HttpStatus.OK);
    }

    private ResponseEntity<BaseResponse> createOkResponse(Object resultData, String message, HttpStatus httpStatus) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage(message != null ? message : "Operation successful");
        dataResponse.setStatus(httpStatus.value());
        dataResponse.setTimestamp(Calendar.getInstance().getTimeInMillis());
        if (resultData != null) {
            dataResponse.setReturnData(resultData);
        }

        return new ResponseEntity(dataResponse, httpStatus);
    }
}
