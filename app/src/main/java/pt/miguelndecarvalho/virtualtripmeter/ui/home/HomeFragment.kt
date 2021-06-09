package pt.miguelndecarvalho.virtualtripmeter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import pt.miguelndecarvalho.virtualtripmeter.MainActivity
import pt.miguelndecarvalho.virtualtripmeter.R
import pt.miguelndecarvalho.virtualtripmeter.ui.trip.TripFragment

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        var mView = inflater.inflate(R.layout.fragment_home, container, false)

        var trip = TripFragment()

        mView.findViewById<Button>(R.id.start_button).setOnClickListener{
            val transaction = parentFragmentManager.beginTransaction()

            transaction.replace(R.id.nav_host_fragment, trip)
            transaction.addToBackStack(null)
            transaction.commit()
            (activity as MainActivity?)?.tripBarSelected(1)
        }

        return mView
    }
}