/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.sw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.crce.sw.interfaces.PathInterface;

/**
 *
 * @author Leon
 */
public class FetchCountryName implements PathInterface{

    public void fetchData() {
        /*ParameterizedSparqlString qs = new ParameterizedSparqlString("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
                "SELECT * {     ?Subject rdf:type <http://dbpedia.org/ontology/Country> .     ?Subject ?Predicate ?Object } ORDER BY ?Subject "
        );*/
 /*ParameterizedSparqlString qs = new ParameterizedSparqlString("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
                + "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n"
                + "PREFIX dbo: <http://dbpedia.org/ontology/>\n"
                + "\n"
                + "SELECT distinct ?country ?capital ?caplat ?caplong\n"
                + "WHERE {\n"
                + "  ?country rdf:type dbo:Country .\n"
                + "  ?country  dbo:capital ?capital .\n"
                + "}\n"
                + "ORDER BY ?country"); */
        ParameterizedSparqlString qs = new ParameterizedSparqlString("PREFIX wd: <http://www.wikidata.org/entity/>\n"
                + "PREFIX wdt: <http://www.wikidata.org/prop/direct/>\n"
                + "PREFIX wikibase: <http://wikiba.se/ontology#>\n "
                + "PREFIX bd: <http://www.bigdata.com/rdf#>\n"
                + " SELECT DISTINCT ?countryLabel\n"
                + "WHERE\n"
                + "{\n"
                + "  ?country wdt:P31 wd:Q3624078 .\n"
                + "  FILTER NOT EXISTS {?country wdt:P31 wd:Q3024240}\n"
                + "  OPTIONAL { ?country wdt:P36 ?capital } .\n"
                + "\n"
                + "  SERVICE wikibase:label { bd:serviceParam wikibase:language \"en\" }\n"
                + "}\n"
                + "ORDER BY ?countryLabel");

        QueryExecution exec = QueryExecutionFactory.sparqlService("https://query.wikidata.org/sparql", qs.asQuery());
        //exec.setTimeout(10000000);
        exec.setTimeout(-123);
        ResultSet results = exec.execSelect();
        //ResultSetFormatter.out(results);
        try {
            ResultSetFormatter.outputAsCSV(new FileOutputStream(new File(srcMainResourcesPath + "Countries.csv")), results);
//        while (results.hasNext()) {
//            System.out.println(results.next());
//            System.out.println("Entered here");
//            count = count + 1;
//            System.out.println(count);
//        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FetchCountryName.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
