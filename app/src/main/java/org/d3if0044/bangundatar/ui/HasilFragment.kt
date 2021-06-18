package org.d3if0044.bangundatar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if0044.bangundatar.R
import org.d3if0044.bangundatar.data.KategoriBangunDatar
import org.d3if0044.bangundatar.databinding.FragmentHasilBinding

class HasilFragment : Fragment() {

    private val args: HasilFragmentArgs by navArgs()
    private lateinit var binding: FragmentHasilBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHasilBinding.inflate(
            layoutInflater, container, false)
        updateUI(args.kategori)
        return binding.root
    }

    private fun updateUI(kategori: KategoriBangunDatar) {
        val actionBar = (requireActivity() as AppCompatActivity)
            .supportActionBar
        when (kategori) {
            KategoriBangunDatar.PERSEGI -> {
                actionBar?.title = getString(R.string.judul_persegi)
                binding.imageView.setImageResource(R.drawable.persegi)
                binding.textView.text = getString(R.string.rumus_persegi)
            }
            KategoriBangunDatar.SEGITIGA -> {
                actionBar?.title = getString(R.string.judul_segitiga)
                binding.imageView.setImageResource(R.drawable.segitiga)
                binding.textView.text = getString(R.string.rumus_segitiga)
            }
            KategoriBangunDatar.LINGKARAN -> {
                actionBar?.title = getString(R.string.judul_lingkaran)
                binding.imageView.setImageResource(R.drawable.lingkaran)
                binding.textView.text = getString(R.string.rumus_lingkaran)
            }
        }
    }
}