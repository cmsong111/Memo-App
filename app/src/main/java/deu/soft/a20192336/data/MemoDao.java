package deu.soft.a20192336.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemoDao {

    /**
     * 메모를 추가하는 메소드
     *
     * @param title     제목
     * @param content   내용
     * @param createdAt 생성 시간
     * @param updatedAt 수정 시간
     * @return 추가된 메모
     */
    @Query("INSERT INTO memo20192336 (title, content, created_at, updated_at) VALUES (:title, :content, :createdAt, :updatedAt)")
    void save(String title, String content, String createdAt, String updatedAt);

    /**
     * 메모를 수정하는 메소드
     *
     * @param id        메모의 id
     * @param title     메모의 제목
     * @param content   메모의 내용
     * @param updatedAt 메모의 수정 시간
     * @return 수정된 메모
     */
    @Query("UPDATE memo20192336 SET title = :title, content = :content, updated_at = :updatedAt WHERE id = :id")
    void save(int id, String title, String content, String updatedAt);

    /**
     * 모든 메모를 가져오는 메소드
     *
     * @return 모든 메모
     */
    @Query("SELECT * FROM memo20192336")
    List<Memo> getAll();

    /**
     * ID로 메모를 가져오는 메소드
     *
     * @param id 메모의 id
     * @return 메모
     */
    @Query("SELECT * FROM memo20192336 WHERE id = :id")
    Memo findById(int id);

    /**
     * 제목 또는 내용에 특정 문자열이 포함된 메모를 가져오는 메소드
     *
     * @param title   제목
     * @param content 내용
     * @return 메모
     */
    @Query("SELECT * FROM memo20192336 WHERE title LIKE :title OR content LIKE :content")
    List<Memo> findByTitleContainingOrContentContaining(String title, String content);


    /**
     * 메모를 삭제하는 메소드
     *
     * @param id 메모의 id
     */
    @Query("DELETE FROM memo20192336 WHERE id = :id")
    void delete(int id);
}
