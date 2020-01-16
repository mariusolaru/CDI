package repository;

import entity.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserRepository extends DataRepository<User, Long> {

    public UserRepository(){
        super(User.class);
    }

    public User getUserByUsername(String userName){
        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u where u.username = ?1", User.class);
        query.setParameter(1, userName);

        return query.getSingleResult();
    }

}
