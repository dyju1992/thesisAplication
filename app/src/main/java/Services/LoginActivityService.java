package Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.dyju.thesisapplication.R;

import DatabasePackage.DbHandler;
import UsersPackage.User;


public class LoginActivityService extends AppCompatActivity implements ILoginActivityService  {

    public LoginActivityService() {
    }

    @Override
    public User showUser(String name){
        DbHandler dbHandler = new DbHandler(this, null, null, 1);
        return dbHandler.findUser(name);

    }

    @Override
    public boolean checkIfPasswordIsValid(String passwordFromDb, String password) {
        return false;
    }



}
