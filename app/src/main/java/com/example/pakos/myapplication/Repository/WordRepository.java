package com.example.pakos.myapplication.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.pakos.myapplication.DB.WordDao;
import com.example.pakos.myapplication.DB.WordRoomDatabase;
import com.example.pakos.myapplication.POJO.Word;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase mydb = WordRoomDatabase.getINSTANCE(application);
        mWordDao = mydb.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        public insertAsyncTask(WordDao mWordDao) {
            mAsyncTaskDao = mWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }
}
