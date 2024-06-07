package com.example.main.fragments.videos.recyclerview.videogroups.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VideoGroupItemDecoration(
    private val innerDivider: Int,
    private val outerDivider: Int,
    private val horizontalPadding: Int
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

        val isTopmost = adapter.isTopmost(currentPosition)
        val isBottommost = adapter.isBottommost(currentPosition)

        val oneSideInnerDivider = innerDivider / 2

        outRect.run {
            top = if (isTopmost) outerDivider else oneSideInnerDivider
            bottom = if (isBottommost) outerDivider else oneSideInnerDivider
            left = horizontalPadding
            right = horizontalPadding
        }

    }

    private fun RecyclerView.Adapter<*>.isTopmost(
        currentPosition: Int
    ): Boolean = currentPosition == 0

    private fun RecyclerView.Adapter<*>.isBottommost(
        currentPosition: Int
    ): Boolean = currentPosition == itemCount - 1

}