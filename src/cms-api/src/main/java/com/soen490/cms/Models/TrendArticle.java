package com.soen490.cms.Models;

import com.kwabenaberko.newsapilib.models.Article;
import lombok.Data;

@Data
public class TrendArticle {

    private TrendSource source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String type; // "relevant" or "trend"

    public TrendArticle(Article article, String type){
        this.source = new TrendSource(article.getSource());
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.url = article.getUrl();
        this.urlToImage = article.getUrlToImage();
        this.publishedAt = article.getPublishedAt();
        this.type = type;
    }
}
