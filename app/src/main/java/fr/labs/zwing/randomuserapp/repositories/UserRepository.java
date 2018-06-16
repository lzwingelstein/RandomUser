package fr.labs.zwing.randomuserapp.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import fr.labs.zwing.randomuserapp.App;
import fr.labs.zwing.randomuserapp.api.UserWebservice;
import fr.labs.zwing.randomuserapp.database.dao.UserDao;
import fr.labs.zwing.randomuserapp.database.entity.Result;
import fr.labs.zwing.randomuserapp.database.entity.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ludov on 12,juin,2018
 */
public class UserRepository {

    public final static String RESPONSE_RECEIVED = "response_received";
    public final static String STILL_WAITING = "still_waiting";

    private MutableLiveData<String> requestState;

    private final UserWebservice webservice;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(UserWebservice webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<String> getRequestState() {
        if (requestState == null) {
            requestState = new MutableLiveData<String>();
        }
        return requestState;
    }

    public void setRequestState(String currentState){
        if (requestState == null) {
            requestState = new MutableLiveData<String>();
        }
        requestState.setValue(currentState);
    }

    public LiveData<List<User>> getListOfUsers() {
        refreshListOfUsers();
        return userDao.getAllUsers();
    }

    private void refreshListOfUsers() {
        executor.execute(() -> {
            webservice.getResults().enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(App.context, "Server returned an error", Toast.LENGTH_LONG).show();
                    }else {
                        executor.execute(() -> {

                            userDao.nukeTable();
                            List<User> users = response.body().getResults();
                            if (users != null)
                                userDao.insertListOfUsers(users);

                        });
                        Toast.makeText(App.context, "Server returned data", Toast.LENGTH_LONG).show();
                        setRequestState(RESPONSE_RECEIVED);
                    }

                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {

                    if (t instanceof IOException) {
                        Log.i(getClass().getName(), "Network failure :(");
                        setRequestState(STILL_WAITING);
                        refreshListOfUsers();
                    }
                    else {
                        Toast.makeText(App.context, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        Log.e(getClass().getName(),"conversion issue! big problems :( ");
                    }
                }
            });

        });
    }



}
