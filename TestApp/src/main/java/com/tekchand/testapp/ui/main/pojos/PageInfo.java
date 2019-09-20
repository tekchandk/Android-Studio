package com.tekchand.testapp.ui.main.pojos;

import androidx.annotation.NonNull;

public class PageInfo{
	private int totalResults;
	private int resultsPerPage;

	public void setTotalResults(@NonNull final int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setResultsPerPage(@NonNull final int resultsPerPage){
		this.resultsPerPage = resultsPerPage;
	}

	public int getResultsPerPage(){
		return resultsPerPage;
	}

	@Override
 	public String toString(){
		return 
			"PageInfo{" + 
			"totalResults = '" + totalResults + '\'' + 
			",resultsPerPage = '" + resultsPerPage + '\'' + 
			"}";
		}
}
