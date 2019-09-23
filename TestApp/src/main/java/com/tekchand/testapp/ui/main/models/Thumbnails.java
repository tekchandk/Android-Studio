package com.tekchand.testapp.ui.main.models;

import androidx.annotation.NonNull;

public class Thumbnails{
	private JsonMemberDefault jsonMemberDefault;
	private High high;
	private Medium medium;

	public void setJsonMemberDefault(JsonMemberDefault jsonMemberDefault){
		this.jsonMemberDefault = jsonMemberDefault;
	}

	public JsonMemberDefault getJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public void setHigh(@NonNull final High high){
		this.high = high;
	}

	public High getHigh(){
		return high;
	}

	public void setMedium(@NonNull final Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	@Override
 	public String toString(){
		return 
			"Thumbnails{" + 
			"default = '" + jsonMemberDefault + '\'' + 
			",high = '" + high + '\'' + 
			",medium = '" + medium + '\'' + 
			"}";
		}
}
