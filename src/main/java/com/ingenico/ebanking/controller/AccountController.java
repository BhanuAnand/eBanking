package com.ingenico.ebanking.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ingenico.ebanking.account.AbstractAccount;
import com.ingenico.ebanking.exception.ExceptionHandler;
import com.ingenico.ebanking.exception.ResponseException;
import com.ingenico.ebanking.model.AccountModel;
import com.ingenico.ebanking.model.TransferModel;
import com.ingenico.ebanking.response.Response;
import com.ingenico.ebanking.service.AccountTransaction;

/**
 *
 * @author caydogdu
 *
 *         This is a controller for rest services
 */
@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountTransaction accountTransaction;

    /**
     *
     * @param account model to open
     * @return opened account
     */
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
    public ResponseEntity<Response<AbstractAccount>> openAccount(@RequestBody AccountModel acc) {

        Response<AbstractAccount> accResponse = new Response<>();
        ResponseEntity<Response<AbstractAccount>> response = new ResponseEntity<>(accResponse, HttpStatus.OK);

        try {
            AbstractAccount account = (AbstractAccount) accountTransaction.openAccount(acc);
            response.getBody().setSuccess(true);
            response.getBody().setResult(account);

            // display accounts after opening
            accountTransaction.displayAccounts();

        } catch (Exception e) {
            logger.error("error", e);
            response.getBody().setSuccess(false);
            response.getBody().setError(ExceptionHandler.errorResponse(e));
        }
        return response;
    }

    /**
     *
     * @param transfer model which includes accouns and amount
     * @return boolean
     */
    @RequestMapping(value = "/transfers", method = RequestMethod.POST)
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
    public ResponseEntity<Response<String>> transfer(@RequestBody TransferModel tm) {

        Response<String> accResponse = new Response<>();
        ResponseEntity<Response<String>> response = new ResponseEntity<>(accResponse, HttpStatus.OK);

        try {
            accountTransaction.transfer(tm);
            response.getBody().setSuccess(true);

            // display accounts after transfer
            accountTransaction.displayAccounts();

        } catch (ResponseException e) {
            logger.error(String.format("error: %s", e.getExceptionDescription()));
            response.getBody().setSuccess(false);
            response.getBody().setError(ExceptionHandler.errorResponse(e));
        } catch (Exception e) {
            logger.error("error", e);
            response.getBody().setSuccess(false);
            response.getBody().setError(ExceptionHandler.errorResponse(e));
        }
        return response;
    }

}
