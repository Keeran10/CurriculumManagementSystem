package com.soen490.cms.Services;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Degree;
import com.soen490.cms.Models.DegreeRequirement;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.DegreeRequirementRepository;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Service
public class TrendService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DegreeRequirementRepository degreeRequirementRepository;
    @Autowired
    DegreeRepository degreeRepository;

    static int MAX_ARTICLES = 3;

    public List<Article> getTrends(String requestForm){

        ArrayList<Article> articles = null;
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
        List<String> keywords = getKeywords(present, proposed);
        String keyword = "";

        for(String k: keywords)
            keyword += k;

        NewsApiClient newsApiClient = new NewsApiClient("0582968f2d9547518781438e31b66f87");
        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(keyword)
                        .domains(present.getProgram().getName())
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        int ctr = 0;
                        for(Article article : response.getArticles()){
                            articles.add(article);
                            ctr++;
                            if(ctr == MAX_ARTICLES)
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );

        return articles;
    }

    // keywords heuristic
    public List<String> getKeywords(Course present, Course proposed){

        String credit_keyword = getCreditKeyword(present, proposed);
        String description_keyword = getDescriptionKeyword(present, proposed);

        // delete proposed course from database; might not be needed if temp is sufficient (i.e. no heuristic for degree requirements)
        for(DegreeRequirement dr : proposed.getDegreeRequirements()){
            degreeRequirementRepository.delete(dr);
        }
        courseRepository.delete(proposed);

        return null;
    }

    private String getDescriptionKeyword(Course present, Course proposed) {

        return null;
    }

    private String getCreditKeyword(Course present, Course proposed) {

        return null;
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
