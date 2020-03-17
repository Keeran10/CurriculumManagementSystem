package com.soen490.cms.Services;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Degree;
import com.soen490.cms.Models.DegreeRequirement;
import com.soen490.cms.Models.TrendArticle;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.DegreeRequirementRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Log4j2
@Service
public class TrendService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DegreeRequirementRepository degreeRequirementRepository;
    @Autowired
    DegreeRepository degreeRepository;

    static int MAX_RELEVANT_ARTICLES = 3;
    static int MAX_TRENDING_ARTICLES = 3;
    List<Article> articles = null;
    static int year = Calendar.getInstance().get(Calendar.YEAR);
    static List<String> stopwords;

    // text file provided by https://www.baeldung.com/java-string-remove-stopwords
    public static void loadStopwords() throws IOException {
        stopwords = Files.readAllLines(Paths.get("src/main/resources/english_stopwords.txt"));
    }


    /**
     * Takes in a course change request and returns a list of trending or relevant articles
     * @param requestForm course info provided by user
     * @return a list of articles either of type "relevant" or "trending"
     */
    public List<TrendArticle> getArticles(String requestForm){

        try {
            loadStopwords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<TrendArticle> articlesToReturn = new ArrayList<>();

        ArrayList<Course> targets;
        try {
            targets = getTargetCourses(requestForm);
            if(targets == null)
                return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        Course present = targets.get(0);
        Course proposed = targets.get(1);

        List<TrendArticle> trendingArticles = getTrendingArticles(present, proposed);
        List<TrendArticle> relevantArticles = getRelevantArticles(present, proposed);
        List<TrendArticle> growthArticles = getGrowthArticles(present, proposed);
        List<TrendArticle> localArticles = getLocalArticles(present, proposed);

        if(relevantArticles != null && relevantArticles.size() > MAX_RELEVANT_ARTICLES) {

            int ctr = 0;
            for (TrendArticle article : relevantArticles) {
                boolean exist = false;
                if(article.getAuthor() == null || article.getTitle() == null || article.getDescription() == null ||
                        article.getTitle().contains("$") || article.getDescription().contains("$") ||
                        article.getTitle().contains("be single") || article.getDescription().contains("be single"))
                    continue;
                log.info(article.getTitle());
                for(TrendArticle a : articlesToReturn){
                    if(article.getTitle().equals(a.getTitle())) {
                        exist = true;
                        break;
                    }
                }
                if(exist)
                    continue;
                articlesToReturn.add(article);
                ctr++;
                if(ctr == MAX_RELEVANT_ARTICLES)
                    break;
            }
        }

        if(trendingArticles != null && trendingArticles.size() > MAX_TRENDING_ARTICLES) {

            int ctr = 0;
            for (TrendArticle article : trendingArticles) {
                boolean exist = false;
                if(article.getAuthor() == null || article.getTitle() == null || article.getDescription() == null ||
                        article.getTitle().contains("$") || article.getDescription().contains("$") ||
                        article.getTitle().contains("be single") || article.getDescription().contains("be single"))
                    continue;
                log.info(article.getTitle());
                for(TrendArticle a : articlesToReturn){
                    if(article.getTitle().equals(a.getTitle())) {
                        exist = true;
                        break;
                    }
                }
                if(exist)
                    continue;
                articlesToReturn.add(article);
                ctr++;
                if(ctr == MAX_RELEVANT_ARTICLES)
                    break;
            }
        }

        if(growthArticles != null && growthArticles.size() > MAX_TRENDING_ARTICLES) {

            int ctr = 0;
            for (TrendArticle article : growthArticles) {
                boolean exist = false;
                if(article.getAuthor() == null || article.getTitle() == null || article.getDescription() == null ||
                        article.getTitle().contains("$") || article.getDescription().contains("$") ||
                        article.getTitle().contains("be single") || article.getDescription().contains("be single") ||
                        article.getTitle().contains("Q1") || article.getDescription().contains("Q1") ||
                        article.getTitle().contains("Q2") || article.getDescription().contains("Q2") ||
                        article.getTitle().contains("Q3") || article.getDescription().contains("Q3") ||
                        article.getTitle().contains("Q4") || article.getDescription().contains("Q4"))
                    continue;
                log.info(article.getTitle());
                for(TrendArticle a : articlesToReturn){
                    if(article.getTitle().equals(a.getTitle())) {
                        exist = true;
                        break;
                    }
                }
                if(exist)
                    continue;
                articlesToReturn.add(article);
                ctr++;
                if(ctr == MAX_RELEVANT_ARTICLES)
                    break;
            }
        }

        if(localArticles != null && localArticles.size() > MAX_TRENDING_ARTICLES) {

            int ctr = 0;
            for (TrendArticle article : localArticles) {
                boolean exist = false;
                if(article.getAuthor() == null || article.getTitle() == null || article.getDescription() == null ||
                        article.getTitle().contains("$") || article.getDescription().contains("$") ||
                        article.getTitle().contains("be single") || article.getDescription().contains("be single") ||
                        article.getTitle().contains("Q1") || article.getDescription().contains("Q1") ||
                        article.getTitle().contains("Q2") || article.getDescription().contains("Q2") ||
                        article.getTitle().contains("Q3") || article.getDescription().contains("Q3") ||
                        article.getTitle().contains("Q4") || article.getDescription().contains("Q4"))
                    continue;
                log.info(article.getTitle());
                for(TrendArticle a : articlesToReturn){
                    if(article.getTitle().equals(a.getTitle())) {
                        exist = true;
                        break;
                    }
                }
                if(exist)
                    continue;
                articlesToReturn.add(article);
                ctr++;
                if(ctr == MAX_RELEVANT_ARTICLES)
                    break;
            }
        }

        for(DegreeRequirement dr : proposed.getDegreeRequirements()){
            degreeRequirementRepository.delete(dr);
        }
        courseRepository.delete(proposed);

        return articlesToReturn;
    }

    public List<TrendArticle> getRelevantArticles(Course present, Course proposed){

        List<String> descriptionKeywords = getDescriptionKeywords(present, proposed);
        List<String> titleKeywords = getTitleKeywords(present, proposed);
        String keyword = "";

        for (String k : titleKeywords)
            keyword += k + " AND ";

        keyword = keyword + "(";

        for (String k : descriptionKeywords)
            keyword += k + " OR ";

        keyword = keyword.trim();
        keyword = keyword.substring(0, keyword.length() - 3);
        keyword = keyword + ")";

        System.out.println(keyword);

        NewsApiClient newsApiClient = new NewsApiClient("0582968f2d9547518781438e31b66f87");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(keyword)
                        .language("en")
                        .sortBy("relevancy")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articles = response.getArticles();
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

        if(articles == null)
            return null;

        System.out.println("Relevant Articles: " + articles.size());

        List<TrendArticle> articlesToReturn = new ArrayList<>();

        for(Article a : articles)
            articlesToReturn.add(new TrendArticle(a, "relevant"));

        return articlesToReturn;
    }


    private List<TrendArticle> getTrendingArticles(Course present, Course proposed){

        String keyword = proposed.getProgram().getName().toLowerCase() + " AND " + "(job OR jobs OR education)";

        System.out.println(keyword);

        NewsApiClient newsApiClient = new NewsApiClient("0582968f2d9547518781438e31b66f87");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(keyword)
                        .language("en")
                        .sortBy("popularity")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articles = response.getArticles();
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

        if(articles == null)
            return null;

        System.out.println("Trending Articles: " + articles.size());

        List<TrendArticle> articlesToReturn = new ArrayList<>();

        for(Article a : articles)
            articlesToReturn.add(new TrendArticle(a, "trend"));

        return articlesToReturn;
    }

    private List<TrendArticle> getLocalArticles(Course present, Course proposed){

        String keyword = proposed.getProgram().getName().toLowerCase() + " AND (montreal OR quebec OR canada)";// AND " + "(job OR jobs OR education)";

        System.out.println(keyword);

        NewsApiClient newsApiClient = new NewsApiClient("0582968f2d9547518781438e31b66f87");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(keyword)
                        .language("en")
                        .sortBy("relevancy")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articles = response.getArticles();
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

        if(articles == null)
            return null;

        System.out.println("Local News Articles: " + articles.size());

        List<TrendArticle> articlesToReturn = new ArrayList<>();

        for(Article a : articles)
            articlesToReturn.add(new TrendArticle(a, "local_news"));

        return articlesToReturn;
    }

    private List<TrendArticle> getGrowthArticles(Course present, Course proposed) {

        List<String> programKeywords = getProgramKeywords(present, proposed);
        List<String> titleKeywords = getTitleKeywords(present, proposed);
        String keyword = "((";
        // add program words
        for(String k : programKeywords) {
            keyword += k.toLowerCase() + " AND ";
        }
        keyword = keyword.trim();
        keyword = keyword.substring(0, keyword.length() - 4);
        keyword = keyword + ")";
        keyword += " OR (";
        // add title words
        for(String k : titleKeywords) {
            keyword += k.toLowerCase() + " AND ";
        }
        keyword = keyword.trim();
        keyword = keyword.substring(0, keyword.length() - 4);
        keyword = keyword + ")";

        keyword += ") AND (growth OR expansion) AND (job OR jobs)";

        System.out.println(keyword);

        NewsApiClient newsApiClient = new NewsApiClient("0582968f2d9547518781438e31b66f87");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(keyword)
                        .language("en")
                        .sortBy("relevancy")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        articles = response.getArticles();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );

        if(articles == null) {
            return null;
        }

        System.out.println("Growth articles: " + articles.size());

        List<TrendArticle> growthArticles = new ArrayList<>();

        for(Article a : articles)
            growthArticles.add(new TrendArticle(a, "growth"));

        return growthArticles;
    }

    private List<String> getProgramKeywords(Course present, Course proposed) {

        String rawKeywords = proposed.getProgram().getName();

        rawKeywords = rawKeywords.replaceAll("[^a-zA-Z ]", "");

        ArrayList<String> keywords =
                Stream.of(rawKeywords.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        keywords.removeAll(stopwords);

        return keywords;
    }


    public List<String> getTitleKeywords(Course present, Course proposed){

        String rawKeywords = proposed.getTitle();

        rawKeywords = rawKeywords.replaceAll("[^a-zA-Z ]", "");

        ArrayList<String> keywords =
                Stream.of(rawKeywords.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        keywords.removeAll(stopwords);

        return keywords;
    }

    public List<String> getDescriptionKeywords(Course present, Course proposed){

        String rawKeywords = proposed.getDescription();

        rawKeywords = rawKeywords.replaceAll("[^a-zA-Z ]", "");

        ArrayList<String> keywords =
                Stream.of(rawKeywords.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        keywords.removeAll(stopwords);

        return keywords;
    }


    public ArrayList<Course> getTargetCourses(String requestForm) throws JSONException {

        log.info("Json received for getTargetCourses: " + requestForm);

        JSONObject json = new JSONObject(requestForm);
        JSONArray array = json.getJSONObject("params").getJSONArray("updates");
        JSONObject course = new JSONObject((String) array.getJSONObject(0).get("value"));
        JSONObject courseExtras = new JSONObject((String) array.getJSONObject(1).get("value"));

        int original_id = (Integer) course.get("id");

        Course original = null;

        if (original_id != 0)
            original = courseRepository.findById(original_id);

        if(original == null)
            return null;

        Course c = new Course();
        c.setName((String) course.get("name"));
        c.setNumber(Integer.valueOf((String.valueOf(course.get("number")))));
        c.setTitle((String) course.get("title"));
        c.setCredits(Double.valueOf(String.valueOf(course.get("credits"))));
        c.setDescription((String) course.get("description"));
        c.setLevel((Integer) course.get("level"));
        c.setNote((String) course.get("note"));
        c.setLabHours(Double.valueOf(String.valueOf(course.get("labHours"))));
        c.setTutorialHours(Double.valueOf(String.valueOf(course.get("tutorialHours"))));
        c.setLectureHours(Double.valueOf(String.valueOf(course.get("lectureHours"))));
        c.setIsActive(0);

        courseRepository.save(c);

        // Set degree requirements
        ArrayList<DegreeRequirement> list = new ArrayList<>();
        int size = course.getJSONArray("degreeRequirements").length();
        int ctr = 0;

        for(int i = 0; i < size; i++){

            JSONObject degreeRequirements = (JSONObject) course.getJSONArray("degreeRequirements").get(i);

            JSONObject degreeJSON = (JSONObject) degreeRequirements.get("degree");
            int degree_id = (Integer) degreeJSON.get("id");


            Degree degree = degreeRepository.findById(degree_id);
            String core = (String) degreeRequirements.get("core");

            if(ctr == 0 && degree != null){
                c.setProgram(degree.getProgram());
                courseRepository.save(c);
                ctr++;
            }

            DegreeRequirement cdr = new DegreeRequirement();

            if(core == null || degree == null)
                continue;

            cdr.setCore(core);
            cdr.setDegree(degree);
            cdr.setCourse(c);

            degreeRequirementRepository.save(cdr);
            list.add(cdr);
        }

        c.setDegreeRequirements(list);

        ArrayList<Course> impact_courses = new ArrayList<>();
        impact_courses.add(original);
        impact_courses.add(c);
        return impact_courses;
    }
}
