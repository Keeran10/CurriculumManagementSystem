// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package com.soen490.cms.Controllers;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.soen490.cms.Models.TrendArticle;
import com.soen490.cms.Services.MailService;
import com.soen490.cms.Services.TrendService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NewsFetchingController {

    @Autowired
    private TrendService trendService;

    Article articleFound = null;
    /**
     * endpoint of: /TopNews
     * takes as parameter the keyword to search a news in
     *
     * @param keyword
     * @return mail sent
     */
    @GetMapping(value = "/TopNews")
    public Article getNews(@RequestParam String keyword){
        log.info("Fetching News");
        articleFound = null;
        NewsApiClient newsApiClient = new NewsApiClient("0582968f2d9547518781438e31b66f87");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q(keyword)
                        .category("technology")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        System.err.println("Fetching the first news from Top Headlines");
                        System.err.println(response.getTotalResults());
                        System.err.println(response.getArticles());
                        articleFound = response.getArticles().get(0);
                        System.err.println("URL: "+articleFound.getUrl());
                        System.err.println("Title: "+articleFound.getTitle());
                        System.err.println("Description: "+articleFound.getDescription());

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return articleFound;
    }


    @PostMapping(value = "check_trends")
    public List<TrendArticle> getTrends(@RequestBody String requestForm){

        return trendService.getArticles(requestForm);
    }

    public void setArticleFoundMock(Article articleMock){
        articleFound = articleMock;
    }
}
