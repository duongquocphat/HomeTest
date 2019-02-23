package com.wordpress.mobilegeeksblog.hometest.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wordpress.mobilegeeksblog.hometest.entity.HotKeyword
import kotlinx.android.synthetic.main.item_hot_key.view.*
import java.util.*


class HotKeywordsAdapter(private val context: Context, private val hotKeywordList: ArrayList<HotKeyword>) :
    RecyclerView.Adapter<HotKeywordsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(com.wordpress.mobilegeeksblog.hometest.R.layout.item_hot_key, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // remove unnecessary space characters
        val tempHotKeyword =
            hotKeywordList[position]
                .keyword
                .replace("\\s+".toRegex(), " ")
                .trim()

        setRandomBackgroundColor(holder, position)
        setHotKeywordText(tempHotKeyword, holder)
    }

    private fun setHotKeywordText(tempHotKeyword: String, holder: MyViewHolder) {
        val spaceCount = tempHotKeyword.length - tempHotKeyword.replace(" ", "").length
        when (spaceCount) {
            // words with only 0 space character
            0 ->
                holder.tvHotKeyword.text = tempHotKeyword
            // words with only 1 space character
            1 ->
                if (tempHotKeyword.contains(" ")) {
                    val firstSpaceCharacter = tempHotKeyword.indexOf(" ", 0, false)
                    holder.tvHotKeyword.text =
                        tempHotKeyword.replaceRange(firstSpaceCharacter, firstSpaceCharacter, "\n")
                }
            // words with more 1 space character
            else -> {
                val hotKeywordLength = tempHotKeyword.length
                val hotKeywordMiddleIndex = hotKeywordLength / 2
                val isEvenHotKeywordLength = hotKeywordLength % 2 == 0

                // with Even Numbers
                if (isEvenHotKeywordLength) {
                    if (tempHotKeyword.toCharArray()[hotKeywordMiddleIndex - 1] == ' ') {
                        holder.tvHotKeyword.text =
                            tempHotKeyword.replaceRange(
                                hotKeywordMiddleIndex,
                                hotKeywordMiddleIndex,
                                "\n"
                            )
                        return
                    }

                    val textLeft = tempHotKeyword.substring(0, hotKeywordMiddleIndex).reversed()
                    val textRight = tempHotKeyword.substring(hotKeywordMiddleIndex, tempHotKeyword.length)

                    val textLeftSpaceIndex = textLeft.indexOf(" ", 0, false)
                    val textRightSpaceIndex = textRight.indexOf(" ", 0, false) + 1

                    if (textLeftSpaceIndex >= textRightSpaceIndex)
                        holder.tvHotKeyword.text =
                            tempHotKeyword.replaceRange(
                                textLeft.length + textRightSpaceIndex,
                                textLeft.length + textRightSpaceIndex,
                                "\n"
                            )
                    else
                        holder.tvHotKeyword.text =
                            tempHotKeyword.replaceRange(
                                textLeft.length - textLeftSpaceIndex - 1,
                                textLeft.length - textLeftSpaceIndex,
                                "\n"
                            )
                }
                // with Odd Numbers
                else {
                    if (tempHotKeyword.toCharArray()[hotKeywordMiddleIndex] == ' ') {
                        holder.tvHotKeyword.text =
                            tempHotKeyword.replaceRange(
                                hotKeywordMiddleIndex,
                                hotKeywordMiddleIndex,
                                "\n"
                            )
                        return
                    }

                    val textLeft = tempHotKeyword.substring(0, hotKeywordMiddleIndex).reversed()
                    val textRight = tempHotKeyword.substring(hotKeywordMiddleIndex, tempHotKeyword.length)

                    val textLeftSpaceIndex = textLeft.indexOf(" ", 0, false)
                    val textRightSpaceIndex = textRight.indexOf(" ", 0, false) + 1

                    if (textLeftSpaceIndex >= textRightSpaceIndex)
                        holder.tvHotKeyword.text =
                            tempHotKeyword.replaceRange(
                                textLeft.length + textRightSpaceIndex,
                                textLeft.length + textRightSpaceIndex,
                                "\n"
                            )
                    else
                        holder.tvHotKeyword.text =
                            tempHotKeyword.replaceRange(
                                textLeft.length - textLeftSpaceIndex,
                                textLeft.length - textLeftSpaceIndex,
                                "\n"
                            )

                }
            }
        }
    }

    private fun setRandomBackgroundColor(holder: MyViewHolder, position: Int) {
        val shape = GradientDrawable()
        shape.cornerRadius = 10F
        when (hotKeywordList[position].randomNumber) {
            0 ->
                shape.setColor(
                    ContextCompat.getColor(
                        context,
                        com.wordpress.mobilegeeksblog.hometest.R.color.colorAccent
                    )
                )
            1 ->
                shape.setColor(
                    ContextCompat.getColor(
                        context,
                        com.wordpress.mobilegeeksblog.hometest.R.color.colorBlue
                    )
                )
            2 ->
                shape.setColor(
                    ContextCompat.getColor(
                        context,
                        com.wordpress.mobilegeeksblog.hometest.R.color.colorOrange
                    )
                )
            3 ->
                shape.setColor(
                    ContextCompat.getColor(
                        context,
                        com.wordpress.mobilegeeksblog.hometest.R.color.colorPrimary
                    )
                )
            4 ->
                shape.setColor(
                    ContextCompat.getColor(
                        context,
                        com.wordpress.mobilegeeksblog.hometest.R.color.colorYellow
                    )
                )
        }
        holder.tvHotKeyword.background = shape
    }

    override fun getItemCount(): Int = hotKeywordList.size

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHotKeyword: TextView = view.tvHotKeyword
    }

}