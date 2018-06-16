package fr.labs.zwing.randomuserapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.labs.zwing.randomuserapp.database.entity.User;

/**
 * Created by Ludovic ZWINGELSTEIN on 12,juin,2018
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM User ORDER BY namefirst")
    LiveData<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListOfUsers(List<User> users);

    @Query("DELETE FROM User")
     void nukeTable();


}
