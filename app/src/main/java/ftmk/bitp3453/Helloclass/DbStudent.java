package ftmk.bitp3453.Helloclass;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



    public class DbStudent extends SQLiteOpenHelper {

        public static final String dbname = "dbstudent";
        public static final String tblNameExpense = "students";
        public static final String colFullName = "fullname";
        public static final String colStudNum = "studNo";
        public static final String colEmail = "email";
        public static final String colBirth = "birth";
        public static final String colGender = "gender";
        public static final String colState = "state";


        public static final String strCrtTableExpenses = "CREATE TABLE IF NOT EXISTS " + tblNameExpense
                + " ( " + colFullName + " TEXT, " + colStudNum
                + " INTEGER PRIMARY KEY, " + colEmail + " TEXT, " + colBirth + " DATE, " + colGender + " TEXT,"
                + colState + " TEXT) ";

        public static final String strDropTableExpenses = "DROP TABLE IF EXISTS " + tblNameExpense;

        public DbStudent(Context context) {
            super(context, dbname, null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(strCrtTableExpenses);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(strDropTableExpenses);
            onCreate(db);

        }


        public float fnInsertExpense(Student student) {
            float retResult = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(colFullName, student.getStrFullname());
            values.put(colStudNum, student.getStrStudNo());
            values.put(colEmail, student.getStrEmail());
            values.put(colBirth, student.getStrBirthdate());
            values.put(colGender, student.getStrGender());
            values.put(colState, student.getStrState());

            retResult = db.insert(tblNameExpense, null, values);
            return retResult;
        }

        @SuppressLint("Range")
        public Student fnGetExpenses(int intExpId) {
            Student student = new Student();

            String strSelQuery = "Select * from " + tblNameExpense + "where " + colStudNum
                    + "= " + intExpId;
            Cursor cursor = this.getReadableDatabase().rawQuery(strSelQuery, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            student.setStrFullname(cursor.getString(cursor.getColumnIndex(colFullName)));
            student.setStrStudNo(cursor.getString(cursor.getColumnIndex(colStudNum)));
            student.setStrEmail(cursor.getString(cursor.getColumnIndex(colEmail)));
            student.setStrBirthdate(cursor.getString(cursor.getColumnIndex(colBirth)));
            student.setStrState(cursor.getString(cursor.getColumnIndex(colState)));

            return student;
        }


        @SuppressLint("Range")
        public List<Student> fnGetAllExpenses() {

            List<Student> listExp = new ArrayList<Student>();

            String strSelAll = "Select * from " + tblNameExpense;

            Cursor cursor = this.getReadableDatabase().rawQuery(strSelAll, null);
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();

                    student.setStrFullname(cursor.getString(cursor.getColumnIndex(colFullName)));
                    student.setStrStudNo(cursor.getString(cursor.getColumnIndex(colStudNum)));
                    student.setStrEmail(cursor.getString(cursor.getColumnIndex(colEmail)));
                    student.setStrBirthdate(cursor.getString(cursor.getColumnIndex(colBirth)));
                    student.setStrState(cursor.getString(cursor.getColumnIndex(colState)));

                    listExp.add(student);

                } while (cursor.moveToNext());
            }

            return listExp;
        }

        public int fnUpdateExpenses(Student student) {
            int retResult = 0;

            ContentValues values = new ContentValues();
            values.put(colFullName, student.getStrFullname());
            values.put(colStudNum, student.getStrStudNo());
            values.put(colEmail, student.getStrEmail());
            values.put(colBirth, student.getStrBirthdate());
            values.put(colGender, student.getStrGender());
            values.put(colState, student.getStrState());

            String[] argg = {String.valueOf(student.getStrId())};

            this.getWritableDatabase().update(tblNameExpense, values, colStudNum + " = ?", argg);
            return retResult;
        }
    }

