/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.sw;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.crce.sw.interfaces.PathInterface;

/**
 *
 * @author Leon
 */
public class StartClass implements PathInterface{
    public static void main(String [] args) throws FileNotFoundException, IOException{
        new ServiceStateCheck();
        
        FetchCountryName fetchCountryData = new FetchCountryName();
        fetchCountryData.fetchData();
        CSVReaderUtil csv = new CSVReaderUtil(srcMainResourcesPath + "Countries.csv");
        //csv.readCSV();
        FetchCountryData fetchCountriesData = new FetchCountryData(csv.readCSV());
        fetchCountriesData.getCountriesInformation();
        
//        CSVReaderUtil csv = new CSVReaderUtil(Countries.csv");
//        csv.readCSV();
        
    }
}
