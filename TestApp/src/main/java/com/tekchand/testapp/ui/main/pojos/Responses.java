package com.tekchand.testapp.ui.main.pojos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Responses{
	private String regionCode;
	private String kind;
	private String nextPageToken;
	private PageInfo pageInfo;
	private String etag;
	@SerializedName("items")
	private List<ItemsItem> items;

	public void setRegionCode(@NonNull final String regionCode){
		this.regionCode = regionCode;
	}

	public String getRegionCode(){
		return regionCode;
	}

	public void setKind(@NonNull final String kind){
		this.kind = kind;
	}


	public String getKind(){
		return kind;
	}

	public void setNextPageToken(@NonNull final String nextPageToken){
		this.nextPageToken = nextPageToken;
	}

	public String getNextPageToken(){
		return nextPageToken;
	}

	public void setPageInfo(@NonNull final PageInfo pageInfo){
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo(){
		return pageInfo;
	}

	public void setEtag(@NonNull final String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setItems(@NonNull final List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"regionCode = '" + regionCode + '\'' + 
			",kind = '" + kind + '\'' + 
			",nextPageToken = '" + nextPageToken + '\'' + 
			",pageInfo = '" + pageInfo + '\'' + 
			",etag = '" + etag + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}