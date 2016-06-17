package br.com.dts.intents;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    private final String options [] = {"Abir Browser", "Fazer ligação", "Msg. Whatsapp", "Abrir mapa em localicação", "Tirar foto", "Abrir galeria", "Sair" };
    private static final String sURL = "http://developer.android.com";
    private static final String sMESSAGE = "Olá, estou em aula";
    private static final String sPHONE = "99998888";
    private Intent mIntent;

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
                break;

            case 1:
                mIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + sPHONE));
                break;

            case 2:
                mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.putExtra(Intent.EXTRA_TEXT, sMESSAGE);
                mIntent.setType("text/plain");
                mIntent.setPackage("com.whatsapp");
                break;
            case 3:
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                mIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mIntent.setPackage("com.google.android.apps.maps");
                break;
            case 4:
                mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                break;
            case 5:
                mIntent = new Intent();
                mIntent.setAction(android.content.Intent.ACTION_VIEW);
                mIntent.setType("image/*");
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                break;
            default:
                finish();
                break;

        }

        if (mIntent != null) startActivity(mIntent);
    }
}
