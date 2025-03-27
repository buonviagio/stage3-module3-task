package com.mjc.school.controller.implementation;

import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import org.springframework.stereotype.Component;
import com.mjc.school.controller.BaseController;
import java.util.ArrayList;
import java.util.List;

@Component
//public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
public class NewsController implements BaseController<String, Object, Long> {

    /*private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService) {
        this.newsService = newsService;
        System.out.println("NewsController создан");
    }*/

    @CommandHandler(operation = "Get all news")
    @Override
    public List<Object> readAll() {
        System.out.println("readAll() from NewsController \n");
        return new ArrayList<>();
        /*List<NewsDtoResponse> response = newsService.readAll();
        System.out.println(printNews(newsService.readAll().toArray(new NewsDtoResponse[0])));
        return response;*/
    }

    @CommandHandler(operation = "Get news by id")
    @Override
    public Object readById(@CommandParam("id") Long id) {
        System.out.println("readById() from NewsController \n");
        return new Object();
        /*NewsDtoResponse response = newsService.readById(id);
        System.out.println(printNews(response));
        return response;*/
    }

    @CommandHandler(operation = "Create news")
    @Override
    public Object create(@CommandBody String createRequest) {
        System.out.println("create() from NewsController \n");
        return new Object();
       /* NewsDtoResponse response = newsService.create(createRequest);
        System.out.println(printNews(response));
        return response;*/
    }

    @CommandHandler(operation = "Update news")
    @Override
    public Object update(@CommandBody String updateRequest) {
        System.out.println("update() from NewsController \n");
        return new Object();
        /*NewsDtoResponse response = newsService.update(updateRequest);
        System.out.println(printNews(response));
        return response;*/
    }

    @CommandHandler(operation = "Remove news by id")
    @Override
    public boolean deleteById(@CommandParam("id") Long id) {
        System.out.println("deleteById() from NewsController \n");
        return true;
        //return newsService.deleteById(id);
    }

    /*private String printNews(NewsDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (NewsDtoResponse n : response) {
            builder.append("NewsDtoResponse [id=").append(n.getId())
                    .append(", title=").append(n.getName())
                    .append(", content=").append(n.getContent())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append(", authorId=").append(n.getAuthorId()).append("]")
                    .append("\n");
        }
        return builder.toString();
    }*/
}
