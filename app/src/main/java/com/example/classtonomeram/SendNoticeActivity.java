package com.example.classtonomeram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class SendNoticeActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextMessage;
    private Button buttonSend;
    private DatabaseReference noticeReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notice);

        editTextTitle = findViewById(R.id.ettitle);
        editTextMessage = findViewById(R.id.etmessage);
        buttonSend = findViewById(R.id.btnsend);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            noticeReference = FirebaseDatabase.getInstance().getReference("Notices").child(userId);
        }

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotice();
            }
        });
    }

    private void sendNotice() {
        String title = editTextTitle.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        if (title.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please enter title and message", Toast.LENGTH_SHORT).show();
            return;
        }

        String noticeId = noticeReference.push().getKey();
        Date currentDate=new Date();
        Notice notice = new Notice(noticeId, title, message,currentDate);

        if (noticeId != null) {
            noticeReference.child(noticeId).setValue(notice);
            Toast.makeText(this, "Notice sent successfully", Toast.LENGTH_SHORT).show();
            editTextTitle.setText("");
            editTextMessage.setText("");
        } else {
            Toast.makeText(this, "Failed to send notice", Toast.LENGTH_SHORT).show();
        }
    }
}
