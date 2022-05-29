package cl.arech.awesomeitems.products.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import cl.arech.awesomeitems.databinding.FragmentProductsDetailsBinding
import cl.arech.awesomeitems.products.ui.provider.UiComponentProvider
import cl.arech.utils.extension.setImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var binding: FragmentProductsDetailsBinding? = null

    @Inject
    lateinit var uiProvider: UiComponentProvider

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        title.text = args.product.title
        price.text = args.product.price
        freeShipping.isVisible = args.product.shipping.hasFreeShipping
        image.setImage(args.product.thumbnail)
        detailList.setAttributes(
            uiProvider.getDetailsAttrs(args.product.attributes)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}