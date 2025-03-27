package com.mjc.school.controller.implementation;



import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import org.springframework.stereotype.Component;
import com.mjc.school.controller.BaseController;
import java.util.ArrayList;
import java.util.List;

@Component
//public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {
public class AuthorController implements BaseController<String, Object, Long> {
/*    private final BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService;
    @Autowired
    public AuthorController(BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> authorService) {
        this.authorService = authorService;
        System.out.println("AuthorController создан");
    }*/

    @CommandHandler(operation = "Get all authors")
    @Override
    public List<Object> readAll() {
        System.out.println("readAll() from AuthorController \n");
        return new ArrayList<>();
        /*List<AuthorDtoResponse> responses = authorService.readAll();
        System.out.println(printAuthor(responses.toArray(new AuthorDtoResponse[0])));*/
        //return responses;
        //return authorService.readAll();
    }

    @CommandHandler(operation = "Get author by id")
    @Override
    public Object readById(@CommandParam("id") Long id) {
        System.out.println("readById() from AuthorController \n");
        return new Object();
        /*AuthorDtoResponse response = authorService.readById(id);
        System.out.println(printAuthor(response));
        return response;*/
        //return authorService.readById(id);
    }

    @CommandHandler(operation = "Create author")
    @Override
    public Object create(@CommandBody String createRequest) {
        System.out.println("create() from AuthorController \n");
        return new Object();
        /*AuthorDtoResponse response = authorService.create(createRequest);
        System.out.println(printAuthor(response));
        return response;*/
        //return authorService.create(createRequest);
    }

    @CommandHandler(operation = "Update authors")
    @Override
    public Object update(@CommandBody String updateRequest) {
        System.out.println("update() from AuthorController \n");
        return new Object();
       /* AuthorDtoResponse response = authorService.update(updateRequest);
        System.out.println(printAuthor(response));
        return response;*/
        //return authorService.update(updateRequest);
    }

    @CommandHandler(operation = "Remove author by id")
    @Override
    public boolean deleteById(@CommandParam("id") Long id) {
        System.out.println("deleteById() from AuthorController \n");
        return true;
       /* boolean response = authorService.deleteById(id);
        System.out.println("Author was deleted: " + response);
        return response;*/
        //return authorService.deleteById(id);
    }

/*    private String printAuthor(AuthorDtoResponse... response) {
        StringBuilder builder = new StringBuilder();
        for (AuthorDtoResponse n : response) {
            builder.append("AuthorDtoResponse [id=").append(n.getId())
                    .append(", name=").append(n.getName())
                    .append(", createDate=").append(n.getCreateDate())
                    .append(", lastUpdatedDate=").append(n.getLastUpdateDate())
                    .append("]").append("\n");
        }
        return builder.toString();
    }*/
}
