package com.infinite.pay.medicines.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.databinding.FragmentModuleBinding

class ModuleFragment : Fragment(), View.OnClickListener {

    private lateinit var bindView: FragmentModuleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentModuleBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.moduleAtomize.setOnClickListener(this)
        bindView.modulePrescription.setOnClickListener(this)
        bindView.moduleStock.setOnClickListener(this)
        bindView.moduleRetail.setOnClickListener(this)
        bindView.moduleSetting.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.module_atomize -> findNavController().navigate(ModuleFragmentDirections.actionModuleFragmentToAtomizeFragment())
            R.id.module_prescription -> findNavController().navigate(ModuleFragmentDirections.actionModuleFragmentToPrescriptionFragment())
            R.id.module_retail -> findNavController().navigate(ModuleFragmentDirections.actionModuleFragmentToRetailFragment())
            R.id.module_stock -> findNavController().navigate(ModuleFragmentDirections.actionModuleFragmentToStockFragment())
            R.id.module_setting -> findNavController().navigate(ModuleFragmentDirections.actionModuleFragmentToSettingsFragment())
        }
    }

}