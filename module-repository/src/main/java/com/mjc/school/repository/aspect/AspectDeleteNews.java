package com.mjc.school.repository.aspect;

import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/*@Aspect
@Component
public class AspectDeleteNews {
    DataSource dataSource = DataSource.getInstance();

    @AfterReturning(value = "@annotation(com.mjc.school.repository.annotations.DeleteNews) " +
            "&& args(id)", returning = "result")
    public void afterAuthorDelete(Long id, boolean result) {
        if (result) {
            List<NewsModel> list = dataSource.getNews();
            List<NewsModel> listResult = list
                    .stream()
                    .filter(c -> c.getAuthorId().equals(id))
                    .toList();
            list.removeAll(listResult);
        }
    }
}*/
