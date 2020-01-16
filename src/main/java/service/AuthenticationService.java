package service;

import entity.User;
import repository.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthenticationService implements Serializable {

    @EJB
    private UserRepository userRepository;

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void login(User user){
        this.currentUser = userRepository.getUserByUsername(user.getUsername());
    }

    public void logout(){
        this.currentUser = null;
    }
}
