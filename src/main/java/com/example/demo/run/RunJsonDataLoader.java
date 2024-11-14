package com.example.demo.run;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RunJsonDataLoader implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);

	private final JdbcClientRunRepository runRepository;
	private final ObjectMapper objectMapper;

	public RunJsonDataLoader(JdbcClientRunRepository runRepository, ObjectMapper objectMapper) {
		this.runRepository = runRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		if (runRepository.count() == 0) {
			try (InputStream inputStream = getClass().getResourceAsStream("/data/runs.json")) {
				if (inputStream == null) {
					throw new RuntimeException("File /data/runs.json not found");
				}
				List<Run> allRuns = objectMapper.readValue(inputStream, new TypeReference<List<Run>>() {
				});
				log.info("Reading {} runs from JSON data and saving it to a database", allRuns.size());
				runRepository.saveAll(allRuns);
			} catch (IOException e) {
				throw new RuntimeException("Failed to read JSON data", e);
			}
		} else {
			log.info("Not loading Runs from JSON data because the collection contains data.");
		}
	}
}
