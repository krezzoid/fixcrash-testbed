package io.github.krezzoid.fixcrash.testbed.details

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import io.github.krezzoid.fixcrash.testbed.R

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val handler = Handler(Looper.getMainLooper())
    private val loadDetails = Runnable { onDetailsLoaded() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler.postDelayed(loadDetails, LOAD_DELAY_MS)
    }

    override fun onDestroyView() {
        handler.removeCallbacks(loadDetails)
        super.onDestroyView()
    }

    private fun onDetailsLoaded() {
        val context = context ?: return
        val text = context.getString(R.string.details_loaded)
        view?.findViewById<TextView>(R.id.details_text)?.text = text
    }

    private companion object {
        const val LOAD_DELAY_MS = 1_500L
    }
}
