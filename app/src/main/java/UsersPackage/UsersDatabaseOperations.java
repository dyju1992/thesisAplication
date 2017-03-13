package UsersPackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dyju on 2017-01-07.
 */

public class UsersDatabaseOperations extends SQLiteOpenHelper{

    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE "+ UsersTableData.UsersTableInfo.TABLE_NAME+"("+ UsersTableData.UsersTableInfo.userName+ " TEXT, "+ UsersTableData.UsersTableInfo.password+" TEXT, "+ UsersTableData.UsersTableInfo.userAccountType+" TEXT)";

    public UsersDatabaseOperations(Context context) {
        super(context, UsersTableData.UsersTableInfo.USERS_TABLE_DATABASE_NAME, null, database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
