/* H κλάση Simio χρησιμποιείται για να γίνουν static οι συντεταγμένες της τρέχουσας θέσης του χρήστη (mylat, mylong) 
 * και η ακτίνα για την επιστροφή αποτελεσμάτων
 */

package com.example.easyinfo;

import android.app.Application;

public class Simio extends Application{
	
	public static int aktina;
	public static double mylat, mylong;

}
