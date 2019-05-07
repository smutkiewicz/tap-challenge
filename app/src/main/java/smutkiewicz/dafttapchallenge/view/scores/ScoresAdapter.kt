package smutkiewicz.dafttapchallenge.view.scores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import smutkiewicz.dafttapchallenge.R
import smutkiewicz.dafttapchallenge.entity.Score

class ScoresAdapter : RecyclerView.Adapter<ScoresViewHolder>() {

    var items = emptyList<Score>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_score, parent, false)
        return ScoresViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}