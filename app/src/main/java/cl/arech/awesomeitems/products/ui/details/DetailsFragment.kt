package cl.arech.awesomeitems.products.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.arech.awesomeitems.databinding.FragmentProductsDetailsBinding

class DetailsFragment : Fragment() {
    private var binding: FragmentProductsDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null)
            binding = FragmentProductsDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }
}