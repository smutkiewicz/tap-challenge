package smutkiewicz.dafttapchallenge.view.scores

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.top_score.view.*
import smutkiewicz.dafttapchallenge.R
import smutkiewicz.dafttapchallenge.entity.Score

class ScoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(score: Score) = with(itemView) {
        scorePositionTv.text = context.getString(R.string.score_position, score.position)
        scoreTapsAmountTv.text = context.getString(R.string.score_amount_of_taps, score.amountOfTaps)
        scoreTimestampTv.text = score.timestamp
    }
}