package com.infinite.pay.medicines.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.Hold
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialElevationScale
import com.infinite.pay.medicines.R
import com.infinite.pay.medicines.adapter.MedicineItemAdapter
import com.infinite.pay.medicines.adapter.PrescriptionItemAdapter
import com.infinite.pay.medicines.data.entity.Goods
import com.infinite.pay.medicines.databinding.FragmentPrescriptionBinding
import com.infinite.pay.medicines.popup.ItemCountPopup
import com.infinite.pay.medicines.popup.PaymentPopup

/**
 * 处方
 */
class PrescriptionFragment : Fragment() {

    private lateinit var bindView: FragmentPrescriptionBinding

    private val prescriptionItemAdapter: PrescriptionItemAdapter by lazy {
        PrescriptionItemAdapter().apply {
            addChildClickViewIds(R.id.count)
            setOnItemChildClickListener { _, childView, position ->
                if (childView.id == R.id.count) {
                    ItemCountPopup(this@PrescriptionFragment.requireContext()).apply {
                        popupWithClick = {
                            getItem(position).count = it
                            notifyItemChanged(position)
                        }
                    }.showPopupWindow()
                }
            }
        }
    }
    private val medicineItemAdapter: MedicineItemAdapter by lazy { MedicineItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
        reenterTransition = Hold()
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300
            pathMotion = MaterialArcMotion()
            fadeMode = MaterialContainerTransform.FADE_MODE_THROUGH
            fadeProgressThresholds = MaterialContainerTransform.ProgressThresholds(0.6f,0.9f)
            transitionDirection = MaterialContainerTransform.TRANSITION_DIRECTION_AUTO
        }
    }

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
        // RecyclerView左右滑动删除
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            // 上下移动
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            // 左右滑动
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                prescriptionItemAdapter.removeAt(viewHolder.bindingAdapterPosition)
            }

        }).attachToRecyclerView(bindView.prescriptionItemList)
        bindView.prescriptionMedicines.adapter = medicineItemAdapter

        medicineItemAdapter.setOnItemClickListener { _, _, position ->
            prescriptionItemAdapter.addData(0, Goods(medicineItemAdapter.getItem(position)))
        }

        bindView.clear.setOnClickListener { prescriptionItemAdapter.setNewInstance(mutableListOf()) }
        bindView.close.setOnClickListener { findNavController().navigateUp() }
        bindView.checkout.setOnClickListener {
            val price = prescriptionItemAdapter.data.sumOf { it.total() }
            PaymentPopup(requireContext(), price).apply {
                popupWithCompleted = {
                    prescriptionItemAdapter.setNewInstance(mutableListOf())
                }
            }.showPopupWindow()
        }
    }

}