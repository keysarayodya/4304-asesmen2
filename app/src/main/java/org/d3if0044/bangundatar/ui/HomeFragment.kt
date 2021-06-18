package org.d3if0044.bangundatar.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if0044.bangundatar.R
import org.d3if0044.bangundatar.data.KategoriBangunDatar
import org.d3if0044.bangundatar.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var kategoriBangunDatar: KategoriBangunDatar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(
                layoutInflater, container, false)
        binding.selectButton.setOnClickListener { view: View ->
            pilihBangunDatar()
            if (kategoriBangunDatar != KategoriBangunDatar.ERROR) {
                view.findNavController().navigate(
                        HomeFragmentDirections.
                        actionHomeFragmentToMenuFragment(kategoriBangunDatar)
                )
            }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_homeFragment_to_aboutFragment)
            return true
        } else if (item.itemId == R.id.menu_universitas) {
            findNavController().navigate(
                R.id.action_homeFragment_to_universitasFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun pilihBangunDatar () {
        val selectedJenis = binding.radioGroup2.checkedRadioButtonId
        if (selectedJenis == -1) {
            Toast.makeText(context, R.string.jenis_invalid, Toast.LENGTH_LONG).show()
            kategoriBangunDatar = KategoriBangunDatar.ERROR
        }
        else if (selectedJenis == R.id.persegiRadioButton) {
            kategoriBangunDatar = KategoriBangunDatar.PERSEGI
        }
        else if (selectedJenis == R.id.segitigaRadioButton) {
            kategoriBangunDatar = KategoriBangunDatar.SEGITIGA
        }
        else {
            kategoriBangunDatar = KategoriBangunDatar.LINGKARAN
        }
    }

}