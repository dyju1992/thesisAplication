package DatabasePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PublicKey;
import java.util.ArrayList;

import UsersPackage.AccountVersion;
import UsersPackage.User;

/**
 * Created by dyju on 2017-02-21.
 */
public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "thesisDB.db";
    private static final String USER_TABLE = "users";

    public static final String USER_ID = "_id";
    public static final String USER_NAME = "_name";
    public static final String USER_PASSWORD = "_password";
    public static final String ACCOUNT_VERSION = "_version";

    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" + USER_ID +
                " INTEGER PRIMARY KEY," + USER_NAME + " TEXT," + USER_PASSWORD + " TEXT,"+
                 ACCOUNT_VERSION +" TEXT)";
        db.execSQL(CREATE_USER_TABLE);

    }

    public void addVersionColumn(){
        String ADD_COLUMN = "ALTER TABLE "+USER_TABLE+" ADD COLUMN "+ACCOUNT_VERSION+" TEXT";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(ADD_COLUMN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);

    }

    public void addNewUserToDb(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.get_name());
        contentValues.put(USER_PASSWORD, user.get_password());
        contentValues.put(ACCOUNT_VERSION, user.getAccountVersion().getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(USER_TABLE, null, contentValues);
        db.close();

    }

    public ArrayList<User> getAllUsersFromDb(){
        String query = "SELECT * FROM "+ USER_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<User> usersList = new ArrayList<User>();
        while (cursor.moveToNext()){
            User user = new User();
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.set_name(cursor.getString(1));
            user.set_password(cursor.getString(2));
            user.setAccountVersion(cursor.getString(3).toUpperCase()!="PRO"?AccountVersion.PRO : AccountVersion.EDUCATION);
            usersList.add(user);
        }

        cursor.close();
        db.close();
        return usersList;

    }
    public User findUser(String name){
        String query = "SELECT * FROM "+ USER_TABLE + " WHERE " + USER_NAME + "=  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = new User();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.set_name(cursor.getString(1));
            user.set_password(cursor.getString(2));
            user.setAccountVersion(cursor.getString(3).toUpperCase().equals("PRO")?AccountVersion.PRO : AccountVersion.EDUCATION);
            cursor.close();
        } else {
            user = null;
        }
        db.close();
        return user;
    }
}
