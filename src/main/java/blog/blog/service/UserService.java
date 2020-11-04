package blog.blog.service;

import blog.blog.model.User;
import blog.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) { return userRepository.findById(id); }

    public User findOne(Long id) {
        return userRepository.getOne(id);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByNome(String nome) {
        return userRepository.findByNome(nome);
    }
}
