package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;

public interface UserService {

   User findByUsername();
   boolean loginUser();
   void logoutUser();

}
