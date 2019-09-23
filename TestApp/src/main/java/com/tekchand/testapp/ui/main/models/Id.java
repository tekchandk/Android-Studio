package com.tekchand.testapp.ui.main.models;

import androidx.annotation.NonNull;

public class Id{
	private String kind;
	private String videoId;

	public void setKind(@NonNull final String kind){
		this.kind = kind;
	}

	public String getKind(){
		return kind;
	}

	public void setVideoId(@NonNull final String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return videoId;
	}

	@Override
 	public String toString(){
		return 
			"Id{" + 
			"kind = '" + kind + '\'' + 
			",videoId = '" + videoId + '\'' + 
			"}";
		}
}
