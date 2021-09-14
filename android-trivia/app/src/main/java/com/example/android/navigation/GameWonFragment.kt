package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view: View ->
            // view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment2)
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment2())
        }

        // declaring that our fragment has menu
        setHasOptionsMenu(true)

        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context,"Num Correct :${args.numCorrect}, Num Question :${args.numQuestions}"
            ,Toast.LENGTH_SHORT).show()

        return binding.root
    }

    // Create share Intent
    /***
     * ShareCompat is a Extra helper functionality for sharing data between activities.
     * ShareCompat provides functionality to extend the
     * Intent.ACTION_SEND/ Intent.ACTION_SEND_MULTIPLE protocol and
     * support retrieving more info about the activity that invoked a social sharing action.
     *
     * ******************************************************************************************  *
     *
     * shareCompat.IntentBuilder provides helper functions for constructing a sharing intent that always
     * includes data about the calling activity and app. This lets the called activity provide attribution for
     * the app that shared content. Constructing an intent this way can be done in a method-chaining style.
     * To obtain an IntentBuilder with info about your calling activity, use the static method
     */
    // create a getShareIntent method and move our GameWonFragmentArgs.fromBundle there
    // using shareCompat to create our share Implicit intent
    private fun getShareIntent() : Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
             shareIntent.setType("text/plain")
                 .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect,
                 args.numQuestions))
             return shareIntent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)

        // check if activity resolves.
        if(null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve.
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    // sharing from the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}