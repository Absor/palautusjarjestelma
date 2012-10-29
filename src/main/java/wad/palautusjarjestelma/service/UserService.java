package wad.palautusjarjestelma.service;

import wad.palautusjarjestelma.data.User;

public interface UserService extends ServiceInterface<User> {

    User findByUsername(String username);
}
