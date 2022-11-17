package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    public void add(User user);
    public void remove(int id);
    public User getUserById(int id);
    public List<User> getAllUsers();
}
