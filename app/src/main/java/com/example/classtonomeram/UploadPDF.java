package com.example.classtonomeram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPDF extends AppCompatActivity {

    private Button upload_btn;
    private EditText pdf_name;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private static final int PICK_PDF_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        upload_btn = findViewById(R.id.upload_btn);
        pdf_name = findViewById(R.id.name);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), PICK_PDF_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            uploadFile(uri);
        }
    }

    private void uploadFile(Uri fileUri) {
        if (fileUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading....");
            progressDialog.show();

            String fileName = pdf_name.getText().toString().trim();
            if (fileName.isEmpty()) {
                Toast.makeText(this, "Please enter a PDF name", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                return;
            }

            StorageReference fileReference = storageReference.child(fileName + ".pdf");

            UploadTask uploadTask = fileReference.putFile(fileUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete()) ;

                    Uri downloadUri = uriTask.getResult();

                    if (downloadUri != null) {
                        PdfClass pdfClass = new PdfClass(fileName, downloadUri.toString());
                        databaseReference.push().setValue(pdfClass);
                        Toast.makeText(UploadPDF.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UploadPDF.this, "Failed to retrieve download URL", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");
                }

            });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}

