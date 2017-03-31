package br.com.dts.intents;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private final String options [] = {"Abir Browser", "Fazer ligação", "Msg. Whatsapp", "Abrir mapa em localicação", "Tirar foto", "Abrir galeria", "Outra App", "Sair" };
    private static final String sURL = "http://developer.android.com";
    private static final String sMESSAGE = "Olá, estou em aula";
    private static final String sPHONE = "99998888";
    private Intent mIntent;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CALL_PERMISSION = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, options));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        switch (position){

            case 0:
                mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse(sURL));
                startActivity(mIntent);
                break;

            case 1:
                int hasPhonePermission = checkSelfPermission(Manifest.permission.CALL_PHONE);
                if (hasPhonePermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_CALL_PERMISSION);
                } else {
                    //mIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sPHONE));
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sPHONE)));
                }
                break;
            case 2:
                mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.putExtra(Intent.EXTRA_TEXT, sMESSAGE);
                mIntent.setType("text/plain");
                mIntent.setPackage("com.whatsapp");
                startActivity(mIntent);
                break;
            case 3:
                Uri mapUri = Uri.parse("geo:0,0?q=37.423156,-122.084917 (" + "Local" + ")");
                mIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mIntent.setPackage("com.google.android.apps.maps");
                startActivity(mIntent);
                break;
            case 4:

                int hasCamera = checkSelfPermission(Manifest.permission.CAMERA);
                if (hasCamera != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION);

                } else {
                    mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent, REQUEST_IMAGE_CAPTURE);

                }
                break;

            case 5:
                mIntent = new Intent();
                mIntent.setAction(android.content.Intent.ACTION_VIEW);
                mIntent.setType("image/*");
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mIntent);

                break;

            case 6:
                mIntent = new Intent();
                mIntent.setAction("br.com.dts.intentfilter.View");
                mIntent.addCategory("br.com.dts.intentfilter.filter");
                startActivity(mIntent);
                break;


            default:
                finish();
                break;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, PictureActivity.class);
            intent.putExtras(data.getExtras());
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sPHONE));
                    startActivity(mIntent);

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to phone app", Toast.LENGTH_SHORT).show();
                }
                break;


            case REQUEST_CAMERA_PERMISSION:

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent, REQUEST_IMAGE_CAPTURE);

                } else {
                    Toast.makeText(MainActivity.this, "Permission denied to read camera ", Toast.LENGTH_SHORT).show();
                }
                break;



        }
    }
}
