package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @PersistenceContext
   EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.persist(user);
   }

   @Override
   public User getById(Long id) {
      return entityManager.find(User.class, id);
   }

   @Override
   public List<User> listUsers() {
      return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
   }

   @Override
   public void delete(Long id) {
      User deleting = entityManager.find(User.class, id);
      entityManager.remove(deleting);
   }

   @Override
   public void update(User user) {
      entityManager.merge(user);
   }

}
