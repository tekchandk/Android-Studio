package com.tekchand.testapp.ui.main.models;

import androidx.annotation.NonNull;

public class Snippet{
	private String publishedAt;
	private String description;
	private String title;
	private Thumbnails thumbnails;
	private String channelId;
	private String channelTitle;
	private String liveBroadcastContent;

	public void setPublishedAt(@NonNull final String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setDescription(final String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(@NonNull final String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setThumbnails(@NonNull final Thumbnails thumbnails){
		this.thumbnails = thumbnails;
	}

	public Thumbnails getThumbnails(){
		return thumbnails;
	}

	public void setChannelId(@NonNull final String channelId){
		this.channelId = channelId;
	}

	public String getChannelId(){
		return channelId;
	}

	public void setChannelTitle(@NonNull final String channelTitle){
		this.channelTitle = channelTitle;
	}

	public String getChannelTitle(){
		return channelTitle;
	}

	public void setLiveBroadcastContent(@NonNull final String liveBroadcastContent){
		this.liveBroadcastContent = liveBroadcastContent;
	}

	public String getLiveBroadcastContent(){
		return liveBroadcastContent;
	}

	@Override
 	public String toString(){
		return 
			"Snippet{" + 
			"publishedAt = '" + publishedAt + '\'' + 
			",description = '" + description + '\'' + 
			",title = '" + title + '\'' + 
			",thumbnails = '" + thumbnails + '\'' + 
			",channelId = '" + channelId + '\'' + 
			",channelTitle = '" + channelTitle + '\'' + 
			",liveBroadcastContent = '" + liveBroadcastContent + '\'' + 
			"}";
		}
}
