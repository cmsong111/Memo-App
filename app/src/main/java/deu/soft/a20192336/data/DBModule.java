/**
 * 개인 프로젝트 - DBModule.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
package deu.soft.a20192336.data;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import deu.soft.a20192336.BaseApplication;

@InstallIn(SingletonComponent.class)
@Module
public class DBModule {

    @Singleton
    @Provides
    public static AppDatabase provideAppDatabase() {
        return Room.databaseBuilder(
                        BaseApplication.getContext(),
                        AppDatabase.class,
                        "prj20192336.db")
                .fallbackToDestructiveMigration().build();
    }

    @Singleton
    @Provides
    public static MemoDao provideMemoDao(AppDatabase appDatabase) {
        return appDatabase.memoDao();
    }
}
