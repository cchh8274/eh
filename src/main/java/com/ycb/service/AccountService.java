package com.ycb.service;

import java.util.HashMap;
import java.util.List;

public interface AccountService {

	List<String> queryAccount(String yesterday);

	void insertAccount(List<HashMap> batchlist) throws Exception;

}
