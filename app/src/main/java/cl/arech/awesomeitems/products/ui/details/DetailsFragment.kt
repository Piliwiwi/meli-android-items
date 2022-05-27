package cl.arech.awesomeitems.products.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cl.arech.awesomeitems.databinding.FragmentProductsDetailsBinding
import cl.arech.awesomeitems.products.ui.list.ListFragmentArgs

class DetailsFragment : Fragment() {
    private var binding: FragmentProductsDetailsBinding? = null

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null)
            binding = FragmentProductsDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = binding?.apply {
        testText.text = "Detalles del producto ${args.productId}"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}