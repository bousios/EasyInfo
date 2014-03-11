/*Η κλάση EasyInfoArxiki χρησιμοποιείται για να προσδιοριστεί η θέση του χρήστη και να αποδοθεί στις static μεταβλητές Simio.mylat
 * , Simio.mylong. Επίσης αφού ο χρήστης προσδιορίσει την ακτίνα για την επιστροφή των αποτελεσμάτων αυτή αποδίδεται στην static
 * μεταβλητή Simio.aktina. Με το πάτημα του κουμπιού "Ανάκτηση Πληροφοριών" μεταφερόμαστε στην κλάση EasyInfoListView
  */

package com.example.easyinfo;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EasyInfoArxiki extends Activity {


	private static final long mindistance=10;
	private static final long mintime=10000;
	protected LocationManager locmanager;

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_info_arxiki);
        
        locmanager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locmanager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mintime, mindistance, new TrexousathesiListener());
	     
	    Location thesi=locmanager.getLastKnownLocation(locmanager.NETWORK_PROVIDER);
		Simio.mylat=thesi.getLatitude();
		Simio.mylong=thesi.getLongitude();
		
		EditText textthesi=(EditText) findViewById(R.id.text_thesi);
		
	    if (thesi != null){
    		String message=String.format("Τρέχουσα Θέση \n Γεωγραφικό Πλάτος: %1$s \n Γεωγραφικό Μήκος: %2$s",thesi.getLatitude(), thesi.getLongitude());
    		Toast.makeText(EasyInfoArxiki.this, message, Toast.LENGTH_LONG).show();
    		textthesi.setText("Η Τρέχουσα θέση προσδιορίστηκε");
    	}
		 
		
		Button banaktisi =(Button) findViewById(R.id.button_anaktisi);
		banaktisi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				EditText textaktina=(EditText) findViewById(R.id.text_aktina);
				Simio.aktina=Integer.parseInt(textaktina.getText().toString());
				Intent goListView=new Intent(EasyInfoArxiki.this, EasyInfoListview.class);
				startActivity(goListView);
			}
		});
		
    }

    private class TrexousathesiListener implements LocationListener {

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
		     Location thesi=locmanager.getLastKnownLocation(locmanager.NETWORK_PROVIDER);
			 Simio.mylat=thesi.getLatitude();
			 Simio.mylong=thesi.getLongitude();
			 String message=String.format("Τρέχουσα Θέση \n Γεωγραφικό Πλάτος: %1$s \n Γεωγραφικό Μήκος: %2$s",thesi.getLatitude(), thesi.getLongitude());
			 Toast.makeText(EasyInfoArxiki.this, message, Toast.LENGTH_LONG).show();
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(EasyInfoArxiki.this, "Απενεργοποίηση GPS", Toast.LENGTH_LONG).show();	
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(EasyInfoArxiki.this, "Ενεργοποίηση GPS", Toast.LENGTH_LONG).show();
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_easy_info_arxiki, menu);
        return true;
    }
    
}
