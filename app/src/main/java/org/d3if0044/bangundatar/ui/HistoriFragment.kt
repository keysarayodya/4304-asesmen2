package org.d3if0044.bangundatar.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if0044.bangundatar.R
import org.d3if0044.bangundatar.adapter.HistoriAdapter
import org.d3if0044.bangundatar.adapter.HistoriClickAction
import org.d3if0044.bangundatar.adapter.HistoriListener
import org.d3if0044.bangundatar.data.local.BangunDatarDatabase
import org.d3if0044.bangundatar.data.local.BangunDatarEntity
import org.d3if0044.bangundatar.databinding.FragmentHistoriBinding
import org.d3if0044.bangundatar.viewmodel.HistoriViewModel
import org.d3if0044.bangundatar.viewmodel.HistoriViewModelFactory

class HistoriFragment : Fragment() {
    private val viewModel: HistoriViewModel by lazy {
        val local = BangunDatarDatabase.getInstance(requireContext())
        val factory = HistoriViewModelFactory(local.dao)
        ViewModelProvider(this, factory).get(HistoriViewModel::class.java)
    }

    private lateinit var binding: FragmentHistoriBinding
    private lateinit var myAdapter: HistoriAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHistoriBinding.inflate(layoutInflater,
            container, false)
        myAdapter = HistoriAdapter(HistoriListener{bangunDatar, action->
            when(action) {
                HistoriClickAction.DELETE->deleteBangunDatar(bangunDatar)
            }
        })
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context,
                    RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.noteCalculation.observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
                .setMessage(R.string.konfirmasi_hapus)
                .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                    viewModel.clearData()
                }
                .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
    }

    private fun deleteBangunDatar(bangunDatarEntity: BangunDatarEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Yakin ingin menghapus riwayat ini ?")
            .setPositiveButton("Ya") { _, _ ->
                viewModel.deleteNote(bangunDatarEntity)
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}