package deu.soft.a20192336.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Memo.class},
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemoDao memoDao();
}
