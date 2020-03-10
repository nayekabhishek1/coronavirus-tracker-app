package com.nayek.coronavirustrackerapp.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nayek.coronavirustrackerapp.model.CoronaVirusStatsModel;

@Service
public class CoronaAllDataService {

	private static String CASES_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
	private List<CoronaVirusStatsModel> mainList = new ArrayList<>();

	public List<CoronaVirusStatsModel> getMainList() {
		return mainList;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getVirusData() throws IOException {

		List<CoronaVirusStatsModel> tempList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(CASES_DATA_URL, String.class);
		// System.out.print(response.getBody());

		StringReader csvbodyReader = new StringReader(response.getBody());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyReader);

		for (CSVRecord record : records) {

			CoronaVirusStatsModel cvst = new CoronaVirusStatsModel();
			cvst.setState(record.get("Province/State"));
			cvst.setCountry(record.get("Country/Region"));
			cvst.setLatitude(record.get("Lat"));
			cvst.setLongitude(record.get("Long"));
			cvst.setLatestTotalCases(record.get(record.size() - 1));

			tempList.add(cvst);
		}
		this.mainList = tempList;
	}
}
