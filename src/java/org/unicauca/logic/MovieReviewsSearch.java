/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicauca.logic;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Juan
 */
@WebService(serviceName = "MovieReviewsSearch")
public class MovieReviewsSearch {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "getMovie")
    public String getMovie(@WebParam(name = "keyword") String keyword) {
        return MovieSearch.getMovieReviews(keyword);
    }
}
