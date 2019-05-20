package br.com.eduardo.users.api;

import br.com.eduardo.users.model.User;
import br.com.eduardo.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<User> create(@RequestBody final User user){
        return service.create(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private Mono<User> update(@RequestBody final User user){
        return service.update(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private Flux<User> list(){
        return service.list();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<User> findById(@PathVariable("userId") final String userId){
        return service.findById(userId);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    private Flux<User> findByParameters(@RequestBody final User user){
        return service.findByParameter(user);
    }

    @DeleteMapping("/{userId}")
    private Mono<Void> delete(@PathVariable("userId") final String userId){
        return service.delete(userId);
    }
}
