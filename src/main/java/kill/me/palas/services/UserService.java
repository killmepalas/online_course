package kill.me.palas.services;

import kill.me.palas.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
