/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

/**
 *
 * @author Leon
 */
public class FetchCountryData {

    List<String> listOfCountries;

    public FetchCountryData(List<String> list) {
        listOfCountries = list;
    }

    public void getCountriesInformation() throws FileNotFoundException, IOException {

        System.out.println(listOfCountries);
        ParameterizedSparqlString query;
        //FileOutputStream file;
        File f = new File("C:/Users/leons/Desktop/CountryData.csv");
        FileOutputStream file = new FileOutputStream(f);
        System.out.println(listOfCountries.get(5).contains(" "));
        listOfCountries = processCountryNames(listOfCountries);
        for (String country : listOfCountries) {
//            ParameterizedSparqlString qs = new ParameterizedSparqlString("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n "
//                    + "SELECT * {     ?Subject rdf:type <http://dbpedia.org/page/" + listOfCountries.get(0) + "> .     ?Subject ?Predicate ?Object } ORDER BY ?Subject LIMIT 10000 OFFSET 10000");
            ParameterizedSparqlString qs = new ParameterizedSparqlString("SELECT * WHERE {\n"
                    + "  <http://dbpedia.org/resource/" + country + "> ?p ?o .\n"
                    + "}");

            QueryExecution exec = QueryExecutionFactory.sparqlService("https://dbpedia.org/sparql", qs.asQuery());
            //exec.setTimeout(10000000);
            exec.setTimeout(10, TimeUnit.MINUTES);
            ResultSet results = exec.execSelect();
            //ResultSetFormatter.outputAsCSV(new FileOutputStream(new File("C:/Users/leons/Desktop/CountryData.csv")), results);
            ResultSetFormatter.outputAsCSV(file, results);
            //ResultSetFormatter.out(results);
        }
        //CSVCountryNameFetcher countryNameFetcher = new CSVCountryNameFetcher();
        //countryNameFetcher.fetchDataFromCSV();

    }

    public List<String> processCountryNames(List<String> list) {
        String newName = null;
        for (String country : list) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(" ")) {
                    newName = list.get(i).replaceAll(" ", "_");
                    list.set(i, newName);
                }
            }
        }
        System.out.println(list);

        return list;
    }

}
