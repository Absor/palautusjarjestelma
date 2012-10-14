package wad.palautusjarjestelma.service;

import java.util.List;
import wad.palautusjarjestelma.data.User;

public interface UserService {

    User add(User user);

    List<User> list();

    User getById(Long id);

    void deleteById(Long id);
}
