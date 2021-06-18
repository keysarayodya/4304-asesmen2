package org.d3if0044.bangundatar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if0044.bangundatar.data.network.UniversityEntity
import org.d3if0044.bangundatar.data.network.UniversityEntityItem
import org.d3if0044.bangundatar.databinding.DaftarUniversitasBinding

class UniversitasAdapter  : RecyclerView.Adapter<UniversitasAdapter.ViewHolder>() {
    private val data = mutableListOf<UniversityEntityItem>()
    fun updateData(newData: List<UniversityEntityItem>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: DaftarUniversitasBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(universitas: UniversityEntityItem) {
            with(binding) {
                countryTextView.text = universitas.country
                namaUniversitasTextView.text = universitas.name
                websiteTextView.text = universitas.web_pages[0]
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DaftarUniversitasBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}