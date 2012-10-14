
package wad.palautusjarjestelma.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.palautusjarjestelma.data.User;
import wad.palautusjarjestelma.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=false)
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly=true)
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly=false)
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

}
