package ftmk.bitp3453.Helloclass;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Vector;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<Student> students;

    public StudentAdapter(LayoutInflater layoutInflater, List<Student> students){
        this.layoutInflater = layoutInflater;
        this.students = students;
    }

    public Student getStudentAt(int postion){
        return students.get(postion);
    }


    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new StudentViewHolder(layoutInflater.inflate(R.layout.item_student,parent,false));
    }

    public void onBindViewHolder(StudentViewHolder holder, int position){
        holder.setStudent(students.get(position));
    }

    public int getItemCount(){
        return students.size();
    }
}
