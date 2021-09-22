package com.devcenter.apiRest.Controller;

import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcenter.apiRest.constants.Constantes;
import com.devcenter.apiRest.model.Response;

@RestController
public class IndexController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity<Response> error(HttpServletRequest request) {
    	HttpStatus responseStatus = null;
    	Integer errorCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	Response response = new Response(new Timestamp(System.currentTimeMillis()).toString(), Constantes.DATOS_VACIOS,
    			errorCode.toString(), Constantes.NOT_OK);
    	if("400".equals(errorCode.toString())) {
    		responseStatus = HttpStatus.BAD_REQUEST;
    	} else if("404".equals(errorCode.toString())) {
    		responseStatus = HttpStatus.NOT_FOUND;
    	} else if("500".equals(errorCode.toString())) {
    		responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
    	
    	return new ResponseEntity(response, responseStatus);
    }

    public String getErrorPath() {
        return PATH;
    }
}