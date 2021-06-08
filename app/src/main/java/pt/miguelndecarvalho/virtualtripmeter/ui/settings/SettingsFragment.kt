package pt.miguelndecarvalho.virtualtripmeter.ui.settings

import android.content.Intent
import android.os.Bundle
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
        AuthUI.getInstance()
            .delete(requireContext().applicationContext)
            .addOnCompleteListener {
                val intent = Intent(requireContext().applicationContext, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(requireContext().applicationContext, getString(R.string.login_delete_toast, user?.displayName) , Toast.LENGTH_LONG).show()
            }
    }
}