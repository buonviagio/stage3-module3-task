package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @PersistenceContext
    private EntityManager entityManager;


    private BaseRepository<AuthorModel, Long> authorRepository;
    @Autowired
    public NewsRepository(BaseRepository<AuthorModel, Long> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<NewsModel> readAll() {
        System.out.println("Method readAll() from NEWS REPOSITORY CLASS");
        TypedQuery<NewsModel> query = entityManager.createQuery("SELECT n FROM NewsModel n", NewsModel.class);
        List<NewsModel> result = query.getResultList();
        return result;
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        System.out.println("ID -> " + id);
        TypedQuery<NewsModel> typedQuery = entityManager.createQuery(
                "select c from NewsModel c where c.id like :id", NewsModel.class);
        typedQuery.setParameter("id", id);
        return Optional.ofNullable(typedQuery.getSingleResult());
        //return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
       /* System.out.println("CREATE NEWS " + entity);
        Optional<AuthorModel> authorModel = authorRepository.readById(1L);
        entity.setAuthor(authorModel.get());*/
        return entityManager.merge(entity);
        //entityManager.persist(entity);
        //return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        return entityManager.merge(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        NewsModel news = entityManager.find(NewsModel.class, id);
        if (news != null) {
            entityManager.remove(news);
            return true;
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager.find(NewsModel.class, id) != null;
    }

    public List<NewsModel> findByCriteria(String tagName, Long tagId, String authorName, String title, String content) {
        /* This is like Fabric, in order to create parts of query (conditions, functions)*/
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // This is query -> SELECT * FROM NewsTable
        CriteriaQuery<NewsModel> cq = cb.createQuery(NewsModel.class);
        // Root is equivalent SQL -> FROM news AS n. Here we can obtain access to fields of NewsModel n.get("title)...
        // Throw Root I have access to fields (attributes) of entity NesModel
        Root<NewsModel> root = cq.from(NewsModel.class);

        // We create a list of conditionals. In our case Predicate like WHERE
        List<Predicate> predicates = new ArrayList<>();

        if (tagName != null && !tagName.isEmpty()) {
            System.out.println("Tag Name Section");
            Join<NewsModel, TagModel> tagJoin = root.join("tags");
            predicates.add(cb.equal(tagJoin.get("name"), tagName));
        }

        if (tagId != null) {
            System.out.println("Tag ID Section");
            Join<NewsModel, TagModel> tagJoin = root.join("tags");
            predicates.add(cb.equal(tagJoin.get("id"), tagId));
        }

        if (authorName != null && !authorName.isEmpty()) {
            System.out.println("authorName Section");
            Join<NewsModel, AuthorModel> authorJoin = root.join("author");
            predicates.add(cb.equal(authorJoin.get("name"), authorName));
        }

        if (title != null && !title.isEmpty()) {
            System.out.println("Tittle Section");
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }

        if (content != null && !content.isEmpty()) {
            System.out.println("Content Section");
            //predicates.add(cb.like(root.get("content"), "%" + content + "%"));
            predicates.add(cb.like(cb.lower(root.get("content")), "%" + content.toLowerCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        System.out.println("Repository method");
        return entityManager.createQuery(cq).getResultList();
    }

 /*   public List<NewsModel> findByTagId(Long tagId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> cq = cb.createQuery(NewsModel.class);
        Root<NewsModel> root = cq.from(NewsModel.class);
        Join<NewsModel, TagModel> tagJoin = root.join("tags");
        cq.where(cb.equal(tagJoin.get("id"), tagId));
        return entityManager.createQuery(cq).getResultList();
    }*/
}
