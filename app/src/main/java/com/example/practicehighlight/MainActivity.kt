package com.example.practicehighlight

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var textToBeHighlighted = "布理是这一带最大的村庄。这块有人居住的区域相较于外面的荒野，像是大海中的孤岛一般遗世而独立。除了布理之外，山的另一边还有史戴多村，再往东方过去一点的深谷中则是康比村，位于契特森林的边缘还有一个叫阿契特的村庄。夹在布理山和这些村庄之间有一块只有几哩宽的小林场。"
    private var wordList = mutableListOf("布理","一般", "山", "一点", "外面的", "面", "人")
    private var spannableString = SpannableString(textToBeHighlighted)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textFinal.text = highlightText(textToBeHighlighted, wordList)
    }

    private fun highlightText(text: String, words: MutableList<String>):SpannableString {
        val indexesAndWordSizes = getIndexesAndWordSizes(text, words)

        indexesAndWordSizes.forEach{
            spannableString.setSpan(BackgroundColorSpan(Color.YELLOW), it.first, it.first+it.second, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString
    }

    private fun getIndexesAndWordSizes(text: String, words: MutableList<String>): MutableList<Pair<Int, Int>> {

        val indexesAndWordSizes = mutableListOf<Pair<Int,Int>>()

        if (words.isNotEmpty()){

            words.forEach{word ->
                var index = text.indexOf(word, 0)
                while (index != -1){
                    indexesAndWordSizes.add(Pair(index, word.length))
                    index = text.indexOf(word, index+1)
                }
            }
        }
        return indexesAndWordSizes
    }
}
