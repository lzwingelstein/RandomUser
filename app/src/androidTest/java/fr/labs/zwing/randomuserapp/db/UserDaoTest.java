package fr.labs.zwing.randomuserapp.db;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import fr.labs.zwing.randomuserapp.database.entity.Location;
import fr.labs.zwing.randomuserapp.database.entity.Name;
import fr.labs.zwing.randomuserapp.database.entity.Picture;
import fr.labs.zwing.randomuserapp.database.entity.User;
import fr.labs.zwing.randomuserapp.utils.LiveDataTestUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by ludov on 12,juin,2018
 */
@RunWith(AndroidJUnit4.class)
public class UserDaoTest extends DbTest {

    @Test
    public void insertAndLoad() throws InterruptedException {
        final User user1 = new User(12, "male",
                new Name(
                        "Mr.",
                        "Frank",
                        "Marshall"),
                new Location(
                        "9 rue Saint-Maur",
                        "Paris",
                        "France",
                        75000),
                "frank.marshall@aol.fr",
                "0627275858",
                "0147858386",
                new Picture(
                        "https://randomuser.me/api/portraits/men/96.jpg",
                        "https://randomuser.me/api/portraits/med/men/96.jpg",
                        "https://randomuser.me/api/portraits/thumb/men/96.jpg"),
                "FR");

        final User user2 = new User(25, "female",
                new Name(
                        "Ms.",
                        "Laura",
                        "Dupont"),
                new Location(
                        "9 rue Saint-Maur",
                        "Paris",
                        "France",
                        75000),
                "frank.marshall@aol.fr",
                "0627275858",
                "0147858386",
                new Picture(
                        "https://randomuser.me/api/portraits/men/96.jpg",
                        "https://randomuser.me/api/portraits/med/men/96.jpg",
                        "https://randomuser.me/api/portraits/thumb/men/96.jpg"),
                "FR");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        //db.userDao().insertUser(user1);
        db.userDao().insertListOfUsers(users);

        //final User loaded1 = LiveDataTestUtil.getValue(db.userDao().getUserByUid(user1.getId()));
        //final User loaded2 = LiveDataTestUtil.getValue(db.userDao().getUserByUid(user2.getId()));
        final List<User> loadedList = LiveDataTestUtil.getValue(db.userDao().getAllUsers());
        //assertThat(loaded1.getName().getFirst(), is("Frank"));
        //assertThat(loaded2.getName().getFirst(), is("Laura"));
        assertThat(loadedList.toString(), is(users.toString()));
    }
}
