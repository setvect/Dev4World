package com.dev4world.example.z_etc;

public class NewsArticle {
	public String title;
	public String content;
	public String auth;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "NewsArticle [title=" + title + ", content=" + content + ", auth=" + auth + "]";
	}

}
