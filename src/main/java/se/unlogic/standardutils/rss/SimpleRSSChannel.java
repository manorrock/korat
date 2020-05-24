package se.unlogic.standardutils.rss;

import java.util.Collection;
import java.util.Date;

public class SimpleRSSChannel implements RSSChannel {

	private String title;
	private String description;
	private String link;
	private String feedLink;
	private Integer itemsPerChannel;
	private String managingEditor;
	private String webmaster;
	private String copyright;
	private String language;
	private Integer ttl;
	private Date lastBuildDate;
	private Date pubDate;
	private Collection<String> categories;

	@Override
	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	@Override
	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	@Override
	public String getLink() {

		return link;
	}

	public void setLink(String link) {

		this.link = link;
	}

	@Override
	public Integer getItemsPerChannel() {

		return itemsPerChannel;
	}

	public void setItemsPerChannel(Integer itemsPerChannel) {

		this.itemsPerChannel = itemsPerChannel;
	}

	@Override
	public String getManagingEditor() {

		return managingEditor;
	}

	public void setManagingEditor(String managingEditor) {

		this.managingEditor = managingEditor;
	}

	@Override
	public String getWebmaster() {

		return webmaster;
	}

	public void setWebmaster(String webmaster) {

		this.webmaster = webmaster;
	}

	@Override
	public String getCopyright() {

		return copyright;
	}

	public void setCopyright(String copyright) {

		this.copyright = copyright;
	}

	@Override
	public String getLanguage() {

		return language;
	}

	public void setLanguage(String language) {

		this.language = language;
	}

	@Override
	public Integer getTtl() {

		return ttl;
	}

	public void setTtl(Integer ttl) {

		this.ttl = ttl;
	}

	@Override
	public Date getLastBuildDate() {

		return lastBuildDate;
	}

	public void setLastBuildDate(Date lastBuildDate) {

		this.lastBuildDate = lastBuildDate;
	}

	@Override
	public Date getPubDate() {

		return pubDate;
	}

	public void setPubDate(Date pubDate) {

		this.pubDate = pubDate;
	}

	@Override
	public Collection<String> getCategories() {

		return categories;
	}

	public void setCategories(Collection<String> categories) {

		this.categories = categories;
	}

	@Override
	public String getFeedLink() {

		return feedLink;
	}

	public void setFeedLink(String feedLink) {

		this.feedLink = feedLink;
	}
}
