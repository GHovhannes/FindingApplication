package com.example.facedetectorapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

public class SearchPageActivity extends AppCompatActivity {

    Button selectImage,detectFaces;

    ImageView imageView,toProfilePage,toHistoryPage;

    private static int RESULT_LOAD_IMAGE = 1;

    String path;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        checkPermission();

        selectImage = findViewById(R.id.importer);
        detectFaces = findViewById(R.id.finder);
        imageView = findViewById(R.id.image);
        toHistoryPage = findViewById(R.id.to_history_page);
        toProfilePage = findViewById(R.id.to_profile_page);

        toProfilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile = new Intent(getApplicationContext(),ProfilePageActivity.class);
                startActivity(toProfile);
            }
        });
        toHistoryPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHistory = new Intent(getApplicationContext(),HistoryPageActivity.class);
                startActivity(toHistory);
            }
        });
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            }
        });

        detectFaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap inputImage = BitmapFactory.decodeFile(path);

                Paint paint = new Paint();
                paint.setStrokeWidth(10);
                paint.setColor(Color.YELLOW);
                paint.setStyle(Paint.Style.STROKE);

                Bitmap finalImage = Bitmap.createBitmap(
                        inputImage.getWidth(),
                        inputImage.getHeight(),
                        Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(finalImage);

                canvas.drawBitmap(inputImage,0,0,null);

                com.google.android.gms.vision.face.FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                        .setTrackingEnabled(false)
                        .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                        .setMode(FaceDetector.ACCURATE_MODE)
                        .build();

                if(!faceDetector.isOperational()){
                    Toast.makeText(SearchPageActivity.this, "Detector is not ready now", Toast.LENGTH_SHORT).show();
                    return;

                }

                Frame frame = new Frame.Builder().setBitmap(inputImage).build();

                SparseArray<Face> faces = faceDetector.detect(frame);

                for(int i = 0; i<faces.size();i++){
                    Face face = faces.valueAt(i);

                    float x1 = face.getPosition().x;
                    float y1 = face.getPosition().y;
                    float x2 = x1 + face.getWidth();
                    float y2 = y1 + face.getHeight();

                    RectF rect = new RectF(x1,y1,x2,y2);
                    canvas.drawRect(rect,paint);


                    for(Landmark landmark : face.getLandmarks()){
                        int circleX = (int) landmark.getPosition().x;
                        int circleY = (int) landmark.getPosition().y;

                        canvas.drawCircle(circleX,circleY,10,paint);

                    }
                }

                imageView.setImageDrawable(new BitmapDrawable(getResources(),finalImage));
                faceDetector.release();
            }

        });
    }
    public boolean checkPermission(){
        if(Build.VERSION.SDK_INT>=27){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3);
                return false;
            }
        }else {
            return true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            String[] filePaths = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,filePaths,null,null,null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePaths[0]);
            path = cursor.getString(columnIndex);
            cursor.close();

            imageView.setImageDrawable(BitmapDrawable.createFromPath(path));
        }
    }
}