package com.example.realm.app;

import android.app.Application;
import com.example.realm.modelos.Board;
import com.example.realm.modelos.Note;
import java.util.concurrent.atomic.AtomicInteger;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class Myapplication extends Application {
    public static AtomicInteger boardid = new AtomicInteger();
    public static AtomicInteger noteid = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm realm = Realm.getDefaultInstance();
        boardid = getidtable(realm,Board.class);
        noteid = getidtable(realm,Note.class);
        realm.close();
    }
    public void ConfiguracionRealm(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("default-realm")
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .compactOnLaunch()
                .inMemory()
                .build();
    // set this config as the default realm
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getidtable(Realm real, Class<T> anyclass) {
        RealmResults<T> resultados = real.where(anyclass).findAll();
        return (resultados.size()>0)? new AtomicInteger(resultados.max("id").intValue()) :new AtomicInteger();
    }
}
