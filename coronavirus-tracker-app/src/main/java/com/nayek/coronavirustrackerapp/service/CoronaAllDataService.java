package com.nayek.coronavirustrackerapp.service;

import com.nayek.coronavirustrackerapp.model.CoronaVirusStatsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaAllDataService {

	private List<CoronaVirusStatsModel> mainList = new ArrayList<>();
	private FacadeProvider provider;

	public List<CoronaVirusStatsModel> getMainList() {
		return mainList;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getVirusData() throws IOException {

		this.mainList = provider.getData();
	}

	@Autowired
	public void setProvider(final FacadeProvider provider) {
		this.provider = provider;
	}
}
