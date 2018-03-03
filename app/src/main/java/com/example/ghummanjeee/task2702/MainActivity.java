package com.example.ghummanjeee.task2702;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;

import static com.example.ghummanjeee.task2702.R.id.imageView;


public class MainActivity extends AppCompatActivity {
    ImageView vi;
    Button hbtm, hnext;
    Integer REQUEST_CAMERA = 0, SELCT_FILE = 1;
    Bitmap btm;
      Uri imge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vi = (ImageView) findViewById(imageView);
        hbtm = (Button) findViewById(R.id.btn);
        hnext = (Button) findViewById(R.id.next);
        hnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });


        hbtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
    }

    private  void  send(){
        Intent intent=new Intent(this,Main2Activity.class);
       intent.putExtra("Image",btm);
      intent.putExtra("imagePath",imge);
        startActivity(intent);;


    }
    private void select() {
        final CharSequence[] items = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ADD IMAGE");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {
                    Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it, REQUEST_CAMERA);
                } else if (items[i].equals("Gallery")) {
                    Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    it.setType("image/*");
                    startActivityForResult(it.createChooser(it, "Select File"), SELCT_FILE);
                }
            }
        });
        builder.show();

    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (resultcode == Activity.RESULT_OK) {

            if (requestcode == REQUEST_CAMERA) {

                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    btm = (Bitmap) bundle.get("data");
                    vi.setImageBitmap(btm);
                }
            } else {
                if (requestcode == SELCT_FILE) {
                    imge = data.getData();

                    try {
                        btm = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imge);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    vi.setImageBitmap(btm);


                }
            }
        }
    }



}
