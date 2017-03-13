package UsersPackage;

/**
 * Created by dyju on 2017-02-26.
 */
public enum AccountVersion {

    EDUCATION("Education"),
    PRO("PRO");

    private final String name;

    AccountVersion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


