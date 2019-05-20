package br.com.eduardo.users.service;

import br.com.eduardo.users.model.User;
import br.com.eduardo.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> create(final User user) {
        return userRepository.save(user);
    }


    public Mono<User> update(final User user) {
        return userRepository.save(user);
    }

    public Flux<User> list() {
        return userRepository.findAll();
    }

    public Mono<Void> delete(String userId) {
        return userRepository.deleteById(userId);
    }

    public Mono<User> findById(String userId) {
        return userRepository.findById(userId);
    }
}
