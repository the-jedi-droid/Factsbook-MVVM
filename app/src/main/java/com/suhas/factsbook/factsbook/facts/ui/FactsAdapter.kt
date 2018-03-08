package com.suhas.factsbook.factsbook.facts.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.suhas.factsbook.factsbook.R
import com.suhas.factsbook.factsbook.model.Row
import kotlinx.android.synthetic.main.layout_fact.view.*

class FactsAdapter : RecyclerView.Adapter<FactsAdapter.FactViewHolder>() {

    var rows: List<Row> = mutableListOf()
        set(value) {
            (rows as MutableList).clear()
            (rows as MutableList).addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.layout_fact, parent, false))
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(rows[position])
    }

    override fun onViewRecycled(holder: FactViewHolder) {
        super.onViewRecycled(holder)

        // https://github.com/bumptech/glide/issues/1591
        holder.itemView.factImage.setImageDrawable(null)
        holder.itemView.factImage.layout(0, 0, 0, 0)
    }

    class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(row: Row) = with(itemView) {
            factTitleText.text = row.title ?: ""
            factDescriptionText.text = row.description ?: ""
            row.imageHref?.let {
                Glide.with(context)
                        .load(row.imageHref)
                        .apply(RequestOptions().skipMemoryCache(true))
                        .into(factImage)
            }
        }

    }
}