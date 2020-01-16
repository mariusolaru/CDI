package repository;

import entity.Admin;

import javax.ejb.Stateless;

@Stateless
public class AdminRepository extends DataRepository<Admin, Long> {

    public AdminRepository(){
        super(Admin.class);
    }
}
