package Services;

import UsersPackage.User;


public interface ILoginActivityService {

    User showUser(String name);

    boolean checkIfPasswordIsValid(String passwordFromDb, String password);

}
