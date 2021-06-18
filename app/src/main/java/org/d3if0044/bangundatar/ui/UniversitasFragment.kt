package org.d3if0044.bangundatar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0044.bangundatar.adapter.UniversitasAdapter
import org.d3if0044.bangundatar.databinding.FragmentUniversitasBinding
import org.d3if0044.bangundatar.viewmodel.UniversitasViewModel

class UniversitasFragment : Fragment() {
    private val viewModel: UniversitasViewModel by lazy {
        ViewModelProvider(this).get(UniversitasViewModel::class.java)
    }
    private lateinit var binding: FragmentUniversitasBinding
    private lateinit var myAdapter: UniversitasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniversitasBinding.inflate(
            layoutInflater,
            container, false
        )
        myAdapter = UniversitasAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        viewModel.retrieveData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
            if (it.isEmpty()) {
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.emptyView.visibility = View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, { loading->
            if (loading) {
                binding.progressBarUniversity.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.progressBarUniversity.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        })
    }
}