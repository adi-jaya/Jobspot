package com.jobspot.jobspot;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileChooserFragment extends Fragment {
    private static final int REQUEST_CODE = 1;
    // private static final int RESULT_OK = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    LinearLayout chooseFile;
    MaterialButton uploadFileButton;
    ImageView iconImageView;
    TextView textTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_chooser, container, false);

        chooseFile = view.findViewById(R.id.chooseFile);
        iconImageView = view.findViewById(R.id.iconImageView);
        textTextView = view.findViewById(R.id.textTextView);
        uploadFileButton = view.findViewById(R.id.uploadFileButton);
        uploadFileButton.setVisibility(View.GONE);

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissionAndChooseFile();
            }
        });

        uploadFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetUploadFileBox();
            }
        });

        return view;
    }

    public void askPermissionAndChooseFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int selfPermission = ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);

            if (selfPermission != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                return;
            }
        }
        this.chooseFile();
    }

    public void chooseFile() {
        Intent chooseFile = new Intent();
        chooseFile.setAction(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile.addCategory((Intent.CATEGORY_OPENABLE)); // Only return URIs that can be opened with ContentResult
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ((requestCode == REQUEST_CODE) && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            this.chooseFile();
        } else {
            Toast.makeText(this.getContext(), "Permisson Denied", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * Add this method to receive the result of the file picker screen
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK) && (data != null) && (data.getData() != null)) {
            // Get URI pointing to the file that was selected from the user
            Uri uri = data.getData();

            // Get file extension
            String fileType = getFileType(uri);

            try {
                // Temporary file to hold content of actual file
                // File file = File.createTempFile("doc_", fileType);
                File file = File.createTempFile("doc_", "pdf");

                // Copy content from actual file to Temporary file using Input Stream
                copyInputStreamToFile(getContext().getContentResolver().openInputStream(uri), file);

                // At thi point the Temporary file object is ready, you can upload or use as needed
                setUploadFileBox();
            } catch (IOException e) {
                e.printStackTrace();
                resetUploadFileBox();
            }
        }
    }

    /*
     * Get the extension of the file the provided uti points to
     * */
    private String getFileType(Uri uri) {
        String mimeType = getContext().getContentResolver().getType(uri);
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(mimeType);
    }

    /*
     * Copy contents from input stream to file
     * */
    private void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);

            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

            while ((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }

            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Failed to load file: ", e.getMessage());
        }
    }

    public void setUploadFileBox() {
        iconImageView.setImageResource(R.drawable.ic_pdf_file);
        textTextView.setText("CV/Resume");
        uploadFileButton.setVisibility(View.VISIBLE);
    }

    public void resetUploadFileBox() {
        iconImageView.setImageResource(R.drawable.ic_upload_file);
        textTextView.setText("Upload CV/Resume");
        uploadFileButton.setVisibility(View.GONE);
    }
}