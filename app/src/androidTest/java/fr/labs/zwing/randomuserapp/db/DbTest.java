package fr.labs.zwing.randomuserapp.db;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;

import fr.labs.zwing.randomuserapp.database.UserDatabase;

/**
 * Created by ludov on 12,juin,2018
 */
abstract public class DbTest {
    protected UserDatabase db;

    @Before
    public void initDb() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                UserDatabase.class).build();
    }

    @After
    public void closeDb() {
        db.close();
    }
}

