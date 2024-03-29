package com.tekchand.testapp.ui.main.models;

import androidx.annotation.NonNull;

public class Medium{
	private int width;
	private String url;
	private int height;

	public void setWidth(@NonNull final int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setUrl(@NonNull final String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHeight(@NonNull final int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"Medium{" + 
			"width = '" + width + '\'' + 
			",url = '" + url + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}
