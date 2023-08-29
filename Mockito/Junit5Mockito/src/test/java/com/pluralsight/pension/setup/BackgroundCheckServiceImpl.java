package com.pluralsight.pension.setup;

import java.io.IOException;
import java.time.LocalDate;

public class BackgroundCheckServiceImpl implements BackgroundCheckService {

	@Override
	public BackgroundCheckResults confirm(String firstName, String lastName, String taxId, LocalDate dob)
			throws IOException {
		// TODO Auto-generated method stub
		return new BackgroundCheckResults("LOW", Long.valueOf(100));
	}

}
