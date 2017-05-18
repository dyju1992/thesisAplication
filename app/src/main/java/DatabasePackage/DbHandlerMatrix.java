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

    public ArrayList<DHForUserDto> getDhForUser(User user){
        String query = "SELECT "+ MANIPULATOR_NAME + "," + ALPHA_ANGLES  + "," + THETA_ANGLES  + "," + A_DIMENSIONS  + "," + D_DIMENSIONS + " FROM "+ DH_TABLE + " WHERE " + USER_ID + " = \"" + user.getID() + "\"";
        return queryExecutor(query);
    }

    public ArrayList<DHForUserDto> getDhForManipulatorName(String name){
        String query = "SELECT "+ MANIPULATOR_NAME + "," + ALPHA_ANGLES  + "," + THETA_ANGLES  + "," + A_DIMENSIONS  + "," + D_DIMENSIONS + " FROM "+ DH_TABLE + " WHERE " + MANIPULATOR_NAME + " = \"" + name + "\"";
        return queryExecutor(query);
    }

    public Boolean datasArentBeOnDb(String manipulatorName, String userId){
        String query = "SELECT * FROM "+ DH_TABLE + " WHERE " + MANIPULATOR_NAME + " = \"" + manipulatorName + "\"" + " AND " + USER_ID + " = \"" + userId + "\"";
        return !(queryExecutor(query).size() >0);
    }

    public void deleteManipulatorFromDb(String manipulatorName, String userId){
        String query = "DELETE FROM " + DH_TABLE + " WHERE " + MANIPULATOR_NAME + " = \"" + manipulatorName + "\"" + " AND " + USER_ID + " = \"" + userId + "\"";
        queryExecutor(query);
    }

    public void deleteRowByName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DH_TABLE, USER_ID+"=? and "+MANIPULATOR_NAME+" =?", new String[]{"-1", name});
        db.close();

    }

    private ArrayList<DHForUserDto> queryExecutor(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 0;
        if(i>2) {
            System.out.println("Zapętlenie, nie można utworzyć tabali dh na bazie");
            return new ArrayList<>();
        }

        try {
            Cursor cursor = db.rawQuery(query, null);
            ArrayList<DHForUserDto> dhDatasList = new ArrayList<>();

            while(cursor.moveToNext()){
                DHForUserDto dhForUserDto = new DHForUserDto();
                dhForUserDto.setUserid(-1);
                DhDatas dhDatas = new DhDatas();
                dhDatas.setAlpha(cursor.getString(1));
                dhDatas.setTheta(cursor.getString(2));
                dhDatas.setD(cursor.getString(4));
                dhDatas.setA(cursor.getString(3));
                dhDatas.setManipulatorName(cursor.getString(0));
                dhForUserDto.setDhDatas(dhDatas);
//                dhDatas.setAlpha(cursor.getString(1));
//                dhDatas.setTheta(cursor.getString(2));
//                dhDatas.setA(cursor.getString(3));
//                dhDatas.setD(cursor.getString(4));
                dhDatasList.add(dhForUserDto);

            }

            cursor.close();
            db.close();
            return dhDatasList;
        }catch (Exception e){
            i++;
            onCreate(db);
            return queryExecutor(query);
        }

    }

    public void insertManipulatorMockDataToDb(String manipulatorName){
        DHForUserDto dhForUserDto = getDatasForManipulatorName(manipulatorName);
        addNewDataAboutmanipulator(dhForUserDto);
    }
    private DHForUserDto getDatasForManipulatorName(String name){
        DHForUserDto dhForUserDto = new DHForUserDto();
        dhForUserDto.setUserid(-1);
        DhDatas dhDatas = new DhDatas();
        switch (name){
            case "first_manipulator":
                dhDatas.setAlpha("0,0,0,90,90");
                dhDatas.setA("0,l1,l2,0,l4");
                dhDatas.setD("0,0,0,d4(t),0");
                dhDatas.setTheta("theta1(t),theta2(t),theta3(t),180,theta5(t)");
                dhDatas.setManipulatorName(name);
                break;

            case "Manipulator_1":
                dhDatas.setAlpha("90,90,90");
                dhDatas.setTheta("theta1(t),theta2(t),0");
                dhDatas.setA("0,0,l3");
                dhDatas.setD("0,lambda2,lambda3(t)");
                dhDatas.setManipulatorName(name);
                break;

            case "default":
                dhDatas.setA("1");
                dhDatas.setAlpha("1");
                dhDatas.setD("1");
                dhDatas.setTheta("1");
                dhDatas.setManipulatorName("default");
        }
        dhForUserDto.setDhDatas(dhDatas);

        return dhForUserDto;
    }

//    private String getQueryForManipulatorName(String name){
//        switch (name){
//            case "first_manipulator":
//                return getQueryForFirstmani
//        }
//    }

}
