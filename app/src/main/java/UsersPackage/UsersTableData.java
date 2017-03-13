package UsersPackage;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by dyju on 2017-01-07.
 */
public class UsersTableData {

    public UsersTableData() {
    }

    public static abstract class UsersTableInfo implements BaseColumns{
        public static final String userName = "user_name";
        public static final String password = "password";
        public static final String USERS_TABLE_DATABASE_NAME = "user_info";
        public static final String userAccountType = "account_type";
        public static final String TABLE_NAME = "users";
     }

}
