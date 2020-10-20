package com.eugeneemelyanov.weatherapp.presentation.views

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.eugeneemelyanov.weatherapp.presentation.presenters.BasePresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.MvpFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    var progressDialog: ProgressDialog? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun hideLoader() {
        progressDialog?.dismiss()
    }

    override fun showLoader() {
        if(progressDialog == null){
            progressDialog = ProgressDialog.show(activity, null, "Loading")
        } else {
            progressDialog?.show()
        }
    }

    override fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}