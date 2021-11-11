package uz.gita.waterdrink.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.gita.waterdrink.R
import uz.gita.waterdrink.data.entity.DrinkWaterEntity
import uz.gita.waterdrink.sharedPref.SharedPref

class WaterAdapter(private val list: List<DrinkWaterEntity>) :
    RecyclerView.Adapter<WaterAdapter.Vh>() {
    private var listener: ((DrinkWaterEntity, Int, View) -> Unit)? = null

    inner class Vh(view: View) : RecyclerView.ViewHolder(view) {
        private val more = view.findViewById<ImageView>(R.id.more)
        private val time = view.findViewById<TextView>(R.id.waterDrunkTime)
        private val size = view.findViewById<TextView>(R.id.drunkWaterSize)
        private val drop = view.findViewById<ImageView>(R.id.drop)
        val pref = SharedPref.getShared()

        init {
            more.setOnClickListener {
                listener?.invoke(list[absoluteAdapterPosition], absoluteAdapterPosition, more)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            val data = list[absoluteAdapterPosition]
            time.text = data.time
            size.text = "${data.size} ml"

        }

        private fun selectCup(size: Int):Int {
            when (size) {
                100 -> {
                    drop.setBackgroundResource(R.drawable.little)
                }
                150 -> {
                    drop.setBackgroundResource(R.drawable.ic_drink_bottle250)

                }
                200 -> {
                    drop.setBackgroundResource(R.drawable.ic_drink_bottle300)

                }
                250 -> {
                    drop.setBackgroundResource(R.drawable.ic_drink_bottle300)

                }
                300 -> {
                    drop.setBackgroundResource(R.drawable.ic_drink_bottle400)

                }
                400 -> {
                    drop.setBackgroundResource(R.drawable.ic_drink_bottle500)

                }
                500 -> {
                    drop.setBackgroundResource(R.drawable.large)

                }
            }
            return pref.cupSize

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Vh =
        Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_drunk_water, parent, false))


    override fun onBindViewHolder(holder: Vh, p1: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size

    fun setListener(f: (DrinkWaterEntity, Int, View) -> Unit) {
        listener = f
    }

}