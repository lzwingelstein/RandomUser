package fr.labs.zwing.randomuserapp.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import fr.labs.zwing.randomuserapp.view.activities.UserCollectionActivity;

/**
 * Created by ludov on 12,juin,2018
 */
@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract UserCollectionActivity contributeUserCollectionActivity();

}
