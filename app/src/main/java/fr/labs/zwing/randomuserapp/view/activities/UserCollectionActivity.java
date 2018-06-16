package fr.labs.zwing.randomuserapp.view.activities;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import fr.labs.zwing.randomuserapp.R;
import fr.labs.zwing.randomuserapp.util.BaseActivity;
import fr.labs.zwing.randomuserapp.view.fragments.UserCollectionFragment;

public class UserCollectionActivity extends BaseActivity {

    private static final String LIST_FRAG = "LIST_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usercollection_activity);


        FragmentManager manager = getSupportFragmentManager();

        UserCollectionFragment fragment = (UserCollectionFragment) manager.findFragmentByTag(LIST_FRAG);

        if (fragment == null) {
            fragment = UserCollectionFragment.newInstance();
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_list,
                LIST_FRAG
        );


    }

}
