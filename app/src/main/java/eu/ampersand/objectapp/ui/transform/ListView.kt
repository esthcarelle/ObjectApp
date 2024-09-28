package eu.ampersand.objectapp.ui.transform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import eu.ampersand.objectapp.R
import eu.ampersand.objectapp.adapter.TransformAdapter
import eu.ampersand.objectapp.databinding.FragmentListViewBinding
import eu.ampersand.objectapp.utils.NetworkResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Fragment that demonstrates a responsive layout pattern where the format of the content
 * transforms depending on the size of the screen. Specifically this Fragment shows items in
 * the [RecyclerView] using LinearLayoutManager in a small screen
 * and shows items using GridLayoutManager in a large screen.
 */
@AndroidEntryPoint
class ListView : Fragment() {

    private lateinit var _binding: FragmentListViewBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val phoneObjectsViewModel : PhoneObjectsViewModel  by viewModels()
        _binding = FragmentListViewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerviewTransform
        val adapter = TransformAdapter { phoneItem ->
            // Handle item click
            val action = ListViewDirections
                .actionNavTransformToDetailsFragment(phoneItem)
            val navController = findNavController()

            navController.navigate(action)
        }

        recyclerView.adapter = adapter
        lifecycleScope.launch {
            phoneObjectsViewModel.phoneObject.collectLatest {
                when (it) {
                    is NetworkResult.Success -> {
                        binding.isProgressBarVisible = false
                        adapter.submitList(it.data.toMutableList())
                    }
                    is NetworkResult.Failure -> {
                        binding.isProgressBarVisible = false
                        val snackBar = Snackbar.make(binding.root,
                            getString(R.string.we_could_not_retrieve_objects_please_try_again_later), Snackbar.LENGTH_INDEFINITE)

                        snackBar.setAction(getString(R.string.retry)) {
                            phoneObjectsViewModel.getPhoneObjects()
                        }
                        snackBar.show()

                    }
                    is NetworkResult.Loading -> {
                        binding.isProgressBarVisible = true
                    }
                }
            }
        }
        // Setup the toolbar
        (activity as? AppCompatActivity)?.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)  // Show the back button
            title = getString(R.string.object_app)
        }
        return root
    }
}