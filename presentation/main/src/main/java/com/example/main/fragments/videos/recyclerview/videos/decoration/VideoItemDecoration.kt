package com.example.main.fragments.videos.recyclerview.videos.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VideoItemDecoration(
    private val innerDivider: Int,
    private val outerDivider: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view)
            .takeIf { it != RecyclerView.NO_POSITION } ?: return

        val isLeftmost = adapter.isLeftmost(currentPosition)
        val isRightmost = adapter.isRightmost(currentPosition)

        val oneSideInnerDividerPx = innerDivider / 2

        with(outRect) {
            left = if (isLeftmost) outerDivider else oneSideInnerDividerPx
            right = if (isRightmost) outerDivider else oneSideInnerDividerPx
            top = 0
            bottom = 0
        }
    }

    private fun RecyclerView.Adapter<*>.isLeftmost(
        currentPosition: Int
    ): Boolean = currentPosition == 0

    private fun RecyclerView.Adapter<*>.isRightmost(
        currentPosition: Int
    ): Boolean = currentPosition == itemCount - 1
}