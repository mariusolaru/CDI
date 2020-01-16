package beans;

import com.google.common.hash.Hashing;
import entity.User;
import interceptors.ValidPeriod;
import repository.UserRepository;
import service.AuthenticationService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static util.EntityConverter.toGuest;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private User user = new User();

    @EJB
    private UserRepository userRepository;

    @Inject
    private AuthenticationService authenticationService;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.login(user.getUsername(), user.getPassword());

        authenticationService.login(user);

        return "index?faces-redirect=true";
    }

    public String logout() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        request.logout();
        authenticationService.logout();

        return "login?faces-redirect=true";
    }

    @ValidPeriod
    public String register() {
        user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());

        //Only guests will be able to register using register page
        User newGuestUser = toGuest(user);
        userRepository.persist(newGuestUser);

        return "login?faces-redirect=true";
    }


}
