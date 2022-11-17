package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        this.userDAO.add(new User("Guseppe", "Danise", 55));
        this.userDAO.add(new User("Lowernce", "Tibbet", 30));
        this.userDAO.add(new User("Joseph", "Shore", 35));
    }

    @Override
    @Transactional
    public void add(User user) {
        this.userDAO.add(user);
    }

    @Override
    @Transactional
    public void remove(int id) {
        this.userDAO.remove(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

}
