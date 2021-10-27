package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;
   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Override
   public User getById(Long id) {
      return userDao.getById(id);
   }

   @Override
   @Transactional(readOnly = true)
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public void delete(Long id) {
      userDao.delete(id);
   }

   @Override
   public void update(User user) {
      userDao.update(user);
   }


}
