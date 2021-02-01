package com.tenz.tenzmusic.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.entity.PlaySongBean;

@Database(entities = {PlaySongBean.class}, version = 1, exportSchema = false)
public abstract class DBManager extends RoomDatabase {

    public abstract PlaySongDao playSongDao();

    private static DBManager dbManager;
    public static DBManager newInstance() {
        if (dbManager == null) {
            synchronized (DBManager.class) {
                if (dbManager == null) {
                    dbManager = Room.databaseBuilder(App.getContext(),DBManager.class,"tenz_music")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return dbManager;
    }

}
