/**
 * 개인 프로젝트 - AppDatabase.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
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
