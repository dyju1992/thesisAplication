package UsersPackage;

import java.io.Serializable;

/**
 * Created by dyju on 2017-02-21.
 */
public class User implements Serializable {

    private int _id;
    private String _name;
    private String _password;
    private AccountVersion accountVersion;

    public User() {
    }

    public User(int _id, String _name, String _password, AccountVersion accountVersion) {
        this._id = _id;
        this._name = _name;
        this._password = _password;
        this.accountVersion = accountVersion;
    }

    public User(int intId, String name, String password) {
        this._id = intId;
        this._name = name;
        this._password = password;
    }

    public User(String _name, String _password, AccountVersion accountVersion) {
        this._name = _name;
        this._password = _password;
        this.accountVersion = accountVersion;
    }

    public AccountVersion getAccountVersion() {
        return accountVersion;
    }

    public void setAccountVersion(AccountVersion accountVersion) {
        this.accountVersion = accountVersion;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String password) {
        this._password = password;
    }
}
