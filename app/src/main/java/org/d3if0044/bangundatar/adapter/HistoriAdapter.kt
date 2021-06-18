package org.d3if0044.bangundatar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if0044.bangundatar.R
import org.d3if0044.bangundatar.data.local.BangunDatarEntity
import org.d3if0044.bangundatar.databinding.ItemHistoriBinding
import java.text.SimpleDateFormat
import java.util.*

enum class HistoriClickAction { UPDATE, DELETE }
class HistoriListener(val clickListener: (note: BangunDatarEntity, action: HistoriClickAction) -> Unit) {
    fun onClick(note: BangunDatarEntity, action: HistoriClickAction) = clickListener(note, action)
}

class HistoriAdapter (private val clickListener: HistoriListener): RecyclerView.Adapter<HistoriAdapter.ViewHolder>() {
    private val data = mutableListOf<BangunDatarEntity>()
    fun updateData(newData: List<BangunDatarEntity>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }
    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder(
            private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
                Locale("id", "ID"))
        fun bind(item: BangunDatarEntity, clickListener: HistoriListener) = with(binding) {
            deleteBtn.setOnClickListener{
                clickListener.onClick(item, HistoriClickAction.DELETE)
            }
            when(item.type) {
                "Persegi" -> {
                    typeTextView.text = item.type
                    value1TextView.text = "Sisi: ${item.value1}"
                    areaTextView.text = "Luas: ${item.area}"
                    circumferenceTextView.text = "Keliling: ${item.circumference}"
                }
                "Segitiga" -> {
                    typeTextView.text = item.type
                    value1TextView.text = "Alas: ${item.value1}"
                    value2TextView.text = "Tinggi: ${item.value2}"
                    areaTextView.text = "Luas: ${item.area}"
                }
                "Lingkaran" -> {
                    typeTextView.text = item.type
                    value1TextView.text = "Jari jari: ${item.value1}"
                    areaTextView.text = "Luas: ${item.area}"
                    circumferenceTextView.text = "Keliling: ${item.circumference}"
                }
            }
        }
    }
}