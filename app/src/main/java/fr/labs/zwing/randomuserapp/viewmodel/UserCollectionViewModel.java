package fr.labs.zwing.randomuserapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import fr.labs.zwing.randomuserapp.database.entity.User;
import fr.labs.zwing.randomuserapp.repositories.UserRepository;

/**
 * Created by ludov on 12,juin,2018
 */
public class UserCollectionViewModel extends ViewModel {

    private LiveData<List<User>> users;
    private LiveData<String> requestState;
    private UserRepository userRepository;

    @Inject
    public UserCollectionViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init() {
        if (this.users != null) {
            return;
        }
        users = userRepository.getListOfUsers();
        requestState = userRepository.getRequestState();
    }

    public LiveData<List<User>> getListOfUsers() {
        return this.users;
    }

    public LiveData<String> getRequestState() {
        return this.requestState;
    }

}
