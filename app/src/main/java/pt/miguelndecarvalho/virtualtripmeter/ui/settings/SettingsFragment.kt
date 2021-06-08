package pt.miguelndecarvalho.virtualtripmeter.ui.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pt.miguelndecarvalho.virtualtripmeter.LoginActivity
import pt.miguelndecarvalho.virtualtripmeter.R

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private val user = Firebase.auth.currentUser

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
                ViewModelProvider(this).get(SettingsViewModel::class.java)

        var mView = inflater.inflate(R.layout.fragment_settings, container, false)

        mView.findViewById<Button>(R.id.logout_button).setOnClickListener{
            logout()
        }

        mView.findViewById<Button>(R.id.delete_button).setOnClickListener{
            delete()
        }
        return mView
    }

    private fun logout() {
        AuthUI.getInstance()
            .signOut(requireContext().applicationContext)
            .addOnCompleteListener {
                val intent = Intent(requireContext().applicationContext, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(requireContext().applicationContext, getString(R.string.login_logout_toast, user?.displayName) , Toast.LENGTH_LONG).show()
            }
    }

    private fun delete() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.login_delete_dialog_title)
        builder.setMessage(R.string.login_delete_dialog_description)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes"){dialogInterface, which ->
            Log.d(tag,"User chose to delete account")
            deleteAccount()
        }

        builder.setNegativeButton("No"){dialogInterface, which ->
            Log.d(tag,"User chose not to delete account")
        }


        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun deleteAccount() {
        AuthUI.getInstance()
            .delete(requireContext().applicationContext)
            .addOnCompleteListener {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(activity, getString(R.string.login_delete_toast, user?.displayName) , Toast.LENGTH_LONG).show()
            }
    }
}