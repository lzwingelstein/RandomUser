package fr.labs.zwing.randomuserapp.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import fr.labs.zwing.randomuserapp.view.fragments.UserCollectionFragment;

/**
 * Created by ludov on 12,juin,2018
 */
@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract UserCollectionFragment contributeUserCollectionFragment();

}
