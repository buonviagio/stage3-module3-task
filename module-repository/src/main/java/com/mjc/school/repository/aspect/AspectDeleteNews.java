package com.mjc.school.repository.aspect;

import com.mjc.school.repository.model.NewsModel;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Aspect
@Component
public class AspectDeleteNews {

    @PersistenceContext
    private EntityManager entityManager;

    @AfterReturning(value = "@annotation(com.mjc.school.repository.annotations.DeleteNews) " +
            "&& args(id)", returning = "result")
    public void afterAuthorDelete(Long id, boolean result) {
        if (result) {
            System.out.println(" -> ASPECT IN ACTION ... ");
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();

            CriteriaDelete<NewsModel> delete = cb.createCriteriaDelete(NewsModel.class);

            Root<NewsModel> root = delete.from(NewsModel.class);

            delete.where(cb.equal(root.get("author").get("id"), id));

            entityManager.createQuery(delete).executeUpdate();
        }
    }
}
