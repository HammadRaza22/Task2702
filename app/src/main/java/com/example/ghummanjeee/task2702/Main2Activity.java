package com.example.ghummanjeee.task2702;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    ImageView vl;
Uri ri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
     Bundle bundle = this.getIntent().getExtras();
        Intent intent = getIntent();
        vl=(ImageView)findViewById(R.id.imageView);
       Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Image");
      vl.setImageBitmap(bitmap);
    }
}
