package util;

import entity.Admin;
import entity.Guest;
import entity.User;

public class EntityConverter {

    public static Guest toGuest(User user){
        Guest guest = new Guest();

        guest.setUsername(user.getUsername());
        guest.setPassword(user.getPassword());

        return guest;
    }

    public static Admin toAdmin(User user){
        Admin admin = new Admin();

        admin.setUsername(user.getUsername());
        admin.setPassword(user.getPassword());

        return admin;
    }
}
