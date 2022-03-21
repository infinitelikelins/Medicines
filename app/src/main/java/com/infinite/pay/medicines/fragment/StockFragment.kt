package com.infinite.pay.medicines.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.infinite.pay.medicines.databinding.FragmentStockBinding

/**
 * 商品库存
 */
class StockFragment : Fragment() {

    private lateinit var bindView: FragmentStockBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = FragmentStockBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}
