package fr.labs.zwing.randomuserapp.database.entity;

import java.util.List;

/**
 * Created by ludov on 13,juin,2018
 */
public class Result {

    private List<User> results;

    private Info info;

    public Result(List<User> results, Info info) {
        this.results = results;
        this.info = info;
    }

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
