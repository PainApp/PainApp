package xyz.painapp.pocketdoc.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Build
import android.os.Bundle
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.activities.CausesActivity
import xyz.painapp.pocketdoc.activities.RegionActivity

interface HelpDialogInteractionListener {
    fun openHelpFragment()
}

class HelpDialogFragment : DialogFragment() {
    companion object {
        const val HELP_DIALOG_TAG = "help_dialog_tag"
        const val HELP_FRAGMENT_TAG = "help_fragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(activity)

        if (activity is HelpDialogInteractionListener) {
            builder.setTitle(R.string.help_title)
            when (activity) {
                is BodyActivity -> builder.setMessage(R.string.body_activity_help)
                is RegionActivity -> builder.setMessage(R.string.region_activity_help)
                is CausesActivity -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder.setView(R.layout.causes_help_dialog)
                } else {
                    builder.setMessage(R.string.default_help_dialog)
                }
            }

            return builder.run {
                setPositiveButton(R.string.more_help, { dialog, _ ->
                    dialog.dismiss()
                    (activity as HelpDialogInteractionListener).openHelpFragment()
                })
                setNegativeButton(R.string.close, { dialog, _ -> dialog.dismiss() })
                create()
            }

        } else throw ClassCastException(activity.toString()
                + " must implement HelpDialogInteractionListener")
    }
}