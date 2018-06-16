package fr.labs.zwing.randomuserapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import fr.labs.zwing.randomuserapp.di.component.DaggerAppComponent;

/**
 * Created by ludov on 12,juin,2018
 */

public class App extends Application implements HasActivityInjector {

    private static App instance;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static Context context;

    @Override
    public void onCreate() {

        instance = this;

        super.onCreate();
        this.initDagger();
        context = getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    private void initDagger(){
        DaggerAppComponent.builder().application(this).build().inject(this);
    }


    public static synchronized App getInstance() {
        return instance;
    }


}
