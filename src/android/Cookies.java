/*
 * Copyright 2013 Ernests Karlsons
 * https://github.com/bez4pieci
 * http://www.karlsons.net
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package com.bez4pieci.cookies;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;
import android.webkit.CookieManager;
import android.text.TextUtils;
public class Cookies extends CordovaPlugin {
	
	private final String TAG = "CookiesPlugin";
	
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("clear".equals(action)) {
            this.clear();
            callbackContext.success();
            return true;
        } else if ("hasCookies".equals(action)) {
            boolean b = this.hasCookies();
            if (b) {
              callbackContext.success();
            } else {
              callbackContext.error("no session");
            }

            return true;
        }
        return false;  // Returning false results in a "MethodNotFound" error.
    }

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public void clear() {
		Log.v(TAG, "Clearing cookies...");
        CookieManager.getInstance().removeAllCookies(null);
    }
   public boolean hasCookies() {
		Log.v(TAG, "has cookies...");
     // 获取一个网站的cookie
        String cookie1 = CookieManager.getInstance().getCookie("www.majiamen.com");
     if(cookie1 != null && !TextUtils.isEmpty(cookie1)) {
        if (cookie1.contains("91a70_winduser") && cookie1.contains("91a70_ck_info")) {
          return true;
        }
     }

		  return false;
    }
    

}
