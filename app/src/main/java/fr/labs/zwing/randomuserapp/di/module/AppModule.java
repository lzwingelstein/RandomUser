package fr.labs.zwing.randomuserapp.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.labs.zwing.randomuserapp.api.UrlManager;
import fr.labs.zwing.randomuserapp.api.UserWebservice;
import fr.labs.zwing.randomuserapp.database.UserDatabase;
import fr.labs.zwing.randomuserapp.database.dao.UserDao;
import fr.labs.zwing.randomuserapp.repositories.UserRepository;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ludov on 12,juin,2018
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    UserDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                UserDatabase.class, "UserDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(UserDatabase database) { return database.userDao(); }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserWebservice webservice, UserDao userDao, Executor executor) {
        return new UserRepository(webservice, userDao, executor);
    }

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(UrlManager.API_HOST)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    UserWebservice provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(UserWebservice.class);
    }



}
