package com.infinite.pay.medicines.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.infinite.pay.medicines.databinding.FragmentRetailBinding

/**
 * 零售
 */
class RetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var bindView: FragmentRetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = FragmentRetailBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}