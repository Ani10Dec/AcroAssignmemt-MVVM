package com.example.acroassignment.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager

class AppHelper {
    companion object {

        fun isOnline(mContext: Context): Boolean {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }

        private var progressBar: ProgressDialog? = null
        fun showProgressBar(context: Context?) {
            progressBar = ProgressDialog(context)
            progressBar!!.setMessage("Loading Please Wait...")
            progressBar!!.setCancelable(false)
            progressBar!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressBar!!.show()
        }

        fun hideProgressBar() {
            if (progressBar != null && progressBar!!.isShowing) progressBar!!.dismiss()
        }
    }
}