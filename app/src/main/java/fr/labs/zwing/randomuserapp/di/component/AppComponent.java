package fr.labs.zwing.randomuserapp.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import fr.labs.zwing.randomuserapp.App;
import fr.labs.zwing.randomuserapp.di.module.ActivityModule;
import fr.labs.zwing.randomuserapp.di.module.AppModule;
import fr.labs.zwing.randomuserapp.di.module.FragmentModule;

/**
 * Created by ludov on 12,juin,2018
 */
@Singleton
@Component(modules={ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
