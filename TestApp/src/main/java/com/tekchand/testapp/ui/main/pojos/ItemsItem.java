package com.tekchand.testapp.ui.main.pojos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{
	private Snippet snippet;
	private String kind;
	private String etag;
	private Id id;

	public void setSnippet(@NonNull final Snippet snippet){
		this.snippet = snippet;
	}

	public Snippet getSnippet(){
		return snippet;
	}

	public void setKind(@NonNull final String kind){
		this.kind = kind;
	}

	@SerializedName("kind")
	public String getKind(){
		return kind;
	}

	public void setEtag(@NonNull final String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setId(@NonNull final Id id){
		this.id = id;
	}

	public Id getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"snippet = '" + snippet + '\'' + 
			",kind = '" + kind + '\'' + 
			",etag = '" + etag + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
