/*H ����� EasyInfoListview ��������������� ��� �� ����������� ������ �� url string ��� ��� ���� ��� xml ��������� ������� �� ��� ���� ��� 
 * ������ (Simio.mylat, Simio.mylong) ��� ��� ������ ��������� Simio.aktina. ���� �������� ������������� ��� ����������� ��� 
 * ������ ParsingUtility, �� ����� ��������������� ��� �� http request ��� ��� �������� ��� �������� ��� �� xml. 
 * �� �������� ��� ����������� ������������� �� ��� ����������� ArrayList<HashMap<String, String>> ��� ������������ �� ��� 
 * ListView. �� �� ������ �� ��� �������� ��� ListView ������������� ��� ���������� ����������.
 */
package com.example.easyinfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class EasyInfoListview extends ListActivity{
    
	static final String labelArticle = "article"; 
	static final String labelType = "type";
    static final String labelTitle = "title";
    static final String labelMobileURL = "mobileurl";
    static final String labelDistance = "distance";
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parsing);
		
		String url =String.format("http://api.wikilocation.org/articles?lat=%1$s&lng=%2$s&format=xml&radius=%3$s",Simio.mylat, Simio.mylong, Simio.aktina);
				
	    ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();
	    ParsingUtility parser = new ParsingUtility ();
	    String xml = parser.getXmlFromUrl(url); 
	    Document doc = parser.getDomElement(xml); 

	    NodeList nl = doc.getElementsByTagName(labelArticle);
	    
	    for (int i = 0; i < nl.getLength(); i++) {
	        HashMap<String, String> map = new HashMap<String, String>();
	        Element e = (Element) nl.item(i);
	        map.put(labelType, parser.getValue(e, labelType));
	        map.put(labelTitle, parser.getValue(e, labelTitle));
	        map.put(labelMobileURL, parser.getValue(e, labelMobileURL));
	        map.put(labelDistance, parser.getValue(e, labelDistance));

	        menuItems.add(map);
	    }
	    ListAdapter adapter = new SimpleAdapter(this, menuItems, R.layout.listitem, new String[] { labelTitle, labelMobileURL, labelDistance }, new int[] {R.id.title, R.id.mobileurl, R.id.distance });
	    setListAdapter(adapter);
        ListView mylistview = getListView();
        mylistview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String title = ((TextView) arg1.findViewById(R.id.title)).getText().toString();
                String mobileurl = ((TextView) arg1.findViewById(R.id.mobileurl)).getText().toString();
                String distance = ((TextView) arg1.findViewById(R.id.distance)).getText().toString();
 
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mobileurl));
                startActivity(i);
				
			}
        	
        });
	}
}
	

