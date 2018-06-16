package fr.labs.zwing.randomuserapp.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import fr.labs.zwing.randomuserapp.di.key.ViewModelKey;
import fr.labs.zwing.randomuserapp.viewmodel.FactoryViewModel;
import fr.labs.zwing.randomuserapp.viewmodel.UserCollectionViewModel;

/**
 * Created by ludov on 12,juin,2018
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserCollectionViewModel.class)
    abstract ViewModel bindUserCollectionViewModel(UserCollectionViewModel repoViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);

}
