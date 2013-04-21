/**
 * 
 */
package base;

import android.util.Log;

/**
 * @author jie_huang
 *
 */
public class SdkSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7412469832870082860L;

	/**
	 * 
	 */
	public SdkSystemException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public SdkSystemException(String arg0) {
		super(arg0);
		Log.d("mydebug",arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public SdkSystemException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SdkSystemException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
