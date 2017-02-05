/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Checks whether service is up or not! Can be heralded as Base Abstract class for Connection Check
 */
/**
 * @author Leon
 */
package org.crce.sw;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.sparql.engine.http.QueryExceptionHTTP;

/**
 *
 * @author Leon
 */
public class ServiceStateCheck {

    ServiceStateCheck() {
        String service = "http://dbpedia.org/sparql";  //give name of sparql endpoint you want to check here
        String query = "ASK{ }";  //ask query

        QueryExecution qe = QueryExecutionFactory.sparqlService(service, query);
        qe.setTimeout(1000000000);  //increase timeout! Slow Internet Problems for me!
        try {
            //check if service works
            if (qe.execAsk()) {
                System.out.println(service + " is  UP");

            }
        } catch (QueryExceptionHTTP e) {
            System.out.println(service + " is down");
        } finally {
            qe.close();
        }
    }
}
