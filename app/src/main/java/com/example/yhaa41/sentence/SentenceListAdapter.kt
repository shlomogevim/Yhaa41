package com.example.yhaa41.sentence

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yhaa41.R
import com.example.yhaa41.util.Helper
import kotlinx.android.synthetic.main.sentence_layout.view.*
import kotlinx.android.synthetic.main.sentence_layout.view.sentenceTextView


class SentenceListAdapter(val context: Context, private var sentenceList: ArrayList<Sentence>) :
    RecyclerView.Adapter<SentenceListAdapter.SentenceViewHolder>() {

    fun updateSentenceList(newList: ArrayList<Sentence>) {
        sentenceList.clear()
        sentenceList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.sentence_layout, parent, false)
        return SentenceViewHolder(view)
    }

    override fun getItemCount() = sentenceList.size

    override fun onBindViewHolder(holder: SentenceViewHolder, position: Int) {
        holder.view.sentenceTextView.text = sentenceList[position].sentenceText
        holder.view.explainTextView.text = sentenceList[position].explainText
        setSentenceStyle(holder, position)
        holder.view.flowerPostIV.setOnClickListener {
            /*val action1 = ListFragmantDirections.actionListFragmantToSentenceListFragment()
                Navigation.findNavController(it).navigate(action1)*/
            val action = SentenceListFragmentDirections.actionSentenceListFragmentToVideoFtagment()
            action.setVideoNum(position + 1)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun setSentenceStyle(holder: SentenceListAdapter.SentenceViewHolder, position: Int) {
        val vi = holder.view.sentenceTextView
        vi.setTextColor(Color.parseColor("#ffff5a")) //yellow
        vi.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 20f)
        val helper = Helper(context)
        vi.typeface = helper.getTypeFace(1)
        vi.setLineSpacing(1.0f, 1.0f)

        val vi2 = holder.view.explainTextView
        vi2.setTextColor(Color.parseColor("#ffff5a")) //yellow
        vi2.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 24f)
        vi2.typeface = helper.getTypeFace(13)
        vi2.setLineSpacing(1.3f, 1.3f)

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 30.toPx(), 0, 0)
        vi2.setLayoutParams(params)
    }

    fun Int.toPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

    class SentenceViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}