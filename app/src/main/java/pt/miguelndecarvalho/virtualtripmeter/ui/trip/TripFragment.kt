package pt.miguelndecarvalho.virtualtripmeter.ui.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pt.miguelndecarvalho.virtualtripmeter.R

class TripFragment : Fragment() {

    private lateinit var tripViewModel: TripViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        tripViewModel =
                ViewModelProvider(this).get(TripViewModel::class.java)
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }
}