package ftmk.bitp3453.Helloclass;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private final TextView lblFullName, lblStudNo, lblGender, lblBirthdate, lblEmail, lblState;


    public StudentViewHolder(View itemView) {
        super(itemView);
        this.lblFullName = itemView.findViewById(R.id.lblFullName);
        this.lblStudNo = itemView.findViewById(R.id.lblStudNo);
        this.lblGender = itemView.findViewById(R.id.lblGender);
        this.lblBirthdate = itemView.findViewById(R.id.lblBirthdate);
        this.lblEmail = itemView.findViewById(R.id.lblEmail);
        this.lblState = itemView.findViewById(R.id.lblState);
    }

    public void setStudent(Student student){
        lblFullName.setText(student.getStrFullname());
        lblStudNo.setText(student.getStrStudNo());
        lblEmail.setText(student.getStrEmail());
        lblBirthdate.setText(student.getStrBirthdate());
        lblGender.setText(student.getStrGender());
        lblState.setText(student.getStrState());
    }

}

