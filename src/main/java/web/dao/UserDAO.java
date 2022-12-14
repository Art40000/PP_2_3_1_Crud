package web.dao;

import web.model.User;
import java.util.List;

public interface UserDAO {
    public void add(User user);
    public void remove(int id);
    public void update(User fromUser, User toUser);
    public User getUserById(int id);
    public List<User> getAllUsers();
    public boolean doesThisUserExist(User user);
}
