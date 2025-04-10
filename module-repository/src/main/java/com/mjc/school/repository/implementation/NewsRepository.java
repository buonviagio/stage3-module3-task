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
import javax.transaction.Transactional;

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

    @Transactional
    @Override
    public List<NewsModel> readAll() {
        System.out.println("Method readAll() from NEWS REPOSITORY CLASS");
        TypedQuery<NewsModel> query = entityManager.createQuery("SELECT n FROM NewsModel n", NewsModel.class);
        List<NewsModel> result = query.getResultList();
        return result;
    }

    //@Transactional
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> cq = cb.createQuery(NewsModel.class);
        Root<NewsModel> root = cq.from(NewsModel.class);
        List<Predicate> predicates = new ArrayList<>();

        if (tagName != null && !tagName.isEmpty()) {
            Join<NewsModel, TagModel> tagJoin = root.join("tags");
            predicates.add(cb.equal(tagJoin.get("name"), tagName));
        }

        if (tagId != null) {
            Join<NewsModel, TagModel> tagJoin = root.join("tags");
            predicates.add(cb.equal(tagJoin.get("id"), tagId));
        }

        if (authorName != null && !authorName.isEmpty()) {
            Join<NewsModel, AuthorModel> authorJoin = root.join("author");
            predicates.add(cb.equal(authorJoin.get("name"), authorName));
        }

        if (title != null && !title.isEmpty()) {
            predicates.add(cb.like(root.get("title"), "%" + title + "%"));
        }

        if (content != null && !content.isEmpty()) {
            predicates.add(cb.like(root.get("content"), "%" + content + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }

    public List<NewsModel> findByTagId(Long tagId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> cq = cb.createQuery(NewsModel.class);
        Root<NewsModel> root = cq.from(NewsModel.class);
        Join<NewsModel, TagModel> tagJoin = root.join("tags");
        cq.where(cb.equal(tagJoin.get("id"), tagId));
        return entityManager.createQuery(cq).getResultList();
    }
}
