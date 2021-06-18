package org.d3if0044.bangundatar.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.d3if0044.bangundatar.R
import org.d3if0044.bangundatar.data.KategoriBangunDatar
import org.d3if0044.bangundatar.data.local.BangunDatarDatabase
import org.d3if0044.bangundatar.data.local.BangunDatarEntity
import org.d3if0044.bangundatar.databinding.FragmentMenuBinding
import org.d3if0044.bangundatar.viewmodel.HistoriViewModel
import org.d3if0044.bangundatar.viewmodel.HistoriViewModelFactory
import kotlin.coroutines.coroutineContext

class MenuFragment : Fragment() {

    private val args: HasilFragmentArgs by navArgs()
    private lateinit var binding: FragmentMenuBinding
    private lateinit var kategoriBangunDatar: KategoriBangunDatar
    private var kategoriPerhitungan = ""
    private val viewModel: HistoriViewModel by lazy {
        val local = BangunDatarDatabase.getInstance(requireContext())
        val factory = HistoriViewModelFactory(local.dao)
        ViewModelProvider(this, factory).get(HistoriViewModel::class.java)
    }

    private lateinit var tempDataCalculation: BangunDatarEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(
            layoutInflater, container, false
        )
        updateUI(args.kategori)
        binding.button.setOnClickListener { hitungBangunDatar() }
        binding.reset.setOnClickListener { resetBangunDatar() }
        binding.save.setOnClickListener { saveCalculation() }
        binding.detailButton.setOnClickListener { view: View ->
            kategoriBangunDatar = args.kategori
            view.findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToHasilFragment(kategoriBangunDatar)
            )
        }
        binding.shareButton.setOnClickListener { shareData() }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.hitung_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_menuFragment_to_historiFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateUI(kategoriBangunDatar: KategoriBangunDatar) {
        if (kategoriBangunDatar == KategoriBangunDatar.PERSEGI) {
            showPersegi()
        } else if (kategoriBangunDatar == KategoriBangunDatar.SEGITIGA) {
            showSegitiga()
        } else {
            showLingkaran()
        }
    }

    private fun showPersegi() {
        binding.textView1.text = "Sisi"
        binding.textView3.visibility = View.INVISIBLE
        binding.editText2.visibility = View.INVISIBLE
    }

    private fun showSegitiga() {
        binding.textView1.text = "Alas"
        binding.textView3.text = "Tinggi"
        binding.kelilingRadioButton.visibility = View.INVISIBLE
    }

    private fun showLingkaran() {
        binding.textView1.text = "Jari-jari"
        binding.textView3.visibility = View.INVISIBLE
        binding.editText2.visibility = View.INVISIBLE
    }

    private fun hitungBangunDatar() {
        val text1 = binding.editText1.text.toString()
        val text2 = binding.editText2.text.toString()
        var area = "-"
        var circumference = "-"
        var bangunDatar = "-"

        val selectedJenis = binding.radioGroup.checkedRadioButtonId
        if (selectedJenis == -1) {
            Toast.makeText(context, R.string.rumus_invalid, Toast.LENGTH_LONG).show()
            return
        }
        kategoriPerhitungan = if (selectedJenis == R.id.luasRadioButton) {
            "Luas"
        } else {
            "Keliling"
        }

        val kategoriBangunDatar = args.kategori
        if (kategoriBangunDatar == KategoriBangunDatar.PERSEGI) {
            if (TextUtils.isEmpty(text1)) {
                Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
                return
            }
            bangunDatar = "Persegi"
            val sisi = binding.editText1.text.toString()
            if (kategoriPerhitungan == "Luas") {
                area = hitungLuasPersegi(sisi.toDouble())
                circumference = "${4 * sisi.toDouble()}"
            } else {
                area = "${sisi.toDouble() * sisi.toDouble()}"
                circumference = hitungKelilingPersegi(sisi.toDouble())
            }
        } else if (kategoriBangunDatar == KategoriBangunDatar.SEGITIGA) {
            if (TextUtils.isEmpty(text1)) {
                Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
                return
            }
            if (TextUtils.isEmpty(text2)) {
                Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
                return
            }
            bangunDatar = "Segitiga"
            val alas = binding.editText1.text.toString()
            val tinggi = binding.editText2.text.toString()
            if (kategoriPerhitungan == "Luas") {
                area = hitungLuasSegitiga(alas.toDouble(), tinggi.toDouble())
            }
        } else {
            if (TextUtils.isEmpty(text1)) {
                Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
                return
            }
            bangunDatar = "Lingkaran"
            val jari = binding.editText1.text.toString()
            if (kategoriPerhitungan == "Luas") {
                area = hitungLuasLingkaran(jari.toDouble())
                circumference = "${2 * 3.14 * jari.toDouble()}"
            } else {
                area = "${3.14 * jari.toDouble() * jari.toDouble()}"
                circumference = hitungKelilingLingkaran(jari.toDouble())
            }
        }
        tempDataCalculation = BangunDatarEntity(
            type = bangunDatar,
            value1 = text1,
            value2 = text2,
            area = area,
            circumference = circumference
        )
        binding.detailButton.visibility = View.VISIBLE
        binding.shareButton.visibility = View.VISIBLE
        binding.save.visibility = View.VISIBLE
    }

    private fun hitungLuasPersegi(sisi: Double): String {
        val luas = sisi * sisi
        binding.resultTextView.text = luas.toString()
        return luas.toString()
    }

    private fun hitungKelilingPersegi(sisi: Double): String {
        val keliling = 4 * sisi
        binding.resultTextView.text = keliling.toString()
        return keliling.toString()
    }

    private fun hitungLuasSegitiga(alas: Double, tinggi: Double): String {
        val luas = 0.5 * alas * tinggi
        binding.resultTextView.text = luas.toString()
        return luas.toString()
    }

    private fun hitungLuasLingkaran(jari: Double): String {
        val luas = 3.14 * jari * jari
        binding.resultTextView.text = luas.toString()
        return luas.toString()
    }

    private fun hitungKelilingLingkaran(jari: Double): String {
        val keliling = 2 * 3.14 * jari
        binding.resultTextView.text = keliling.toString()
        return keliling.toString()
    }

    private fun resetBangunDatar() {
        binding.editText1.setText("")
        binding.editText2.setText("")
        binding.radioGroup.clearCheck()
        binding.resultTextView.text = ""
        binding.typeTextView.text = ""
        binding.detailButton.visibility = View.INVISIBLE
        binding.shareButton.visibility = View.INVISIBLE
        binding.save.visibility = View.INVISIBLE
    }

    private fun shareData() {
        val selectedId = binding.radioGroup.checkedRadioButtonId
        val gender = if (selectedId == R.id.luasRadioButton)
            getString(R.string.luas)
        else
            getString(R.string.keliling)
        val message = getString(
            R.string.bagikan_template,
            binding.editText1.text,
            binding.editText2.text,
            gender,
            binding.resultTextView.text,
            binding.typeTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    private fun saveCalculation() {
        viewModel.addNote(tempDataCalculation)
    }
}