package fr.labs.zwing.randomuserapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fr.labs.zwing.randomuserapp.database.dao.UserDao;
import fr.labs.zwing.randomuserapp.database.entity.User;

/**
 * Created by Ludovic ZWINGELSTEIN on 12,juin,2018
 */
@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static volatile UserDatabase INSTANCE;

    public abstract UserDao userDao();

}
