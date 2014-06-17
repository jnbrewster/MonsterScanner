package com.happydad.monsterscanner.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private MediaPlayer scanSound;

    //icon sourced from: http://clipartist.net/tag/commons-wikimedia-org/
    //sound effect sourced from startrek: The original series

    @Override
    public void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView = (ImageView)this.findViewById(R.id.image_view_camera);
        Button photoButton = (Button) this.findViewById(R.id.takePicture);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);


            scanSoundeffect();
            //add scan sound

            for (int i = 0; i < 2; i++) {
                Toast scan1 = Toast.makeText(this, "Scanning.", Toast.LENGTH_SHORT);
                Toast scan2 = Toast.makeText(this, "Scanning..", Toast.LENGTH_SHORT);
                Toast scan3 = Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT);
                LinearLayout linearLayout1 = null;
                LinearLayout linearLayout2 = null;
                LinearLayout linearLayout3 = null;
                linearLayout1 = (LinearLayout) scan1.getView();
                linearLayout2 = (LinearLayout) scan2.getView();
                linearLayout3 = (LinearLayout) scan3.getView();
                View child1 = linearLayout1.getChildAt(0);
                View child2 = linearLayout2.getChildAt(0);
                View child3 = linearLayout3.getChildAt(0);
                TextView messageTextView1 = null;
                TextView messageTextView2 = null;
                TextView messageTextView3 = null;
                messageTextView1 = (TextView) child1;
                messageTextView2 = (TextView) child2;
                messageTextView3 = (TextView) child3;
                messageTextView1.setTextSize(40);
                messageTextView2.setTextSize(40);
                messageTextView3.setTextSize(40);
                scan1.show();
                scan2.show();
                scan3.show();
            }


            Random random = new Random();
            int RandomNumber = random.nextInt(21);
            System.out.println(RandomNumber);

            if (RandomNumber == 1) {
                Toast error = Toast.makeText(this, "Monster Detected! Scan again to remove the monster!", Toast.LENGTH_LONG);

                LinearLayout linearLayout = null;
                linearLayout = (LinearLayout) error.getView();
                View child = linearLayout.getChildAt(0);
                TextView messageTextView = null;
                messageTextView = (TextView) child;
                messageTextView.setTextSize(60);
                error.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                error.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                messageTextView.setTextColor(Color.RED);

                error.show();
            }
            else {
                for (int i=0; i < 1; i++) {
                    Toast monster = Toast.makeText(this, "No monsters detected", Toast.LENGTH_LONG);


                    LinearLayout linearLayout = null;
                    linearLayout = (LinearLayout) monster.getView();
                    View child = linearLayout.getChildAt(0);
                    TextView messageTextView = null;
                    messageTextView = (TextView) child;
                    messageTextView.setTextSize(60);
                    monster.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    monster.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    messageTextView.setTextColor(Color.GREEN);

                    monster.show();
                     }

                }

            }

        }


    public void scanSoundeffect() {
        //Scan sound
        final MediaPlayer scanSound = MediaPlayer.create(this, R.raw.scannersound);
        scanSound.start();

        //Kill the sound when it has played
        scanSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer scanSound) {
                scanSound.release();
            }
        });
    }

    protected void showdialog() {

        //TODO: Add a timer of about 3 seconds that shows the popup
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("No monsters have been found");
        dlgAlert.setTitle("Monster Scanner");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }



}
