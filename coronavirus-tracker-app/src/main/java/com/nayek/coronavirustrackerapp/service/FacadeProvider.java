package com.nayek.coronavirustrackerapp.service;

import com.nayek.coronavirustrackerapp.model.CoronaVirusStatsModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class FacadeProvider {

    private ClientWrapper clientWrapper;

    public List<CoronaVirusStatsModel> getData() throws IOException {

        String CASES_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
        ResponseEntity<String> response = clientWrapper.retrieveCases(CASES_DATA_URL, String.class);

        StringReader csvbodyReader = new StringReader(response.getBody());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyReader);

        return StreamSupport.stream(records.spliterator(), false).map(this::convert).collect(Collectors.toList());

    }

    private CoronaVirusStatsModel convert(final CSVRecord singleRecord) {

        CoronaVirusStatsModel cvst = new CoronaVirusStatsModel();
        cvst.setState(singleRecord.get("Province/State"));
        cvst.setCountry(singleRecord.get("Country/Region"));
        cvst.setLatitude(singleRecord.get("Lat"));
        cvst.setLongitude(singleRecord.get("Long"));
        cvst.setLatestTotalCases(singleRecord.get(singleRecord.size() - 1));

        return cvst;
    }

    @Autowired
    public void setClientWrapper(final ClientWrapper clientWrapper) {
        this.clientWrapper = clientWrapper;
    }
}
