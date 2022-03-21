package com.infinite.pay.medicines.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.adapter.MedicineItemAdapter
import com.infinite.pay.medicines.adapter.PrescriptionItemAdapter
import com.infinite.pay.medicines.data.entity.Goods
import com.infinite.pay.medicines.data.entity.Medicine
import com.infinite.pay.medicines.databinding.FragmentPrescriptionBinding
import com.infinite.pay.medicines.enums.UnitEnum
import com.infinite.pay.medicines.popup.ItemCountPopup
import es.dmoral.toasty.Toasty

/**
 * 处方
 */
class PrescriptionFragment : Fragment() {

    private lateinit var bindView: FragmentPrescriptionBinding
    private val prescriptionItemAdapter: PrescriptionItemAdapter by lazy {
        PrescriptionItemAdapter().apply {
            addChildClickViewIds(R.id.count)
            setOnItemChildClickListener { _, _, position ->
                ItemCountPopup(this@PrescriptionFragment.requireContext()).apply {
                    popupWithClick = {
                        getItem(position).count = it
                        notifyItemChanged(position)
                    }
                }.showPopupWindow()
            }
        }
    }
    private val medicineItemAdapter: MedicineItemAdapter by lazy { MedicineItemAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentPrescriptionBinding.inflate(inflater, container, false)
        return bindView.apply { lifecycleOwner = viewLifecycleOwner }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.prescriptionItemList.adapter = prescriptionItemAdapter
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                prescriptionItemAdapter.removeAt(viewHolder.bindingAdapterPosition)
            }

        }).attachToRecyclerView(bindView.prescriptionItemList)
        bindView.prescriptionMedicines.adapter = medicineItemAdapter

        medicineItemAdapter.addData(MutableList(80) {
            Medicine(it + 1, "商品名称", units = UnitEnum.BOTTLE)
        })

        medicineItemAdapter.setOnItemClickListener { _, _, position ->
            prescriptionItemAdapter.addData(0, Goods(medicineItemAdapter.getItem(position)))
        }

        bindView.clear.setOnClickListener { prescriptionItemAdapter.setNewInstance(mutableListOf()) }
        bindView.close.setOnClickListener { findNavController().navigateUp() }
        bindView.checkout.setOnClickListener {
            var price = 0.0
            prescriptionItemAdapter.data.forEach { price += it.total() }
            Toasty.success(requireContext(), "$price").show()
        }
    }

}