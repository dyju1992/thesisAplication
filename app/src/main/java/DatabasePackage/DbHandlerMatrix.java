package DatabasePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dyju.thesisapplication.DHForUserDto;
import com.example.dyju.thesisapplication.DhDatas;

import java.util.ArrayList;
import java.util.List;

import UsersPackage.User;


public class DbHandlerMatrix extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "thesisDB.db";
    private static final String DH_TABLE = "dh";
    private static final String ID = "int_id";

    private static final String USER_ID = "_userId";
    private static final String ALPHA_ANGLES = "_alpha";
    private static final String THETA_ANGLES = "_theta";
    private static final String D_DIMENSIONS = "_d";
    private static final String A_DIMENSIONS = "_a";
    private static final String MANIPULATOR_NAME = "_name";

    public Context context;

    public DbHandlerMatrix(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DH_TABLE = "CREATE TABLE "+DH_TABLE+"("+ ID + " INTEGER PRIMARY KEY," +
                USER_ID + " INTEGER," + ALPHA_ANGLES + " TEXT," + THETA_ANGLES + " TEXT," +
                D_DIMENSIONS + " TEXT, " + A_DIMENSIONS + " TEXT, " + MANIPULATOR_NAME+" TEXT)";

        db.execSQL(CREATE_DH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DH_TABLE);
        onCreate(db);
    }

    public void addNewDataAboutmanipulator(DHForUserDto dhForUserDto){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALPHA_ANGLES, dhForUserDto.getDhDatas().getAlpha());
        contentValues.put(THETA_ANGLES, dhForUserDto.getDhDatas().getTheta());
        contentValues.put(A_DIMENSIONS, dhForUserDto.getDhDatas().getA());
        contentValues.put(D_DIMENSIONS, dhForUserDto.getDhDatas().getD());
        contentValues.put(MANIPULATOR_NAME, dhForUserDto.getDhDatas().getManipulatorName());
        contentValues.put(USER_ID, dhForUserDto.getUserid());

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(DH_TABLE, null, contentValues);
        }catch (Exception e){
            onCreate(db);
            db.insert(DH_TABLE, null, contentValues);
        }

        db.close();
    }

    public ArrayList<DhDatas> getDhForUser(User user){
        String query = "SELECT "+ MANIPULATOR_NAME + "," + ALPHA_ANGLES  + "," + THETA_ANGLES  + "," + A_DIMENSIONS  + "," + D_DIMENSIONS + " FROM "+ DH_TABLE + " WHERE " + USER_ID + " = \"" + user.getID() + "\"";
        return queryExecutor(query);
    }

    public Boolean datasArentBeOnDb(DbHandlerMatrix dbHandlerMatrix, String manipulatorName){
        String query = "SELECT * FROM "+ DH_TABLE + " WHERE " + MANIPULATOR_NAME + " = \"" + manipulatorName + "\"";
        return queryExecutor(query).size()>0;
    }

    private ArrayList<DhDatas> queryExecutor(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<DhDatas> dhDatasList = new ArrayList<>();

        while(cursor.moveToNext()){
            DhDatas dhDatas = new DhDatas();
            dhDatas.setManipulatorName(cursor.getString(0));
            dhDatas.setAlpha(cursor.getString(1));
            dhDatas.setTheta(cursor.getString(2));
            dhDatas.setA(cursor.getString(3));
            dhDatas.setD(cursor.getString(4));
            dhDatasList.add(dhDatas);

        }

        cursor.close();
        db.close();
        return dhDatasList;
    }

//    private String getQueryForManipulatorName(String name){
//        switch (name){
//            case "first_manipulator":
//                return getQueryForFirstmani
//        }
//    }

}
